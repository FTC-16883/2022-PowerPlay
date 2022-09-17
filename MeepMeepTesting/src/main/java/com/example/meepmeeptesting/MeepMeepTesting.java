package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.MarkerCallback;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

import java.util.Vector;

public class MeepMeepTesting {

    public static double MAX_VEL = 50;
    public static double MAX_ACCEL = 50;
    public static double MAX_ANG_VEL = Math.toRadians(200);
    public static double MAX_ANG_ACCEL = Math.toRadians(200);
    public static double DRIVE_TO_HUB_STEP1_STRAFE_RIGHT = 40;
    public static double DRIVE_TO_HUB_STEP2_BACK = 3;

    public static double DRIVE_TO_CAROUSAL_STEP1_STRAFE_LEFT = 25;
    public static double DRIVE_TO_CAROUSAL_STEP2_FORWARD = 40;
    public static double DRIVE_TO_CAROUSAL_STEP3_STRAFE_LEFT = 10;

    public static void main(String[] args) throws Exception {
        // TODO: If you experience poor performance, enable this flag
        // System.setProperty("sun.java2d.opengl", "true");

        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep mm = new MeepMeep(750)
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_SKYSTONE_STARWARS)
                // Set theme
                .setTheme(new ColorSchemeRedDark())
                // Background opacity from 0-1
                .setBackgroundAlpha(1f)
                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(MAX_VEL, MAX_ACCEL, MAX_ANG_VEL, MAX_ANG_ACCEL, 9)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-30, -68, Math.toRadians(180)))
                                .strafeRight(DRIVE_TO_HUB_STEP1_STRAFE_RIGHT)
                                .back(DRIVE_TO_HUB_STEP2_BACK)
                                .strafeLeft(DRIVE_TO_CAROUSAL_STEP1_STRAFE_LEFT)
                                .forward(DRIVE_TO_CAROUSAL_STEP2_FORWARD)
                                .strafeLeft(DRIVE_TO_CAROUSAL_STEP3_STRAFE_LEFT)
                                .turn(Math.toRadians(45))
                                .lineTo(new Vector2d(-45,-65))
                                .build()
                )
                .start();
    }
}