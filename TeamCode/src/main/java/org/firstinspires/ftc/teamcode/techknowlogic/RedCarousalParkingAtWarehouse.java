package org.firstinspires.ftc.teamcode.techknowlogic;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
@Config
public class RedCarousalParkingAtWarehouse extends BaseRedCarousal {

    public static double PARK_ROBOT_STEP1_BACK = 35;
    public static double PARK_ROBOT_STEP2_STRAFE_LEFT = 20;
    public static double PARK_ROBOT_STEP3_BACK = 95;

    @Override
    protected void parkRobot(SampleMecanumDrive driveTrain) {
        Trajectory step1Back = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(PARK_ROBOT_STEP1_BACK)
                .build();
        driveTrain.followTrajectory(step1Back);

        Trajectory step2StrafeLeft = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .strafeLeft(PARK_ROBOT_STEP2_STRAFE_LEFT)
                .build();
        driveTrain.followTrajectory(step2StrafeLeft);

        Trajectory step3Back = driveTrain.trajectoryBuilder(driveTrain.getPoseEstimate(), false)
                .back(PARK_ROBOT_STEP3_BACK)
                .build();
        driveTrain.followTrajectory(step3Back);
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
