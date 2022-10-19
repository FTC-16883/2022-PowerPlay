package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    public static DcMotorEx armLeft;
    public static DcMotorEx armRight;
    public static Servo clawLeft;
    public static Servo clawRight;

    public Arm() {

    }

    public static void init(DcMotorEx armLeft,DcMotorEx armRight, Servo clawLeft, Servo clawRight) {
        Arm.armLeft = armLeft;
        Arm.armRight = armRight;
        Arm.clawLeft = clawLeft;
        Arm.clawRight = clawRight;

        clawRight.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection(DcMotorEx.Direction.REVERSE);
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
        armLeft.setTargetPosition(0);
        armRight.setTargetPosition(0);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(1);
        armRight.setPower(1);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armLow() {
        armLeft.setTargetPosition(1000);
        armRight.setTargetPosition(1000);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(1);
        armRight.setPower(1);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armMedium() {
        armLeft.setTargetPosition(2000);
        armRight.setTargetPosition(2000);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(1);
        armRight.setPower(1);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armHigh() {
        armLeft.setTargetPosition(3000);
        armRight.setTargetPosition(3000);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(1);
        armRight.setPower(1);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void setPower(double power) {
        armLeft.setPower(power);
        armRight.setPower(power);
    }
}
