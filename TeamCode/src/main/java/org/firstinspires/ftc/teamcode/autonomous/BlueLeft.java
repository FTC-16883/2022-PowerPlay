package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Drivetrain;

public class BlueLeft {@Autonomous(name="BlueLeft", group="Android Studio")
public static class RedRight extends LinearOpMode
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

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        Drivetrain.init(leftFront, rightFront, leftRear, rightRear);

        armLeft = hardwareMap.get(DcMotorEx.class, "armLeft");
        armRight = hardwareMap.get(DcMotorEx.class, "armRight");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");

        Arm.init(armRight, armLeft, claw, wrist);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        Drivetrain.encoderForward(73);
        Drivetrain.encoderTurn(90);
        Arm.armHigh();
        Arm.openClaw();
        sleep(2000);

        Drivetrain.encoderTurn(90);
        Drivetrain.encoderForward(36);
        sleep(1000);

        boolean one;
        {
            Drivetrain.encoderStrafe(24);
            sleep(1000);

        }
        boolean two;
        {
            sleep(1000);
        }

        boolean three;
        {
            if (three = true) ;
            Drivetrain.encoderStrafe(-24);
            sleep(1000);

        }

    }
}

}
