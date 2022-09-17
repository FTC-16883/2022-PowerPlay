package org.firstinspires.ftc.teamcode.techknowlogic;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class BlueCarousalParkingAtStorageUnit extends BaseBlueCarousal {

    public static double PARK_ROBOT_STEP1_STRAFE_LEFT = 20;

    @Override
    protected void parkRobot(SampleMecanumDrive driveTrain) {
        Trajectory strafeLeft = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeLeft(PARK_ROBOT_STEP1_STRAFE_LEFT)
                .build();
        Trajectory strafeRight = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeRight(5)
                .build();
        Trajectory forward = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .forward(20)
                .build();
        driveTrain.followTrajectory(strafeLeft);
        driveTrain.turn(Math.toRadians(-90));
        driveTrain.followTrajectory(strafeRight);

    } protected void parkRobot2(SampleMecanumDrive driveTrain) {


    }
    protected void parkRobot3(SampleMecanumDrive driveTrain) {


    }
    protected  void dropoff(SampleMecanumDrive driveTrain){

    }
    protected void dropoff2(SampleMecanumDrive driveTrain) {


    }
}
