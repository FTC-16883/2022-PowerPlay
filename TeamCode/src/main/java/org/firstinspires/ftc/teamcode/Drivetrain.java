package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain {
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;

    private static final int ticksToWheelRevolution = 1600;
    private static final double wheelRevolutionDistanceInches = 13;
    private static final double degreesperinch = 0.22;

    public Drivetrain() {
    }

    public static void init(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftRear, DcMotorEx rightRear) {
        Drivetrain.leftFront = leftFront;
        Drivetrain.rightFront = rightFront;
        Drivetrain.leftRear = leftRear;
        Drivetrain.rightRear = rightRear;

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static boolean isMoving() {
        return (leftFront.isBusy() || leftRear.isBusy() || rightRear.isBusy() || rightFront.isBusy());
    }

    public static void forward(double power) {
        leftRear.setPower(power);
        rightRear.setPower(power);
        rightFront.setPower(power);
        leftFront.setPower(power);
    }

    public static void strafe(double power) {
        leftRear.setPower(-power);
        rightRear.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(-power);
    }

    public static void stop() {
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }

    public static void turn(double power) {
        leftFront.setPower(power);
        rightRear.setPower(-power);
        rightFront.setPower(-power);
        leftRear.setPower(power);
    }

    public static void encoderForward(double inches) {
        int ticks = (int) (inches / wheelRevolutionDistanceInches) * ticksToWheelRevolution;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);
        leftRear.setTargetPosition(ticks);
        rightRear.setTargetPosition(ticks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        forward(0.75);

        while (isMoving() == true) {
//            Telemetry.updateDrivetrainEncoders();
        }
    }

    public static void encoderStrafe(double inches) {
        int ticks = (int) (inches / wheelRevolutionDistanceInches) * ticksToWheelRevolution;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(ticks);
        rightFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(-ticks);
        rightRear.setTargetPosition(ticks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        strafe(0.75);

        while (isMoving() == true) {
//            Telemetry.updateDrivetrainEncoders();

        }
    }


    public static void encoderTurn(double degrees) {
        int ticks = (int) (((degrees * degreesperinch) / wheelRevolutionDistanceInches) * ticksToWheelRevolution);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(ticks);
        rightFront.setTargetPosition(-ticks);
        leftRear.setTargetPosition(ticks);
        rightRear.setTargetPosition(-ticks);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turn(0.5);

        while (isMoving() == true) {
//            Telemetry.updateDrivetrainEncoders();

        }
    }

}