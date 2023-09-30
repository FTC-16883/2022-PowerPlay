import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

    public static DcMotorEx droneLauncher;

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        droneLauncher = hardwareMap.get(DcMotorEx.class, "droneLauncher");

        Drivetrain.init(leftFront, rightFront, leftRear, rightRear);

        waitForStart();

        while (opModeIsActive()) {
            leftFront.setPower((-gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            rightFront.setPower((gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            leftRear.setPower((gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (-gamepad1.left_stick_x));
            rightRear.setPower((gamepad1.left_stick_y) + (-gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            droneLauncher.setPower(gamepad1.triangle);
            // Makes gamepad1 sticks correlate to different functions. like strafing, turning and going forward

        }

    }
}


