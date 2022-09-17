package org.firstinspires.ftc.teamcode.techknowlogic.utilopmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
@Config
@Disabled
public class CarriageTest extends LinearOpMode {

    public static double POSITION = 0.6;

    @Override
    public void runOpMode() throws InterruptedException {

        Servo carriage = hardwareMap.get(Servo.class,"carriage");

        waitForStart();

        while(opModeIsActive()) {
            carriage.setPosition(POSITION);
        }

    }
}
