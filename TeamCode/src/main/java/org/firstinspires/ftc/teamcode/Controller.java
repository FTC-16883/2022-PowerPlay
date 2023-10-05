package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Drivetrain;

@TeleOp(name="TeleOp", group="Android Studio")
public class Controller extends LinearOpMode {
    // Declare every variable being used in the program here.
    private ElapsedTime runtime = new ElapsedTime();
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;
    public static DcMotorEx leftRobotArm;
    public static DcMotorEx rightRobotArm;
    public static DcMotorEx armExtender;
    public static DcMotorEx droneLauncher;


    

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftRobotArm = hardwareMap.get(DcMotorEx.class, "leftRobotArm");
        leftRobotArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRobotArm = hardwareMap.get(DcMotorEx.class, "rightRobotArm");
        rightRobotArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armExtender = hardwareMap.get(DcMotorEx.class, "armExtender");
        droneLauncher = hardwareMap.get(DcMotorEx.class, "droneLauncher");

        Drivetrain.init(leftFront, rightFront, leftRear, rightRear);

        waitForStart();

        while (opModeIsActive()) {

            leftFront.setPower((-gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            rightFront.setPower((gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            leftRear.setPower((gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (-gamepad1.left_stick_x));
            rightRear.setPower((gamepad1.left_stick_y) + (-gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            if (gamepad1.triangle) {
                droneLauncher.setPower(1);
            } else {
                droneLauncher.setPower(0);
            }
            droneLauncher.setPower(gamepad1.left_trigger);
            leftRobotArm.setPower(gamepad2.left_stick_y);
            rightRobotArm.setPower(-gamepad2.left_stick_y);
            armExtender.setPower(-gamepad2.right_stick_y);


            //Gamepad1 sticks correlate to different functions. like strafing, turning and going forward


        }

    }
}


