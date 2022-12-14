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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file is a LinearOpMode, A Operation Mode that runs line by Line
 * When deployed, this class should appear in the "Autonomous" dropdown menu in alphabetical order
 * When the class is selected, the classes is loaded with all the code before the "runOpMode" method
 * After the "INIT" button is pressed, all the code before the "waitForStart()" function is ran
 * After the "PLAY" button is pressed, all the code after the "waitForStart()" function is ran
 */

@TeleOp(name="Motor Testing", group="Android Studio Testing")
public class TestingRoutine extends LinearOpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public static DcMotorEx leftFront;
    public static DcMotorEx rightFront;
    public static DcMotorEx leftRear;
    public static DcMotorEx rightRear;
    public static DcMotorEx armRight;
    public static DcMotorEx armLeft;
    public static Servo claw;
    public static Servo wrist;
    public static double armPower;
    public static double wristPosition;

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

        Arm.init(armLeft, armRight, claw, wrist);

        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            leftFront.setPower((-gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            rightFront.setPower((-gamepad1.left_stick_y) + (-gamepad1.right_stick_x) + (-gamepad1.left_stick_x));
            leftRear.setPower((-gamepad1.left_stick_y) + (-gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            rightRear.setPower((-gamepad1.left_stick_y) + (gamepad1.right_stick_x) + (-gamepad1.left_stick_x));

            telemetry.addData("arm left Encoder", armLeft.getCurrentPosition());
            telemetry.addData("arm right Encoder", armRight.getCurrentPosition());
            telemetry.addData("Claw Position", claw.getPosition());
            telemetry.update();

            if (gamepad2.triangle) {
                Arm.wristScore();
            }

            if (gamepad2.circle) {
                Arm.wristLevel();
            }

            if (gamepad2.cross) {
                Arm.wristIn();
            }

            if (gamepad2.right_bumper) {
                Arm.closeClaw();
            }
            
            if (gamepad2.left_bumper) {
                Arm.openClaw();
            }

            if (gamepad2.dpad_up) {
                Arm.armHigh();
            }

            if (gamepad2.dpad_down) {
                Arm.armFloor();
            }

            if (gamepad2.dpad_left) {
                Arm.armLow();
            }

            if (gamepad2.dpad_right) {
                Arm.armMedium();
            }

            if (gamepad2.square) {
                Arm.upsideDown();
            }
        }

    }
}
