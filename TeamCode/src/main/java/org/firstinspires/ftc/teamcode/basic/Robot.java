package org.firstinspires.ftc.teamcode.basic;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
/*
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
 */
public class Robot {
    private DcMotor FrontLftMtrAsDcMotor;
    private DcMotor RearRghMtrAsDcMotor;
    private DcMotor RearLftMtrAsDcMotor;
    private DcMotor FrontRgtMtrAsDcMotor;
    private DcMotor SldMtrAsDcMotor;
    private Servo BlkPckSrvAsServo;
    public HardwareMap hardwareMap = null;
    //Sensor Variables
    private ColorSensor SensClrRngAsREVColorRangeSensor;

    //Gyro sensor Variables
    BNO055IMU   imu;
    Orientation lastAngles = new Orientation();
    double      globalAngle, correction;


    // Constants
    private int iMotorCountsPerInch;
    private double fWheelDiameter;
    private double fTicksperInch;
    private double fGearRatio;

    private int iLastDriveTrainTick;
    private boolean bMotor_BusyState;
    private boolean bTorchlight;
    public int iServoPosition;
    private int iServoSpeed;
    public  OpMode opMode;
    /*
    Function Name	: Initialize
    Author 		    : Rishi
    Description	    : Object constructor. Initialize global constants
    Parameters		: None
    Returns		    : None
    */
    public void Initialize() throws InterruptedException {
        // Check the Motor Specification
        iMotorCountsPerInch = 1440;

        //Check the Wheel Specification

        fWheelDiameter = 3.85;

        //Motor to wheel gear ratio
        // 80:40 = 40/80 = 0.5
        // 80 tooth on Motor and 20 tooth on wheel

        fGearRatio = 0.5;

        // To calculate the distance travel we use the below formula
        // ( iMotorCountsPerInch * fGearRatio ) / ( fWheelDiameter * 3.1415 )

        fTicksperInch = Math.abs(( iMotorCountsPerInch * fGearRatio ) / ( fWheelDiameter * 3.1415 ));


        //Define Drive Train Motors
        // TODO: Rename to your config
        FrontLftMtrAsDcMotor = hardwareMap.dcMotor.get("FrontLftMtrAsDcMotor");
        FrontRgtMtrAsDcMotor = hardwareMap.dcMotor.get("FrontRgtMtrAsDcMotor");
        RearLftMtrAsDcMotor = hardwareMap.dcMotor.get("RearLftMtrAsDcMotor");
        RearRghMtrAsDcMotor = hardwareMap.dcMotor.get("RearRghMtrAsDcMotor");
        //Set Direction to reverse for Right side motors to match the direction of the left side motors.
        /*FrontLftMtrAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRgtMtrAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RearLftMtrAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RearRghMtrAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
*/
        FrontRgtMtrAsDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RearRghMtrAsDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //Define Arm Slider Motor

        SldMtrAsDcMotor = hardwareMap.dcMotor.get("SldMtrAsDcMotor");

        //Define Brick Servo Motor
        BlkPckSrvAsServo = hardwareMap.servo.get("BlkPckSrvAsServo");

        //Define Color Sensor identify the brige Marker

        //SensClrRngAsREVColorRangeSensor = hardwareMap.colorSensor.get("SensClrRngAsREVColorRangeSensor");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        opMode.telemetry.addData("Mode", "calibrating...");
        opMode.telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while ( !imu.isGyroCalibrated()) //isStopRequested() &&
        {
            Thread.sleep(50);
        }

    }


