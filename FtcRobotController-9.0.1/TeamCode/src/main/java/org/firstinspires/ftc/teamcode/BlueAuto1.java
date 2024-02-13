package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous
public class BlueAuto1 extends LinearOpMode {
    Robot bot;
    @Override
    public void runOpMode() {
        // create a new robot
        bot = new Robot();


        bot.init(hardwareMap);

        //Send telemetry message to indicate successful Encoder reset
      telemetry.addData("Starting at",  "%7d :%7d", bot.leftDrive.getCurrentPosition(),bot.rightDrive.getCurrentPosition());

        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        bot.moveArmBucket(true,false); //carry mode

        encoderDrive(bot.DRIVE_SPEED, 30, 30, 10);
        encoderDrive(bot.DRIVE_SPEED, -3, -3, 10);
        encoderDrive(bot.DRIVE_SPEED, 13.23, -13.23, 10);
        encoderDrive(bot.DRIVE_SPEED, -30, -30, 10);
        bot.moveArmBucket(true, false); // travel
        bot.moveArmBucket(true,false); //preflip
        sleep(200 );
        bot.moveArmBucket(true, false); //flip
        bot.moveArmBucket(true, false); //postflip
        encoderDrive(bot.DRIVE_SPEED, 8,8,10);
        encoderDrive(bot.DRIVE_SPEED, 13.23,-13.23,10);
        encoderDrive(bot.DRIVE_SPEED, 20,20,10);
        
        }


    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = bot.leftDrive.getCurrentPosition() + (int)(leftInches * bot.COUNTS_PER_INCH);
            newRightTarget = bot.rightDrive.getCurrentPosition() + (int)(rightInches * bot.COUNTS_PER_INCH);
            bot.leftDrive.setTargetPosition(newLeftTarget);
            bot.rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            bot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            bot.getPeriod().reset();
            bot.leftDrive.setPower(Math.abs(speed));
            bot.rightDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (bot.getPeriod().seconds() < timeoutS) &&
                    (bot.leftDrive.isBusy() && bot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Currently at",  " at %7d :%7d",
                        bot.leftDrive.getCurrentPosition(), bot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            bot.leftDrive.setPower(0);
            bot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            bot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
      }

    }
}
