package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

public class Arm {
    public static DcMotorEx armLeft;
    public static DcMotorEx armRight;
    public static Servo claw;
    public static Servo wrist;

    public Arm() {

    }

    public static void init(DcMotorEx armLeft, DcMotorEx armRight, Servo claw, Servo wrist) {
        Arm.armLeft = armLeft;
        Arm.armRight = armRight;
        Arm.claw = claw;
        Arm.wrist = wrist;

        armLeft.setDirection(DcMotorEx.Direction.REVERSE);

        armLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        armRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        armLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public static void openClaw() {
        claw.setPosition(0.25);
    }

    public static void closeClaw() {
        claw.setPosition(0.05);
    }

    public static void armFloor() {
        armLeft.setTargetPosition(0);
        armRight.setTargetPosition(0);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }

    public static void armLow() {
        armLeft.setTargetPosition(10);
        armRight.setTargetPosition(10);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }

    public static void armMedium() {
        armLeft.setTargetPosition(20);
        armRight.setTargetPosition(20);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }

    public static void armHigh() {
        armLeft.setTargetPosition(30);
        armRight.setTargetPosition(30);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }
}
