package org.firstinspires.ftc.teamcode.techknowlogic;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public abstract class BaseBlueCarousal extends BaseAutonomous {

    public static double DRIVE_TO_HUB_STEP1_STRAFE_LEFT = 54;
    public static double DRIVE_TO_HUB_STEP2_BACK = 3;

    public static double DRIVE_TO_CAROUSAL_STEP1_STRAFE_RIGHT = 50;
    public static double DRIVE_TO_CAROUSAL_STEP2_FORWARD = 35;
    public static double DRIVE_TO_CAROUSAL_STEP3_STRAFE_RIGHT = 10;

    @Override
    protected int getElevatorLevel(String shippingElementPosition) {
        if (shippingElementPosition.equals("LEFT")) {
            return 1;
        } else if (shippingElementPosition.equals("RIGHT")) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    protected void driveToShippingHub(SampleMecanumDrive driveTrain) {

        driveTrain.turn(Math.toRadians(90));

        Trajectory strafeLeft = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeLeft(DRIVE_TO_HUB_STEP1_STRAFE_LEFT)
                .build();
        Trajectory back = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(DRIVE_TO_HUB_STEP2_BACK)
                .build();
        driveTrain.followTrajectory(strafeLeft);


        driveTrain.followTrajectory(back);
    }

    @Override
    protected boolean isCarousalSpinReversed() {
        return true;
    }

    @Override
    protected void driveToCarousal(SampleMecanumDrive driveTrain) {

        Trajectory strafeRight = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeRight(DRIVE_TO_CAROUSAL_STEP1_STRAFE_RIGHT)
                .build();
        Trajectory forward = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .forward(DRIVE_TO_CAROUSAL_STEP2_FORWARD)
                .build();
        Trajectory step3StrafeRight = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeRight(DRIVE_TO_CAROUSAL_STEP3_STRAFE_RIGHT)
                .build();
        driveTrain.followTrajectory(strafeRight);


        driveTrain.followTrajectory(forward);


        driveTrain.followTrajectory(step3StrafeRight);
    }
}
