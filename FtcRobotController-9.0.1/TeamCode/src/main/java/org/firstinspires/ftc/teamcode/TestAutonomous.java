package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous
public class TestAutonomous extends LinearOpMode {
    @Override
    public void runOpMode() {
        // create a new robot
        Robot bot = new Robot();

        bot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

    }
}
