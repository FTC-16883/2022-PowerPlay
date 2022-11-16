/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Drivetrain;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.libs.Telemetry;

import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.RemoteCam;
import org.firstinspires.ftc.teamcode.libs.Telemetry;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.util.Calendar;
/*
 * @author Akash Sarada (akashsarada)
 *
 * This file is a LinearOpMode, A Operation Mode that runs line by Line
 * When deployed, this class should appear in the "Autonomous" dropdown menu in alphabetical order
 * When the class is selected, the classes is loaded with all the code before the "runOpMode" method
 * After the "INIT" button is pressed, all the code before the "waitForStart()" function is ran
 * After the "PLAY" button is pressed, all the code after the "waitForStart()" function is ran
 *
 * Once copied: Complete the checklist:

 */

@Autonomous(name="Blue_Left", group="Android Studio")
public class Blue_Left extends LinearOpMode
{
    // Declare every variable being used in the program here.
    private ElapsedTime runtime = new ElapsedTime();
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;

    public static DcMotorEx armRight;
    public static DcMotorEx armLeft;
    public static Servo claw;
    public static Servo wrist;

    public static RemoteCam camInput;
    public static int locSignal;
    public static Runtime opTime;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camInput.webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        camInput.init(camInput.webcam);


        Drivetrain.init(leftFront, rightFront, leftRear, rightRear);

        armLeft = hardwareMap.get(DcMotorEx.class, "armLeft");
        armRight = hardwareMap.get(DcMotorEx.class, "armRight");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
        camInput.xCordCam = 120;
        Arm.initAuton(armRight, armLeft, claw, wrist);
        Arm.closeClaw();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        //grip cone for autonomous

        Arm.wristIn();
        double color1 = camInput.color1average;
        double color2 = camInput.color2average;
        sleep(100);

        //Siddharth M Trials:
        if ((color1>150)) {
            locSignal = 3;
            telemetry.addData("Detected color is blue :", 3);
        } else if ((color2>75)&&(color2<110)) {
            locSignal = 1;
            telemetry.addData("Detected color is green :", 1);
        }
        else if ((color2>120)&&(color2<145)){
            locSignal = 2;
            telemetry.addData("Detected color is yellow :", 2);
        }


        telemetry.addData("color level 1", color1);
        telemetry.addData("color level 2", color2);
        telemetry.addData("color level 3", camInput.color3average);
        telemetry.addData("Pipeline time ms", camInput.webcam.getPipelineTimeMs());
        telemetry.update();

        camInput.webcam.pauseViewport();// Pause image for processing
        Drivetrain.encoderForward(34);
        sleep(500);
        Drivetrain.encoderStrafe(36);
        sleep(500);
        Drivetrain.encoderTurn(40);
        sleep(500);

        Arm.armHigh();
        sleep(2000);

        Drivetrain.moveForwardManual(0.4);
        sleep(100);
        Drivetrain.stop();

        Arm.wristScore();
        sleep(1000);
        Arm.openClaw();
        sleep(1000);
        Arm.wristIn();

        Drivetrain.encoderTurn(-40);
        sleep(500);

        Arm.armFloor();
        sleep(500);
        Arm.closeClaw();
        sleep(500);
        Arm.wristIn();
        if (locSignal == 2){
            Drivetrain.encoderStrafe(-36);
            sleep(1000);
        }
        else if (locSignal == 1) {
            Drivetrain.encoderStrafe(-64);
            sleep(1000);
        }
        else if (locSignal == 3) {
            Drivetrain.stop();
        }


        //scan
        Drivetrain.stop();


    }
}