    public void DriveTrainMotor_Init_Encoder() {
        FrontLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRgtMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRghMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void DriveTrainMotor_Reset_Encoder() {

        FrontLftMtrAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRgtMtrAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLftMtrAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRghMtrAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void DriveTrainMotor_SetRun2Pos() {
        FrontLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRgtMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRghMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void DriveTrainMotors_SettoZeroPower(int iPower) {
        FrontLftMtrAsDcMotor.setPower(iPower);
        FrontRgtMtrAsDcMotor.setPower(iPower);
        RearLftMtrAsDcMotor.setPower(iPower);
        RearRghMtrAsDcMotor.setPower(iPower);
    }


    public boolean CheckDriveMotorBusyState(int iDeltaTicks) {
        if (FrontLftMtrAsDcMotor.isBusy() ||
                FrontRgtMtrAsDcMotor.isBusy() ||
                RearLftMtrAsDcMotor.isBusy() ||
                RearRghMtrAsDcMotor.isBusy()) {

            if (Math.abs(RearRghMtrAsDcMotor.getCurrentPosition()) >= iLastDriveTrainTick - iDeltaTicks) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void WaitForDriveTrainToComplete(int iDeltaTicks) {

        do
        {
            bMotor_BusyState = CheckDriveMotorBusyState(iDeltaTicks);

            /*
            telemetry.addData("FrontLeftMtr : ", Math.abs(FrontLftMtrAsDcMotor.getCurrentPosition()));
            telemetry.addData("FrontRightMtr : ", Math.abs(FrontRgtMtrAsDcMotor.getCurrentPosition()));
            telemetry.addData("MotorState : ", bMotor_BusyState);
            telemetry.update();
            */
        }while (bMotor_BusyState);

        DriveTrainMotor_Reset_Encoder();
        DriveTrainMotors_SettoZeroPower(0);
        DriveTrainMotor_SetRun2Pos();
    }


    public void MoveBot_ForwardUsingGyro( double dPower,
                                          int iDistanceInch,
                                          int iDeltaTicks)
    {
        int iTicks = (int) Math.round( (iDistanceInch * fTicksperInch) + iDeltaTicks );
        iLastDriveTrainTick = iTicks;

        DriveTrainMotor_Init_Encoder();

        int startDistance = FrontLftMtrAsDcMotor.getCurrentPosition();

        // Use gyro to drive in a straight line.
        correction = checkDirection();
        opMode.telemetry.addData("front motor start Position ", startDistance);
        opMode.telemetry.addData("1 imu heading ", lastAngles.firstAngle);
        opMode.telemetry.addData("2 global heading ", globalAngle);
        opMode.telemetry.addData("3 correction ", correction);
        opMode.telemetry.update();

        while (Math.abs(FrontLftMtrAsDcMotor.getCurrentPosition() - startDistance) < Math.abs(iDistanceInch)) {

            correction = checkDirection();
            opMode.telemetry.addData("front motor start Position ", startDistance);
            opMode.telemetry.addData("1 imu heading ", lastAngles.firstAngle);
            opMode.telemetry.addData("2 global heading ", globalAngle);
            opMode.telemetry.addData("3 correction ", correction);
            opMode.telemetry.update();

            RearLftMtrAsDcMotor.setPower(Range.clip(dPower + correction, -1, 1));
            FrontLftMtrAsDcMotor.setPower(Range.clip(dPower - correction, -1, 1));
            RearRghMtrAsDcMotor.setPower(Range.clip(-dPower - correction, -1, 1));
            FrontRgtMtrAsDcMotor.setPower(Range.clip(-dPower + correction, -1, 1));
            //dispEncoders();
        }
    }



    public void MoveBot_Forward(double fFrontLeftPower,
                                double fFrontRightPower,
                                double fRearLeftPower,
                                double fRearRightPower,
                                int iDistanceInch,
                                int iDeltaTicks) {

        int iTicks = (int) Math.round( (iDistanceInch * fTicksperInch) + iDeltaTicks );
        iLastDriveTrainTick = iTicks;

        // Reset motor mode to run to position;
        //DriveTrainMotor_SetRun2Pos();

        FrontLftMtrAsDcMotor.setTargetPosition(iTicks);
        FrontRgtMtrAsDcMotor.setTargetPosition(iTicks);
        RearLftMtrAsDcMotor.setTargetPosition(iTicks);
        RearRghMtrAsDcMotor.setTargetPosition(iTicks);

        FrontLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRgtMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRghMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontLftMtrAsDcMotor.setPower(fFrontLeftPower);
        FrontRgtMtrAsDcMotor.setPower(fFrontRightPower);
        RearLftMtrAsDcMotor.setPower(fRearLeftPower);
        RearRghMtrAsDcMotor.setPower(fRearRightPower);
        WaitForDriveTrainToComplete(20);
    }

    public void MoveBot_Back(double fFrontLeftPower,
                             double fFrontRightPower,
                             double fRearLeftPower,
                             double fRearRightPower,
                             int fDistanceInch,
                             int iDeltaTicks) {

        int iTicks = (int)Math.round( (fDistanceInch * fTicksperInch) + iDeltaTicks );
        iLastDriveTrainTick = iTicks;

        RearLftMtrAsDcMotor.setTargetPosition(-iTicks);
        RearRghMtrAsDcMotor.setTargetPosition(-iTicks);
        FrontLftMtrAsDcMotor.setTargetPosition(-iTicks);
        FrontRgtMtrAsDcMotor.setTargetPosition(-iTicks);

        // Reset motor mode to run to position;
        DriveTrainMotor_SetRun2Pos();

        FrontLftMtrAsDcMotor.setPower(fFrontLeftPower);
        FrontRgtMtrAsDcMotor.setPower(fFrontRightPower);
        RearLftMtrAsDcMotor.setPower(fRearLeftPower);
        RearRghMtrAsDcMotor.setPower(fRearRightPower);

        WaitForDriveTrainToComplete(20);
    }



    public void MoveBot_Left(double fFrontLeftPower,
                             double fFrontRightPower,
                             double fRearLeftPower,
                             double fRearRightPower,
                             int iDistanceInch,
                             int iDeltaTicks) {
        // Need to identify the formula for Strafing.
        int iTicks = (int)Math.abs( (iDistanceInch * fTicksperInch) + iDeltaTicks );

        iLastDriveTrainTick = iTicks;

        FrontLftMtrAsDcMotor.setTargetPosition(-iTicks);
        RearLftMtrAsDcMotor.setTargetPosition(iTicks);
        FrontRgtMtrAsDcMotor.setTargetPosition(iTicks);
        RearRghMtrAsDcMotor.setTargetPosition(-iTicks);

        FrontLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLftMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRgtMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRghMtrAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        RearLftMtrAsDcMotor.setPower(fRearLeftPower);
        FrontLftMtrAsDcMotor.setPower(fFrontLeftPower);
        FrontRgtMtrAsDcMotor.setPower(fFrontRightPower);
        RearRghMtrAsDcMotor.setPower(fRearRightPower);





        WaitForDriveTrainToComplete(20);
    }


    public void MoveBot_Right(double fFrontLeftPower,
                              double fFrontRightPower,
                              double fRearLeftPower,
                              double fRearRightPower,
                              int iDistanceInch,
                              int iDeltaTicks) {

        // Need to identify the formula for Strafing.
        int iTicks = (int)Math.abs( (iDistanceInch * fTicksperInch) + iDeltaTicks );

        iLastDriveTrainTick = iTicks;


        FrontRgtMtrAsDcMotor.setTargetPosition(-iTicks);
        RearRghMtrAsDcMotor.setTargetPosition(iTicks);
        FrontLftMtrAsDcMotor.setTargetPosition(iTicks);
        RearLftMtrAsDcMotor.setTargetPosition(-iTicks);

        // Reset motor mode to run to position;
        DriveTrainMotor_SetRun2Pos();

        FrontRgtMtrAsDcMotor.setPower(fFrontRightPower);
        RearRghMtrAsDcMotor.setPower(fRearRightPower);
        RearLftMtrAsDcMotor.setPower(fRearLeftPower);
        FrontLftMtrAsDcMotor.setPower(fFrontLeftPower);

        WaitForDriveTrainToComplete(20);
    }


    public void Bot_Drive(float fFrontLeftPower,
                          float fFrontRightPower,
                          float fRearLeftPower,
                          float fRearRightPower) {
        FrontLftMtrAsDcMotor.setPower(fFrontLeftPower);
        FrontRgtMtrAsDcMotor.setPower(fFrontRightPower);
        RearLftMtrAsDcMotor.setPower(fRearLeftPower);
        RearRghMtrAsDcMotor.setPower(fRearRightPower);
    }



// TODO: add other functions your robot might need


    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }


    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }


    private double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.


        correction = correction * gain;

        return correction;
    }
    /*

     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     * @param degrees Degrees to turn, + is left - is right

    private void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        // set power to rotate.
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getAngle() == 0) {}

            while (opModeIsActive() && getAngle() > degrees) {}
        }
        else    // left turn.
            while (opModeIsActive() && getAngle() < degrees) {}

        // turn the motors off.
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }
     */
}


