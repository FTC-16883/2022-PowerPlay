package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain {
    DcMotor leftRear;
    DcMotor rightRear;

    public Drivetrain() {
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");
    }

    public void forward(boolean forward) {
        if (forward == true) {
            leftRear.setPower(1);
            rightRear.setPower(1);
        } else if (forward == false) {
            leftRear.setPower(-1);
            rightRear.setPower(-1);
        }
    }

    public void turning(boolean left) {
        if (left == true) {
            leftRear.setPower(-1);
            rightRear.setPower(1);
        } else if (left == false) {
            leftRear.setPower(1);
            rightRear.setPower(-1);
        }
    }

    public void stop() {
        leftRear.setPower(0);
        rightRear.setPower(0);
    }
}
