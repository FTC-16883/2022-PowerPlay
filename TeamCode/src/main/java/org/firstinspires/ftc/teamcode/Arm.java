package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    public static DcMotor armLeft;
    public static DcMotor armRight;
    public static Servo claw;

    public Arm() {

    }

    public static void init(DcMotor armLeft, DcMotor armRight, Servo claw) {
        Arm.armLeft = armLeft;
        Arm.armRight = armRight;
        Arm.claw = claw;

        armRight.setDirection(DcMotorSimple.Direction.REVERSE);

        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void openClaw() {
        claw.setPosition(0);
    }

    public static void closeClaw() {
        claw.setPosition(1);
    }

    public static void armFloor() {
        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setTargetPosition(0);
        armRight.setTargetPosition(0);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.2);
        armRight.setPower(0.2);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armLow() {
        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setTargetPosition(100);
        armRight.setTargetPosition(100);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.5);
        armRight.setPower(0.5);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armMedium() {
        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setTargetPosition(2000);
        armRight.setTargetPosition(2000);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.2);
        armRight.setPower(0.2);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }

    public static void armHigh() {
        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setTargetPosition(3000);
        armRight.setTargetPosition(3000);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(0.2);
        armRight.setPower(0.2);

        while (armLeft.isBusy()) {
            //Telemetry.updateArmEncoder();
        }
    }
}
