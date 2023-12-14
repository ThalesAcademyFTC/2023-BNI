package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous(name="RoughLeftSideAuton")
@Disabled
public class RoughLeftSideAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    private List<AprilTagDetection> currentDetections;


    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
        runtime.reset();

        //Initialize the AprilTag Detection
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(robot.webcamName, aprilTagProcessor);

        waitForStart();
  
        double speed = 0.5;
        int rest = 100;

        /*
        //Code for auton
        robot.moveForwardInches(12 , speed);   
        //robot.sleep(rest);
        
         //code for scanning spike mark goes here
        //robot.sleep(rest);
                //code for placing purple pixel goes here
        //robot.sleep(rest);
        robot.moveBackwardInches(9, speed);    
        //robot.sleep(rest);
        
        robot.turnLeftDegrees(90, speed * 3/2);         
        //robot.sleep(rest);
            
        robot.moveForwardInches(24 , speed); 
        //robot.sleep(rest);
        
        robot.moveRightInches(24 , speed);  
        //robot.sleep(rest);
        //robot scans for the left april tag, moves forward to the correct position, and places the yellow pixel
        //more code needed where indicated

        //code for scanning april tags goes here
        //robot.sleep(rest);
        robot.moveForwardInches(6, speed);
        //robot.sleep(rest);
         //code for positioning to place yellow pixel goes here
     
        //robot.sleep(rest);

        robot.moveLeftInches(24 , speed * 3/2);
        //robot.sleep(rest);
        
        robot.moveForwardInches(18, speed);
        //robot.sleep(rest);

        */

        while (opModeIsActive() && runtime.milliseconds() > 30000){

            currentDetections = aprilTagProcessor.getDetections();
            telemetry.addData("# AprilTags Detected", currentDetections.size());

            // Step through the list of detections and display info for each one.
            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                    telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
                } else {
                    telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                    telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
                }
            }   // end for() loop

            // Add "key" information to telemetry
            telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
            telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
            telemetry.addLine("RBE = Range, Bearing & Elevation");

        }

        visionPortal.stopStreaming();

    }
}