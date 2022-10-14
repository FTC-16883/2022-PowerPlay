package org.firstinspires.ftc.teamcode.libs;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.Drivetrain;

public class Telemetry {
    private Telemetry() {

    }

    public static void addData(String caption, String value) {
        telemetry.addData(caption, value);
    }

    public static void updateDrivetrainEncoders() {
        telemetry.addData("Left Front", Drivetrain.leftFront.getCurrentPosition());
        telemetry.addData("Left Rear", Drivetrain.leftRear.getCurrentPosition());
        telemetry.addData("Right Front", Drivetrain.rightFront.getCurrentPosition());
        telemetry.addData("Right Rear", Drivetrain.rightRear.getCurrentPosition());
        telemetry.update();
    }

    public static void isRobotMoving() {
        telemetry.addData("Is Robot Moving", Boolean.toString(Drivetrain.isMoving()));
        telemetry.update();
    }

    public static void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
}
