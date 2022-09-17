package org.firstinspires.ftc.teamcode.techknowlogic;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class RedCarousalParkingWarehouseThruBarriers extends BaseRedCarousal {

    public static double PARK_ROBOT_STEP1_STRAFE_RIGHT = 35;
    public static double PARK_ROBOT_STEP2_BACK = 140;

    @Override
    protected void parkRobot(SampleMecanumDrive driveTrain) {
        Trajectory strafeRight = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeRight(PARK_ROBOT_STEP1_STRAFE_RIGHT)
                .build();
        driveTrain.followTrajectory(strafeRight);

        Trajectory back = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(PARK_ROBOT_STEP2_BACK)
                .build();
        driveTrain.followTrajectory(back);
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
