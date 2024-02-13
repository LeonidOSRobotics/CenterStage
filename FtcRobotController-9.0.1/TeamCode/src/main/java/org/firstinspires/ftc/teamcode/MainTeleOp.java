package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


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
            double leftPower;
            double rightPower;

            leftPower    = gamepad1.left_stick_y ;
            rightPower   = gamepad1.right_stick_y ;


            // Send calculated power to wheels
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);

            //Activate Intake
            if (gamepad1.b) {
                robot.intake.setPower(.8);
            }
            else if (gamepad1.a) {
                robot.intake.setPower(-.8);
            }
            else {
                robot.intake.setPower(0);
            }

            //Arm and Bucket Movement

            robot.moveArmBucket(gamepad2.a, gamepad2.b);
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
