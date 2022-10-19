package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    public static DcMotorEx arm;
    public static Servo clawLeft;
    public static Servo clawRight;

    public Arm() {

    }

    public static void init(DcMotorEx arm, Servo clawLeft, Servo clawRight) {
        Arm.arm = arm;
        Arm.clawLeft = clawLeft;
        Arm.clawRight = clawRight;

        clawRight.setDirection(Servo.Direction.REVERSE);
    }

    public static void openClaw() {
        clawLeft.setPosition(0);
        clawRight.setPosition(0);
    }

    public static void closeClaw() {
        clawLeft.setPosition(1);
        clawRight.setPosition(1);
    }

    public static void armFloor() {
        arm.setTargetPosition(0);

        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        arm.setPower(1);

        while (arm.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armLow() {
        arm.setTargetPosition(1000);

        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        arm.setPower(1);

        while (arm.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armMedium() {
        arm.setTargetPosition(2000);

        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        arm.setPower(1);

        while (arm.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armHigh() {
        arm.setTargetPosition(3000);

        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        arm.setPower(1);

        while (arm.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }
}
