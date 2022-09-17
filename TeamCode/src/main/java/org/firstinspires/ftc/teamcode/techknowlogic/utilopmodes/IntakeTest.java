package org.firstinspires.ftc.teamcode.techknowlogic.utilopmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
@Config
@Disabled
public class IntakeTest extends LinearOpMode {

    public static int FLOOR_NUMBER = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        //Get intake DCMotor
        DcMotorEx intake = hardwareMap.get(DcMotorEx.class, "intake");

        waitForStart();


        while (opModeIsActive()) {
            intake.setPower(0.6);
            telemetry.log().add("position " + intake.getCurrentPosition());
            sleep(100);
        }


    }

}