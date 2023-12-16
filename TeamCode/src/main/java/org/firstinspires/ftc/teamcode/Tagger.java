package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.teamcode.Spark.Team;

import java.util.List;

/**
 * This class is for all your AprilTagging needs. Hopefully.
 */
public class Tagger {

    /**
     * The auton file that is being used by this Tagger
     */
    private LinearOpMode auton;

    private Telemetry telem;

    private AprilTagProcessor aprilTagProcessor;

    private VisionPortal visionPortal;

    private List<AprilTagDetection> currentDetections;

    private WebcamName webcamName;

    private Team team;

    /**
     * The integer value of the AprilTag ID for the Blue Left April Tag
     */
    private final int BLUE_LEFT = 1;

    /**
     * The integer value of the AprilTag ID for the Blue Center April Tag
     */
    private final int BLUE_CENTER = 2;

    /**
     * The integer value of the AprilTag ID for the Blue Right April Tag
     */
    private final int BLUE_RIGHT = 3;

    /**
     * The integer value of the AprilTag ID for the Red Left April Tag
     */
    private final int RED_LEFT = 4;

    /**
     * The integer value of the AprilTag ID for the Red Center April Tag
     */
    private final int RED_CENTER = 5;

    /**
     * The integer value of the AprilTag ID for the Red Right April Tag
     */
    private final int RED_RIGHT = 6;

    /**
     * Constructs a Tagger object using the auton object that is passed through.
     * Allows easily passing through variables like "runtime" and "isOpModeStopped"
     * @param auton the auton that is running Tagger
     */
    public Tagger( LinearOpMode auton, WebcamName webcamName, Team team ) {

        this.auton = auton;

        this.telem = auton.telemetry;

        this.webcamName = webcamName;

        this.team = team;

    }

    public void init() {
        //Initialize the AprilTag Detection
        aprilTagProcessor = new AprilTagProcessor.Builder()
                //.setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)

                // ... these parameters are fx, fy, cx, cy.

                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        builder.setCamera(webcamName);

        // Set and enable the processor.
        builder.addProcessor(aprilTagProcessor);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();
    }

    public void close() {
        visionPortal.close();
    }

    public void scanWithTelemetry() {

        scan();
        telem.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telem.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telem.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telem.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telem.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                telem.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telem.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telem.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telem.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telem.addLine("RBE = Range, Bearing & Elevation");

    }   // end method telemetry AprilTag()

    //Create functions to scan, check if hasleft, mid, right, etc

    /**
     * Make april tag detections readable. This is intended to be run in a loop.
     * Can also take a single frame.
     */
    public void scan() {

        currentDetections = aprilTagProcessor.getDetections();

    }

    public List<AprilTagDetection> getCurrentDetections() {
        return currentDetections;
    }

    /**
     * Checks if left was detected on the last read frame.
     * @return true if left was detected, false if it was not.
     */
    public boolean leftDetected() {

        // Loop through the detections and see if the id is present
        for (AprilTagDetection detection : currentDetections) {
            if ( detection.id == getLeftId() ) {
                return true;
            }
        }
        // If the id was not present, then return false
        return false;
    }

    /**
     * Checks if center was detected on the last read frame.
     * @return true if center was detected, false if it was not.
     */
    public boolean centerDetected() {

        // Loop through the detections and see if the id is present
        for (AprilTagDetection detection : currentDetections) {
            if ( detection.id == getCenterId() ) {
                return true;
            }
        }
        // If the id was not present, then return false
        return false;
    }

    /**
     * Checks if right was detected on the last read frame.
     * @return true if right was detected, false if it was not.
     */
    public boolean rightDetected() {

        // Loop through the detections and see if the id is present
        for (AprilTagDetection detection : currentDetections) {
            if ( detection.id == getRightId() ) {
                return true;
            }
        }
        // If the id was not present, then return false
        return false;
    }

    /**
     * Gets the locational data of the left april tag, if it is detected.
     * @return the ftcpose value for the detection
     */
    public AprilTagPoseFtc getLeftLocation() {
        AprilTagDetection detection = getDetectionById( getLeftId() );

        if ( detection == null ) {
            return null;
        } else {
            return detection.ftcPose;
        }
    }

    /**
     * Gets the locational data of the center april tag, if it is detected.
     * @return the ftcpose value for the detection
     */
    public AprilTagPoseFtc getCenterLocation() {
        AprilTagDetection detection = getDetectionById( getCenterId() );

        if ( detection == null ) {
            return null;
        } else {
            return detection.ftcPose;
        }
    }

    /**
     * Gets the locational data of the right april tag, if it is detected.
     * @return the ftcpose value for the detection
     */
    public AprilTagPoseFtc getRightLocation() {
        AprilTagDetection detection = getDetectionById( getRightId() );

        if ( detection == null ) {
            return null;
        } else {
            return detection.ftcPose;
        }
    }

    /**
     * Gets the ID of the left april tag based on the team, RED or BLUE
     * @return the id of the left april tag
     */
    private int getLeftId() {
        int left = BLUE_LEFT;

        if ( team == Team.RED) {
            left = RED_LEFT;
        }
        return left;
    }

    /**
     * Gets the ID of the center april tag based on the team, RED or BLUE
     * @return the id of the center april tag
     */
    private int getCenterId() {

        // Set the id of the center april tag depending on which team the auton is for
        int center = BLUE_CENTER;

        if ( team == Team.RED) {
            center = RED_CENTER;
        }
        return center;
    }

    /**
     * Gets the ID of the right april tag based on the team, RED or BLUE
     * @return the id of the right april tag
     */
    private int getRightId() {

        // Set the id of the right april tag depending on which team the auton is for
        int right = BLUE_RIGHT;

        if ( team == Team.RED) {
            right = RED_RIGHT;
        }
        return right;
    }

    /**
     * Returns the first detection found with the same ID
     * @param id the id to search for
     * @return the first detection found with the given ID
     */
    private AprilTagDetection getDetectionById( int id ) {
        for (AprilTagDetection detection : currentDetections) {
            if ( detection.id == id ) {
                return detection;
            }
        }
        return null;
    }



}
