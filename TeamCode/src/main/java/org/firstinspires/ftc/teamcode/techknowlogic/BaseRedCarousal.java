package org.firstinspires.ftc.teamcode.techknowlogic;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public abstract class BaseRedCarousal extends BaseAutonomous {

    public static double DRIVE_TO_HUB_STEP1_STRAFE_RIGHT = 54;
    public static double DRIVE_TO_HUB_STEP2_BACK = 3;

    public static double DRIVE_TO_CAROUSAL_STEP1_STRAFE_LEFT = 50;
    public static double DRIVE_TO_CAROUSAL_STEP2_FORWARD = 40;
    public static double DRIVE_TO_CAROUSAL_STEP3_STRAFE_LEFT = 10;

    @Override
    protected boolean isCarousalSpinReversed() {
        return false;
    }

    @Override
    protected int getElevatorLevel(String shippingElementPosition) {
        if (shippingElementPosition.equals("LEFT")) {
            return 2;
        } else if (shippingElementPosition.equals("RIGHT")) {
            return 3;
        } else {
            return 1;
        }
    }

    @Override
    protected void driveToShippingHub(SampleMecanumDrive driveTrain) {

        driveTrain.turn(Math.toRadians(-90));

        Trajectory strafeRight = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeRight(DRIVE_TO_HUB_STEP1_STRAFE_RIGHT)
                .build();

        driveTrain.followTrajectory(strafeRight);

        Trajectory step1 = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(DRIVE_TO_HUB_STEP2_BACK)
                .build();

        driveTrain.followTrajectory(step1);
    }

    @Override
    protected void driveToCarousal(SampleMecanumDrive driveTrain) {

        Trajectory strafeLeft = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeLeft(DRIVE_TO_CAROUSAL_STEP1_STRAFE_LEFT)
                .build();
        driveTrain.followTrajectory(strafeLeft);

        Trajectory forward = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .forward(DRIVE_TO_CAROUSAL_STEP2_FORWARD)
                .build();
        driveTrain.followTrajectory(forward);

        Trajectory step3StrafeLeft = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeLeft(DRIVE_TO_CAROUSAL_STEP3_STRAFE_LEFT)
                .build();
        driveTrain.followTrajectory(step3StrafeLeft);
    }

}
