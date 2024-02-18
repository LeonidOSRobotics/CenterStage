package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */



@TeleOp(name="Main TeleOp", group="Linear OpMode")

public class MainTeleOp extends LinearOpMode {

    Robot robot = new Robot(); // Using the current Robot.java class




    @Override
    public void runOpMode() {

        /* Initialize the hardware variables
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
          //double forward;
          //double leftTurn;
            // double rightTurn;
          //double backwords;

           double leftTurn = gamepad1.left_stick_y;
           double rightTurn = gamepad1.right_stick_y;
            telemetry.addData("Right Bumper", gamepad1.right_bumper);

           if(gamepad1.right_bumper){
               telemetry.addData("Left", leftTurn/3);
               robot.leftDrive.setPower(leftTurn/2);
               robot.rightDrive.setPower(rightTurn/2);
           }else {
               telemetry.addData("Left", leftTurn);
               robot.leftDrive.setPower(leftTurn);
               robot.rightDrive.setPower(rightTurn);
           }

           //forward = gamepad1.right_trigger *-1 ;
           //backwords = gamepad1.left_trigger;
           //double drive = -gamepad1.left_stick_x;
           //double turn  = -gamepad1.left_stick_x;
           //leftTurn = Range.clip(forward + backwords + turn, -.9, .9) ;
           //rightTurn = Range.clip(forward + backwords - turn, -.9, .9) ;


            robot.leftDrive.setPower(leftTurn);
            robot.rightDrive.setPower(rightTurn);

           //forward = gamepad1.left_stick_y ;
           // leftTurn   = gamepad1.left_trigger * 100;
            //rightTurn = gamepad1.right_trigger * 100;

            // Send calculated power to wheels
           // robot.leftDrive.setPower(forward + leftTurn - rightTurn);
           // robot.rightDrive.setPower(forward - leftTurn + rightTurn);

            //Activate Intake
            if (gamepad2.left_trigger > 0) {
                robot.intake.setPower(.8);
            }
            else if (gamepad2.right_trigger > 0) {
                robot.intake.setPower(-.8);
            }
            else {
                robot.intake.setPower(0);
            }

            //Arm and Bucket Movement

            robot.moveArmBucket(gamepad2.a, gamepad2.b, false);
            sleep(200);

            telemetry.addData("State", robot.state);
            telemetry.update();

            //End Game Functions
            if(gamepad1.y){
                robot.launchAirplane();
            }



        }

    }
}
