package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

public class Arm {
    public static DcMotor armLeft;
    public static DcMotor armRight;
    public static Servo claw;
    public static Servo wrist;

    public Arm() {

    }

    public static void init(DcMotor armLeft, DcMotor armRight, Servo claw, Servo wrist) {
        Arm.armLeft = armLeft;
        Arm.armRight = armRight;
        Arm.claw = claw;
        Arm.wrist = wrist;

        armLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void openClaw() {
        claw.setPosition(0.3);
    }

    public static void closeClaw() {
        claw.setPosition(0.1);
    }

    public static void armFloor() {
        armLeft.setTargetPosition(0);
        armRight.setTargetPosition(0);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }

    public static void armLow() {
        armLeft.setTargetPosition(10);
        armRight.setTargetPosition(10);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }

    public static void armMedium() {
        armLeft.setTargetPosition(20);
        armRight.setTargetPosition(20);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }

    public static void armHigh() {
        armLeft.setTargetPosition(30);
        armRight.setTargetPosition(30);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);
    }
}
