package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;


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



@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")

public class PracticeTeleOp extends LinearOpMode {

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

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
           // double drive = -;
            //double turn  =  ;
            leftPower    = gamepad1.left_stick_y ;
            rightPower   = gamepad1.right_stick_y ;



            // Send calculated power to wheels
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);

            if (gamepad1.a) {
                robot.intake.setPower(1);
            }
            else if (gamepad1.b) {
                robot.intake.setPower(-1);
            }
            else {
                robot.intake.setPower(0);
            }


            if (gamepad1.left_bumper) {
                robot.arm.setPower(0.5);
            }
            else if (gamepad1.right_bumper) {
                robot.arm.setPower(-0.5);
            }
            else {
                robot.arm.setPower(0);
            }

            if(gamepad1.y){
                robot.launchAirplane();
            }

            if(gamepad2.a){
                robot.arm.setTargetPosition((int)robot.LOAD[0]);
                robot.bucket.setPosition(robot.LOAD[1]);
            }else if(gamepad2.b){
                robot.arm.setTargetPosition((int)robot.CARRY[0]);
                robot.bucket.setPosition(robot.CARRY[1]);
            }else if (gamepad2.x){
                robot.arm.setTargetPosition((int)robot.PREFLIP[0]);
                robot.bucket.setPosition(robot.PREFLIP[1]);
            }else if (gamepad2.y){
                robot.arm.setTargetPosition((int)robot.FLIP[0]);
                robot.bucket.setPosition(robot.FLIP[1]);
            }

            // Show the wheel power.
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();



        }

    }
}
