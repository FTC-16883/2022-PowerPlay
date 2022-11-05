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

//        claw.setDirection(Servo.Direction.REVERSE);
    }

    public static void closeClaw() {
        claw.setPosition(0.35);
    }

    public static void openClaw() {
        claw.setPosition(0.00);
    }

    public static void armFloor() {
        armLeft.setTargetPosition(0);
        armRight.setTargetPosition(0);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.25);
        armRight.setPower(0.25);

        wrist.setPosition(0.45);
    }

    public static void armLow() {
        armLeft.setTargetPosition(140);
        armRight.setTargetPosition(140);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.25);
        armRight.setPower(0.25);
    }

    public static void armMedium() {
        armLeft.setTargetPosition(360);
        armRight.setTargetPosition(360);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.25);
        armRight.setPower(0.25);
    }

    public static void armHigh() {
        armLeft.setTargetPosition(570);
        armRight.setTargetPosition(570);

        armLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.25);
        armRight.setPower(0.25);

        wrist.setPosition(1);
    }
}
