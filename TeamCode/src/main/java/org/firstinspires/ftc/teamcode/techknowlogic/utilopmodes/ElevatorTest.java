package org.firstinspires.ftc.teamcode.techknowlogic.utilopmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.techknowlogic.util.Elevator;

@Autonomous
@Config
@Disabled
public class ElevatorTest extends LinearOpMode {

    public static int FLOOR_NUMBER =1;

    @Override
    public void runOpMode() throws InterruptedException {

        Elevator elevator = new Elevator(hardwareMap);

        waitForStart();

        elevator.raiseToTheLevel(FLOOR_NUMBER);

//        while (!elevator.fullyRaised) {
//            sleep(100);
//        }

        //sleep(500);

        telemetry.log().add("Raised to the level " + FLOOR_NUMBER);

        elevator.dropFreight();

        telemetry.log().add("dropped the freight");
     }
}
