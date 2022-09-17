package org.firstinspires.ftc.teamcode.techknowlogic;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.techknowlogic.util.CarousalSpinner;
import org.firstinspires.ftc.teamcode.techknowlogic.util.Elevator;
import org.firstinspires.ftc.teamcode.techknowlogic.util.TeamShippingElementDetector;

public abstract class BaseAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //Step-0 : Set the robot to starting position

        SampleMecanumDrive driveTrain = new SampleMecanumDrive(hardwareMap);
        //driveTrain.setPoseEstimate(startingPosition);

        driveTrain.servoIntakeArm.setPosition(1.0);
        TeamShippingElementDetector detector = new TeamShippingElementDetector(hardwareMap, telemetry);
        CarousalSpinner carousalSpinner = new CarousalSpinner(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);

        //Detection continue to happen throughout init
        detector.startDetection();

        waitForStart();

        //As detection continue to happen since init, we can stop detection (stop streaming)
        detector.stopDetection();

        //Step-1 : Scan for duck or Team Shipping Element
        String shippingElementPosition = detector.getElementPosition();
        telemetry.log().add("team shipping element position " + shippingElementPosition);

        int elevatorLevel = getElevatorLevel(shippingElementPosition);
        telemetry.log().add("elevator level " + elevatorLevel);

        Runnable raisetopos1 = new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                elevator.raiseToTheLevel(3);
            }
        };
        Runnable raisetopos = new Runnable() {
            @Override
            public void run() {

                elevator.raiseToTheLevel(elevatorLevel);
            }
        };
        //Step-2 : Drive to Team Shipping Hub
        new Thread(raisetopos).start();
        driveToShippingHub(driveTrain);

        //Step-3 : Drop the pre-loaded box in the appropriate level


        elevator.dropFreight();


        //while driving to carousal, bring the elevator down to zero in the background (in a separate thread)
        Runnable elevatorDropThread = new Runnable() {
            @Override
            public void run() {
                elevator.dropCarriageArmToHome();
                elevator.dropELevatorToZero();
            }
        };
        new Thread(elevatorDropThread).start();

        //OPTIONAL -- Step-4 Drive to carousal and spin
        try {
            driveToCarousal(driveTrain);
            carousalSpinner.spin(isCarousalSpinReversed());

        } catch (UnsupportedOperationException ex) {
            //Ignore since the carousal spinning is not supported for that
        }

        //Step-4 Park the robot in storage unit or warehouse
        new Thread(elevatorDropThread).start();
        parkRobot(driveTrain);

        new Thread(raisetopos1).start();
        dropoff(driveTrain);
        elevator.dropFreight();


        new Thread(elevatorDropThread).start();

        parkRobot2(driveTrain);

        new Thread(raisetopos1).start();
        dropoff2(driveTrain);
        elevator.dropFreight();
        parkRobot3(driveTrain);
    }

    protected abstract boolean isCarousalSpinReversed();

    protected abstract void driveToCarousal(SampleMecanumDrive driveTrain);

    protected abstract void parkRobot(SampleMecanumDrive driveTrain);

    protected abstract void parkRobot2(SampleMecanumDrive driveTrain);
    protected abstract void dropoff(SampleMecanumDrive driveTrain);
    protected abstract void parkRobot3(SampleMecanumDrive driveTrain);
    protected abstract void driveToShippingHub(SampleMecanumDrive driveTrain);

    protected abstract void dropoff2(SampleMecanumDrive driveTrain);
    protected abstract int getElevatorLevel(String shippingElementPosition);
}
