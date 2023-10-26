package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.autonomous.AutonBase;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Calendar;

// code from 12 to 47 made by ved nakum ;)
public class OpenCV extends AutonBase {
    private OpenCvCamera webcam;

        private static final int CAMERA_WIDTH = 1280; // width  of wanted camera resolution
        private static final int CAMERA_HEIGHT = 720; // height of wanted camera resolution

        public static double borderLeftX = 10;   //fraction of pixels from the left side of the cam to skip
        public static double borderRightX = 10;   //fraction of pixels from the right of the cam to skip
        public static double borderTopY = 10;   //fraction of pixels from the top of the cam to skip
        public static double borderBottomY = 10;   //fraction of pixels from the bottom of the cam to skip
    private Calendar OpenCvCameraFactory;


    @Override
        public void runAutonomous () {
            // OpenCV webcam
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

            webcam.setPipeline(new SamplePipeline());

            // Webcam Streaming
            webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
            webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
             public void onError(int errorCode) {
                 /*
                 add end streaming statement here.
                 */
                                             }
                                         }
            );
        }

    private class SamplePipeline extends OpenCvPipeline {
        @Override
        public Mat processFrame(Mat input) {
            return null;
        }
    }
}

