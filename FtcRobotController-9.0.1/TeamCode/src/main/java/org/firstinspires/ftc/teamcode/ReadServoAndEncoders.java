package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Data OpMode", group="Data OpMode")

/*Provides Data on the positions of different
motors and servos. Will not opperate Robot
 */

public class ReadServoAndEncoders extends LinearOpMode {


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

            double bucketPos = robot.bucket.getPosition();
            double airplanePos = robot.airplane.getPosition();
            int armPos = robot.arm.getCurrentPosition();


            telemetry.addData("Arm"+ armPos, "");
            telemetry.addData("Bucket" + bucketPos, "");
            telemetry.addData("Airplane"+  airplanePos, "");
            telemetry.update();
        }

    }
}