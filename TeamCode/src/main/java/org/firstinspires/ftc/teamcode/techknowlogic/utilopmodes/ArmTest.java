package org.firstinspires.ftc.teamcode.techknowlogic.utilopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.ServiceConfigurationError;

@TeleOp
@Disabled
public class ArmTest  extends OpMode {

    Servo caExtender = null;
    //CRServo caExtender = null;
    double ca_armPosition = 0.6;
    double CANE_MIN = 0.0;
    double CANE_MAX = 1.0;
    double CAARM_INCREMENT = 0.001;

    @Override
    public void init() {
        caExtender  = hardwareMap.get(Servo.class, "caextender");
          //  caExtender = hardwareMap.get(CRServo.class, "caextender");
        //INITIAL STATE MUST BE 0.6
        caExtender.setPosition(0.6);
    }

    @Override
    public void loop() {
        if(gamepad2.dpad_up) {
            //UP POSITION
            //caExtender.setPosition(0.5);
            //caExtender.setPower(0.55);
            ca_armPosition += CAARM_INCREMENT;
        } else if(gamepad2.dpad_down) {
            //DOWN STATE
            //caExtender.setPosition(0.15);
            //caExtender.setPower(-0.45);
            ca_armPosition -= CAARM_INCREMENT;

        }

        telemetry.log().add("Position is " + caExtender.getPosition());
        telemetry.log().add("ca_armPosition is " + ca_armPosition);

        ca_armPosition = Range.clip(ca_armPosition, CANE_MIN, CANE_MAX);
        caExtender.setPosition(ca_armPosition);
    }
}
