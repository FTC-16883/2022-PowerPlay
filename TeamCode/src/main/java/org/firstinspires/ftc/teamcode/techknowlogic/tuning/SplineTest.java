package org.firstinspires.ftc.teamcode.techknowlogic.tuning;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
@Disabled
public class SplineTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(-30, -63, Math.toRadians(270)));
        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-30, -63, Math.toRadians(270)), true)
                .splineTo(new Vector2d(-15, -40), Math.toRadians(270))
                .build();

        drive.followTrajectory(traj);

        sleep(2000);

        drive.followTrajectory(
                drive.trajectoryBuilder(traj.end(), false)
                        .splineTo(new Vector2d(-70, -68), Math.toRadians(225))
                        .build()
        );

        sleep(2000);

        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d(-66, -64, Math.toRadians(180)))
                .back(new Double(15))
                .build()
        );

    }
}
