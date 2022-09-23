package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    public HardwareMap hardwareMap = null;
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    public Drivetrain() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void drive() {
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public enum turnDirection {
        LEFT, RIGHT
    }

    public void turn(int direction) {
        if (direction == 0) {
            frontLeft.setPower(-0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(-0.5);
            backRight.setPower(0.5);
        } else if (direction == 1) {
            frontLeft.setPower(0.5);
            frontRight.setPower(-0.5);
            backLeft.setPower(0.5);
            backRight.setPower(-0.5);
        }
    }

    public enum strafeDirection {
        LEFT, RIGHT
    }

    public void strafe(int direction) {
        if (direction == 0) {
            frontLeft.setPower(-0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(-0.5);
        } else if (direction == 1) {
            frontLeft.setPower(0.5);
            frontRight.setPower(-0.5);
            backLeft.setPower(-0.5);
            backRight.setPower(0.5);
        }
    }

    public void toPosition(int encoderTicksForward, int encoderTicksStrafe) {
        int targetFrontLeft = encoderTicksForward - encoderTicksStrafe;
        int targetFrontRight = encoderTicksForward + encoderTicksStrafe;
        int targetBackLeft = encoderTicksForward + encoderTicksStrafe;
        int targetBackRight = encoderTicksForward - encoderTicksStrafe;

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setTargetPosition(targetFrontLeft);
        frontRight.setTargetPosition(targetFrontRight);
        backLeft.setTargetPosition(targetBackLeft);
        backRight.setTargetPosition(targetBackRight);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            frontLeft.setPower(0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(0.5);
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
