package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {

    //Declare Actuators
    DcMotor leftDrive = null;
    DcMotor rightDrive = null;
    Servo intake = null;
    BNO055IMU imu = null;

    // declare constant
    static final double     COUNTS_PER_MOTOR_REV    =  537.7;
    static final double     WHEEL_DIAMETER_INCHES   = 3.78 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV) /
                                                    (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 1;
    static final double     TURN_SPEED              = 1;

    /* local OpMode members. */
    HardwareMap hwMap = null; //hardware map
    private final ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public Robot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to hardware map
        hwMap = ahwMap;

        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        intake = hwMap.get(Servo.class, "intake");


        imu = hwMap.get(BNO055IMU.class, "imu");

        //Temporary Directions for drive train, change after testing if needed.
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);


        // set all motors to zero power
        stopDriveTrain();


        // Set all motors to run with encoders.
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Set all motors to run with encoders.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    private void stopDriveTrain() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    //TELE-OP METHODS

}
