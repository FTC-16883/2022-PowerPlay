package org.firstinspires.ftc.teamcode.techknowlogic.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CarousalSpinner {

    private DcMotor spinner = null;

    public CarousalSpinner(HardwareMap hardwareMap) {
        super();
        this.spinner = hardwareMap.get(DcMotor.class, "spinner");
    }

    public void spin(boolean reversed) {

        //Spinner REV Ultra Planitary is on control hub -- port 3

        if(reversed) {
            spinner.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        //spin for 3 seconds at full speed
        spinner.setPower(1.0);
        sleep(3000);
    }

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
