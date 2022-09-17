package org.firstinspires.ftc.teamcode.techknowlogic.utilopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.techknowlogic.util.CarousalSpinner;

@Autonomous
@Disabled
public class CarousalSpinnerTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        CarousalSpinner spinner = new CarousalSpinner(hardwareMap);

        waitForStart();

        spinner.spin(true);

    }
}
