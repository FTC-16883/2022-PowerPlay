package org.firstinspires.ftc.teamcode.techknowlogic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriverOperatorPrep extends OpMode {

    Servo teamElementpickuparm = null;
    double PICKUP_ARM_REST_POSITION = 1.0;

    Servo carriageArm = null;
    public final static double CARRIAGE_HOME = 0.03;

    DcMotor elevator = null;

    @Override
    public void init() {
        teamElementpickuparm  = hardwareMap.get(Servo.class, "caextender");
        teamElementpickuparm.setPosition(PICKUP_ARM_REST_POSITION);

        carriageArm = hardwareMap.servo.get("carriage");
        carriageArm.setPosition(CARRIAGE_HOME);

        elevator = hardwareMap.get(DcMotor.class, "elevator");
    }

    @Override
    public void loop() {

        double elevatorpower = 1;

        //Elevator is handled by Operator (gamepad2)
        if (gamepad2.left_bumper) {
            elevator.setPower(elevatorpower);
            //while elevator coming down, we would like to bring the arm to initial (zero) position
            carriageArm.setPosition(CARRIAGE_HOME);
        } else if (gamepad2.right_bumper) {
            elevator.setPower(-elevatorpower);
        } else {
            elevator.setPower(0);
        }
    }
}
