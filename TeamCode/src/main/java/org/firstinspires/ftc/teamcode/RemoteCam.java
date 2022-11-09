/*
 * Copyright (c) 2019 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;


import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class RemoteCam {
    public static OpenCvWebcam webcam;
    public static int location;
    public static double color1average;
    public static double color2average;
    public static double color3average;
    public static int xCordCam = 140;
    public static int yCordCam = 90;
    public static int xCamWindow = 40;
    public static int yCamWindow = 60;
    public static int locSignalProc1, locsignalProc2, locSignal;
    public static double loc1Percent, loc2Percent, loc3Percent;
    public static double gPercent, bPercent, rPercent;
    public enum ParkingPosition {
        LEFT,
        CENTER,
        RIGHT
    }
    public static ParkingPosition position;
    // Percent and mat definitions
    public static double redPercent, greenPercent, bluePercent;

    public RemoteCam() {
    }

    public static void init(OpenCvWebcam frontCamera) {
        /*
         * Specify the image processing pipeline we wish to invoke upon receipt
         * of a frame from the camera. Note that switching pipelines on-the-fly
         * (while a streaming session is in flight) *IS* supported.
         */
        RemoteCam.webcam = frontCamera;
        //RemoteCam.webcam.setPipeline(new SamplePipeline());
        locSignal = 0; //Set to value other than Location 1 /2/3
        location=0;
        RemoteCam.webcam.setPipeline(new SamplePipeline());
        /*
         * Open the connection to the camera device. New in v1.4.0 is the ability
         * to open the camera asynchronously, and this is now the recommended way
         * to do it. The benefits of opening async include faster init time, and
         * better behavior when pressing stop during init (i.e. less of a chance
         * of tripping the stuck watchdog)
         *
         * If you really want to open synchronously, the old method is still available.
         */
        frontCamera.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        frontCamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                frontCamera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

    }


    /*
     * An example image processing pipeline to be run upon receipt of each frame from the camera.
     * Note that the processFrame() method is called serially from the frame worker thread -
     * that is, a new camera frame will not come in while you're still processing a previous one.
     * In other words, the processFrame() method will never be called multiple times simultaneously.
     *
     * However, the rendering of your processed image to the viewport is done in parallel to the
     * frame worker thread. That is, the amount of time it takes to render the image to the
     * viewport does NOT impact the amount of frames per second that your pipeline can process.
     *
     * IMPORTANT NOTE: this pipeline is NOT invoked on your OpMode thread. It is invoked on the
     * frame worker thread. This should not be a problem in the vast majority of cases. However,
     * if you're doing something weird where you do need it synchronized with your OpMode thread,
     * then you will need to account for that accordingly.
     */
    public static class SamplePipeline extends OpenCvPipeline {
        boolean viewportPaused;

        /*
         * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
         * highly recommended to declare them here as instance variables and re-use them for
         * each invocation of processFrame(), rather than declaring them as new local variables
         * each time through processFrame(). This removes the danger of causing a memory leak
         * by forgetting to call mat.release(), and it also reduces memory pressure by not
         * constantly allocating and freeing large chunks of memory.
         */

        @Override
        public Mat processFrame(Mat input) {

            Mat yCbCrChan2Mat = new Mat();
            Mat output = new Mat();
            Mat loc1crop = new Mat();
            Mat loc2crop = new Mat();
            Mat loc3crop = new Mat();
            Mat baseline = new Mat();
            Mat thresholdMat = new Mat();

            //TBD from testing

            /*1108: add*/
            Rect rect1 = new Rect(xCordCam, yCordCam, xCamWindow, yCamWindow);


            /*Comparison Check 1*/

            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);
            Imgproc.rectangle(
                    input,
                    new Point(
                            xCordCam,
                            yCordCam),
                    new Point(
                            xCordCam + xCamWindow,
                            yCordCam + yCamWindow),
                    new Scalar(255, 255, 255), 4);


            loc1crop = yCbCrChan2Mat.submat(rect1);
            Core.extractChannel(loc1crop, loc1crop, 2);
            Scalar upperaverage = Core.mean(loc1crop);
            RemoteCam.color1average = upperaverage.val[0];

            loc2crop = yCbCrChan2Mat.submat(rect1);
            Core.extractChannel(loc2crop, loc2crop, 1);
            upperaverage = Core.mean(loc2crop);
            RemoteCam.color2average = upperaverage.val[0];

            loc3crop = yCbCrChan2Mat.submat(rect1);
            Core.extractChannel(loc3crop, loc3crop, 0);
            upperaverage = Core.mean(loc3crop);
            RemoteCam.color3average = upperaverage.val[0];


            /*Release video image.*/
            loc1crop.release();
            loc2crop.release();
            loc3crop.release();
            yCbCrChan2Mat.release();

            return input;
        }


        public int getSignalLocation() {
            return locSignal;
        }

        @Override
        public void onViewportTapped() {
            /*
             * The viewport (if one was specified in the constructor) can also be dynamically "paused"
             * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
             * when you need your vision pipeline running, but do not require a live preview on the
             * robot controller screen. For instance, this could be useful if you wish to see the live
             * camera preview as you are initializing your robot, but you no longer require the live
             * preview after you have finished your initialization process; pausing the viewport does
             * not stop running your pipeline.
             *
             * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
             */

            viewportPaused = !viewportPaused;

            if (viewportPaused) {
                webcam.pauseViewport();
            } else {
                webcam.resumeViewport();
            }
        }
    }

    public static class CameraSensor extends OpenCvPipeline {
    /*
    GREEN  = Parking Right
    BLUE    = Parking left
    RED = Parking Center
     */

        boolean viewportPaused;
        // TOPLEFT anchor point for the bounding box
        private static Point SLEEVE_TOPLEFT_ANCHOR_POINT = new Point(145, 80);

        // Width and height for the bounding box
        public static int REGION_WIDTH = 20;
        public static int REGION_HEIGHT = 20;

        // Lower and upper boundaries for colors
        private static final Scalar
                lower_green_bounds = new Scalar(0, 200, 0, 255),
                upper_green_bounds = new Scalar(30, 255, 30, 255),
                lower_red_bounds = new Scalar(200, 200, 0, 255),
                upper_red_bounds = new Scalar(255, 255, 30, 255),
                lower_blue_bounds = new Scalar(0, 0, 200, 255),
                upper_blue_bounds = new Scalar(30, 30, 255, 255);

        // Color definitions
        private final Scalar
                RED = new Scalar(255, 255, 0),
                GREEN = new Scalar(0, 255, 0),
                BLUE = new Scalar(0, 0, 255);

        // Percent and mat definitions
        private Mat rMat = new Mat(), gMat = new Mat(), bMat = new Mat(), blurredMat = new Mat(), kernel = new Mat();

        // Anchor point definitions
        Point sleeve_pointA = new Point(
                SLEEVE_TOPLEFT_ANCHOR_POINT.x,
                SLEEVE_TOPLEFT_ANCHOR_POINT.y);
        Point sleeve_pointB = new Point(
                SLEEVE_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                SLEEVE_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        // Running variable storing the parking position


        @Override
        public Mat processFrame(Mat input) {
            // Noise reduction

            Imgproc.rectangle(
                    input,
                    new Point(
                            SLEEVE_TOPLEFT_ANCHOR_POINT.x,
                            SLEEVE_TOPLEFT_ANCHOR_POINT.y),
                    new Point(
                            SLEEVE_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                            SLEEVE_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT),
                    new Scalar(255, 255, 255), 2);

            Imgproc.blur(input, blurredMat, new Size(5, 5));
            blurredMat = blurredMat.submat(new Rect(sleeve_pointA, sleeve_pointB));

            // Apply Morphology
            kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
            Imgproc.morphologyEx(blurredMat, blurredMat, Imgproc.MORPH_CLOSE, kernel);

            // Gets channels from given source mat
            Core.inRange(blurredMat, lower_green_bounds, upper_green_bounds, gMat);
            Core.inRange(blurredMat, lower_red_bounds, upper_red_bounds, rMat);
            Core.inRange(blurredMat, lower_blue_bounds, upper_blue_bounds, bMat);

            // Gets color specific values
           // RemoteCam.gPercent = Core.countNonZero(gMat);
           // RemoteCam.rPercent = Core.countNonZero(rMat);
           // RemoteCam.bPercent = Core.countNonZero(bMat);

             RemoteCam.rPercent = Core.countNonZero(rMat);
             RemoteCam.bPercent = Core.countNonZero(bMat);

            // Calculates the highest amount of pixels being covered on each side
            double maxPercent = Math.max(RemoteCam.gPercent, Math.max(RemoteCam.rPercent, RemoteCam.bPercent));

            // Checks all percentages, will highlight bounding box in camera preview
            // based on what color is being detected
            if (maxPercent == RemoteCam.bPercent) {
                RemoteCam.location = 3;
                Imgproc.rectangle(
                        input,
                        sleeve_pointA,
                        sleeve_pointB,
                        BLUE,
                        2
                );
            } else if (maxPercent == RemoteCam.rPercent) {
                RemoteCam.location = 2;
                Imgproc.rectangle(
                        input,
                        sleeve_pointA,
                        sleeve_pointB,
                        RED,
                        2
                );
            } else if (maxPercent == RemoteCam.gPercent) {
                RemoteCam.location = 1;
                Imgproc.rectangle(
                        input,
                        sleeve_pointA,
                        sleeve_pointB,
                        BLUE,
                        2
                );
            }

            // Memory cleanup
            blurredMat.release();
            rMat.release();
            bMat.release();
            gMat.release();
            kernel.release();

            return input;
        }
        @Override
        public void onViewportTapped() {
            /*
             * The viewport (if one was specified in the constructor) can also be dynamically "paused"
             * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
             * when you need your vision pipeline running, but do not require a live preview on the
             * robot controller screen. For instance, this could be useful if you wish to see the live
             * camera preview as you are initializing your robot, but you no longer require the live
             * preview after you have finished your initialization process; pausing the viewport does
             * not stop running your pipeline.
             *
             * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
             */

            viewportPaused = !viewportPaused;

            if (viewportPaused) {
                webcam.pauseViewport();
            } else {
                webcam.resumeViewport();
            }
        }
    }
}