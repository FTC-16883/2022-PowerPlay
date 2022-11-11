
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
 * Updates 11/10: Scan and Park only functionality in Autonomous Mode (SM)
 */

@Autonomous(name="AutonBaseScanToParkRRBL", group="Android Studio")
public class AutonBaseScanToParkRRBL extends LinearOpMode
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
        camInput.xCordCam = 120;

        Drivetrain.init(leftFront, rightFront, leftRear, rightRear);

        armLeft = hardwareMap.get(DcMotorEx.class, "armLeft");
        armRight = hardwareMap.get(DcMotorEx.class, "armRight");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");

        Arm.initAuton(armRight, armLeft, claw, wrist);
        Arm.closeClaw();
        sleep(500);
        Arm.wristIn();
        sleep(1000);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        //grip cone for autonomous


        //Siddharth M Trials:
        if ((camInput.color1average>150)) {
            locSignal = 3;
            telemetry.addData("Detected color is blue :", 3);
        } else if ((camInput.color2average>90)&&(camInput.color2average<110)) {
            locSignal = 1;
            telemetry.addData("Detected color is green :", 1);
        }
        else if ((camInput.color2average>125)&&(camInput.color2average<140)){
            locSignal = 2;
            telemetry.addData("Detected color is yellow :", 2);
        }

        telemetry.addData("color level 1", camInput.color1average);
        telemetry.addData("color level 2", camInput.color2average);
        telemetry.addData("color level 3", camInput.color3average);
        telemetry.addData("Pipeline time ms", camInput.webcam.getPipelineTimeMs());
        telemetry.update();

        camInput.webcam.pauseViewport();// Pause image for processing
        Drivetrain.encoderForward(28);
        sleep(500);

        if (locSignal == 2){
            sleep(1000);
        }
        else if (locSignal == 1) {
            Drivetrain.encoderStrafe(-30);
            sleep(1000);
        }
        else if (locSignal == 3) {
            Drivetrain.encoderStrafe(30);
            sleep(1000);
        }


        //scan
        Drivetrain.stop();


    }
}