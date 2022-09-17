package org.firstinspires.ftc.teamcode.techknowlogic;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "Blue Carousal")
@Config
@Disabled
@Deprecated
public class BlueCarousal extends BaseAutonomous {

    //public static Pose2d startingPosition = new Pose2d(-30, -63, Math.toRadians(270));

    public static double DRIVE_TO_HUB_STEP1_STRAFE_LEFT = 29;
    public static double DRIVE_TO_HUB_STEP2_BACK = 25;

    public static double DRIVE_TO_CAROUSAL_STEP1_FORWARD = 16;
    public static double DRIVE_TO_CAROUSAL_STEP2_STRAFE_RIGHT = 80;
    public static double DRIVE_TO_CAROUSAL_STEP3_FORWARD = 5;
    public static double DRIVE_TO_STORAGE_UNIT_BACK = 27;

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
        Trajectory strafeRight = driveTrain.trajectoryBuilder(new Pose2d(), false)
                .strafeRight(DRIVE_TO_HUB_STEP1_STRAFE_LEFT)
                .build();
        driveTrain.followTrajectory(strafeRight);

        Trajectory pathToShippingHub = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(DRIVE_TO_HUB_STEP2_BACK)
                .build();

        driveTrain.followTrajectory(pathToShippingHub);
    }

    @Override
    protected boolean isCarousalSpinReversed() {
        return true;
    }

    @Override
    protected void driveToCarousal(SampleMecanumDrive driveTrain) {

        Trajectory forwardPath = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .forward(DRIVE_TO_CAROUSAL_STEP1_FORWARD)
                .build();
        driveTrain.followTrajectory(forwardPath);

        Trajectory strafeLeft = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeLeft(DRIVE_TO_CAROUSAL_STEP2_STRAFE_RIGHT)
                .build();

        driveTrain.followTrajectory(strafeLeft);
        Trajectory straight = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .forward(DRIVE_TO_CAROUSAL_STEP3_FORWARD)
                .build();

        driveTrain.followTrajectory(straight);
    }

    @Override
    protected void parkRobot(SampleMecanumDrive driveTrain) {
        Trajectory reversePath = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(DRIVE_TO_STORAGE_UNIT_BACK)
                .build();
        driveTrain.followTrajectory(reversePath);
    }
    protected void parkRobot2(SampleMecanumDrive driveTrain) {


    }
    protected void parkRobot3(SampleMecanumDrive driveTrain) {


    }
    protected  void dropoff(SampleMecanumDrive driveTrain){

    }
    protected void dropoff2(SampleMecanumDrive driveTrain) {


    }
}
