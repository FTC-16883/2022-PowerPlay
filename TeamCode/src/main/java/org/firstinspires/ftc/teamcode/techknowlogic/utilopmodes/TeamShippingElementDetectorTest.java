package org.firstinspires.ftc.teamcode.techknowlogic.utilopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.techknowlogic.util.TeamShippingElementDetector;

@Autonomous

public class TeamShippingElementDetectorTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        TeamShippingElementDetector detector = new TeamShippingElementDetector(hardwareMap, telemetry);

        //Detection continue to happen throughout init
        detector.startDetection();

        waitForStart();

        long start = System.currentTimeMillis();

        //As detection continue to happen since init, we can stop detection (stop streaming)
        detector.stopDetection();

        //Step-1 : Scan for duck or Team Shipping Element
        String shippingElementPosition = detector.getElementPosition();
        telemetry.log().add("team shipping element position " + shippingElementPosition);

        long end = System.currentTimeMillis();

        long elapsed = end-start;

        telemetry.log().add("Time took to scan " + elapsed);

        telemetry.log().add("Duck is in " + shippingElementPosition);

        while(opModeIsActive()) {
            sleep(100);
        }
    }
}
