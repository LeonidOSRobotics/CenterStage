package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {

    //Declare Actuators
    DcMotor leftDrive = null;
    DcMotor rightDrive = null;
    DcMotor arm = null;
    CRServo intake = null;
    Servo bucket = null;
    Servo airplane = null;
    BNO055IMU imu = null;

    // declare constant
    static final double     COUNTS_PER_MOTOR_REV    =  537.7;
    static final double     WHEEL_DIAMETER_INCHES   = 3.78 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV) /
                                                    (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.40;
    static final double     TURN_SPEED              = 1;

    static final double     UNLAUNCHED              = 0.25;
    static final double     LAUNCHED                = 0.15;

    ArmBucketPosition state                         = ArmBucketPosition.CARRY;
    int stateNum                                    = 0;
    boolean previousStateA = false;
    boolean previousStateB = false;


    /* local OpMode members.*/
    HardwareMap hwMap = null; //hardware map
    private final ElapsedTime period = new ElapsedTime();

    public ElapsedTime getPeriod() {
        return period;
    }

    /* Constructor */
    public Robot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to hardware map
        hwMap = ahwMap;

        leftDrive = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        intake = hwMap.crservo.get("con_servo");
        bucket = hwMap.get(Servo.class, "bucket");
        airplane = hwMap.get(Servo.class, "plane");
        arm = hwMap.get(DcMotor.class, "arm");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //imu = hwMap.get(BNO055IMU.class, "imu");

        //Temporary Directions for drive train, change after testing if needed.
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);


        // set all motors to zero power
        stopDriveTrain();


        // Set all motors to stop and reset with encoders.
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        // Set all motors to run with encoders.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intake.setPower(0);
        airplane.setPosition(UNLAUNCHED);
        //bucket.setPosition(0.3);//test reading


    }

    //General Functions
    private void stopDriveTrain() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void moveArmBucket(boolean progress, boolean regress, boolean auto){

        //Set state of the arm:
        if(progress && (!previousStateA || auto)){
            stateNum++;
            previousStateA = true;
            previousStateB = false;
            if(stateNum == 7){
                stateNum = 0;
            }
        }else if( regress && stateNum > 0 && (!previousStateB || auto)) {
            stateNum--;
            previousStateA = false;
        }else{
            previousStateA = false;
            previousStateB = false;
        }
        if(stateNum == 0){
            state = ArmBucketPosition.LOAD;
        }else if(stateNum == 1){
            state = ArmBucketPosition.CARRY;
        }else if(stateNum == 2 || stateNum == 5){
            state=ArmBucketPosition.TRAVEL;
        }else if(stateNum == 3){
            state=ArmBucketPosition.PREFLIP;
        }else if(stateNum == 4){
            state=ArmBucketPosition.FLIP;
        }else if(stateNum == 6){
            state=ArmBucketPosition.POSTFLIP;
        }





        //Make Movement occur
        arm.setTargetPosition(state.getArmTicks());
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //Proportional control
        int error = (state.getArmTicks()) - (arm.getCurrentPosition());
        double speed = .00083 * error; //Kp = .00083

        if((state.getArmTicks() >= arm.getCurrentPosition()-10 || state.getArmTicks() <= arm.getCurrentPosition() +10 )&& state == ArmBucketPosition.CARRY){
            intake.setPower(-.2);
        }
        bucket.setPosition(state.getBucketPos());
        arm.setPower(speed);
        if((state.getArmTicks() >= arm.getCurrentPosition()-10 || state.getArmTicks() <= arm.getCurrentPosition() +10 ) &&state.getArmTicks() != arm.getCurrentPosition() && state == ArmBucketPosition.LOAD && intake.getPower() == 0 ){
            intake.setPower(.2);
        }

    }


    //TELE-OP METHODS

    public void launchAirplane(){
        airplane.setPosition(LAUNCHED);
    }

}
