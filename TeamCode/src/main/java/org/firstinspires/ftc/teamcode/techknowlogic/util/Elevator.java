package org.firstinspires.ftc.teamcode.techknowlogic.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.techknowlogic.DriverOperator;

@Config
public class Elevator {

    private DcMotorEx elevator = null;
    private Servo carriage = null;
    private Servo caExtender = null;

    public static int LEVEL1_TICKS = 310;
    public static int LEVEL2_TICKS = 800;
    public static int LEVEL3_TICKS = 1600;

    public Elevator(HardwareMap hardwareMap) {
        this.elevator = hardwareMap.get(DcMotorEx.class, "elevator");
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);

        this.carriage = hardwareMap.get(Servo.class, "carriage");
        carriage.setPosition(DriverOperator.CARRIAGE_HOME);

        caExtender  = hardwareMap.get(Servo.class, "caextender");

        //INITIAL STATE MUST BE 0.7
        caExtender.setPosition(0.7);
    }

    public void raiseToTheLevel(int level) {
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int desiredPosition = 0;
        if (level == 1) {
            desiredPosition = LEVEL1_TICKS;
        } else if (level == 2) {
            desiredPosition = LEVEL2_TICKS;
        } else {
            desiredPosition = LEVEL3_TICKS;
        }

        raiseToDesiredPosition(elevator, desiredPosition);
    }

    private void raiseToDesiredPosition(DcMotorEx elevator, int desiredPosition) {

        elevator.setTargetPosition(desiredPosition);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevator.setPower(1.0);

        while (elevator.isBusy()) {
            sleep(50);
        }
    }

    public void dropFreight(){
        carriage.setPosition(0.5);

        sleep(1000);
    }

    public void dropCarriageArmToHome() {
        carriage.setPosition(DriverOperator.CARRIAGE_HOME);

        sleep(1000);
    }

    public void dropELevatorToZero() {

        elevator.setTargetPosition(0);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevator.setPower(1.0);

        while (elevator.isBusy()) {
            sleep(50);
        }
    }

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}