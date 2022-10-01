package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain {
    DcMotor leftRear;
    DcMotor rightRear;
    DcMotor leftFront;
    DcMotor rightFront;

    int ticksToWheelRevolution = 1680;
    int wheelRevolutionDistanceInches = 3; // Can be 2.95 inches

    public Drivetrain() {
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");

        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void forward(double power) {
            leftRear.setPower(power);
            rightRear.setPower(power);
            rightFront.setPower(power);
            leftFront.setPower(power);
    }

    public void turning(double power) {
            leftRear.setPower(-power);
            rightRear.setPower(power);
            leftFront.setPower(-power);
            rightFront.setPower(power);
    }

    public void stop() {
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);

        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void strafe(double power) {
            leftFront.setPower(power);
            rightRear.setPower(power);
            rightFront.setPower(-power);
            leftRear.setPower(-power);
    }

    public void encoderForward(double inches) {
        int ticks = (int) (inches / wheelRevolutionDistanceInches) * ticksToWheelRevolution;

        leftFront.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);
        leftRear.setTargetPosition(ticks);
        rightRear.setTargetPosition(ticks);

        forward(0.75);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void encoderStrafe(double inches) {
        int ticks = (int) (inches / wheelRevolutionDistanceInches) * ticksToWheelRevolution;

        leftFront.setTargetPosition(ticks);
        rightFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(-ticks);
        rightRear.setTargetPosition(ticks);

        strafe(0.75);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
