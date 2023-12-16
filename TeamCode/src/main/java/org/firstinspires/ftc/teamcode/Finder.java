package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.teamcode.Spark.Team;

import java.util.List;

/**
 * This class is for all your object identification needs
 */
public class Finder {

    // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
    // this is only used for Android Studio when using models in Assets.
    private static final String TFOD_MODEL_ASSET = "MyModelStoredAsAsset.tflite";
    // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
    // this is used when uploading models directly to the RC using the model upload interface.
    private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/myCustomModel.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "Pixel",
    };

    private LinearOpMode auton;

    private WebcamName webcamName;

    private Team team;

    private Telemetry telem;

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    private TfodProcessor tfod;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    /**
     * List of recognitions. Empty by default, but filled by the scan function.
     */
    List<Recognition> currentRecognitions;

    /**
     * An ENUM that is used to represent the possible locations for the spike mark
     */
    public enum SPIKE_MARK {
        LEFT,
        CENTER,
        RIGHT
    }

    //TODO Need to change the left divider value to a value tested on field
    private final double LEFT_DIVIDER = -10;

    //TODO Need to change the right divider value to a value tested on field
    private final double RIGHT_DIVIDER = 10;

    public Finder(LinearOpMode auton, WebcamName webcamName, Team team ) {

        this.auton = auton;

        this.telem = auton.telemetry;

        this.webcamName = webcamName;

        this.team = team;

    }

    public void init() {
        tfod = new TfodProcessor.Builder()

                // With the following lines commented out, the default TfodProcessor Builder
                // will load the default model for the season. To define a custom model to load,
                // choose one of the following:
                //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
                //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
                //.setModelAssetName(TFOD_MODEL_ASSET)
                //.setModelFileName(TFOD_MODEL_FILE)

                // The following default settings are available to un-comment and edit as needed to
                // set parameters for custom models.
                //.setModelLabels(LABELS)
                //.setIsModelTensorFlow2(true)
                //.setIsModelQuantized(true)
                //.setModelInputSize(300)
                //.setModelAspectRatio(16.0 / 9.0)

                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        builder.setCamera( webcamName );

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);
    }

    public void scan() {
        currentRecognitions = tfod.getRecognitions();
    }

    public void scanWithTelemetry() {
       scan();
        telem.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telem.addData(""," ");
            telem.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telem.addData("- Position", "%.0f / %.0f", x, y);
            telem.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }   // end for() loop
    }

    public void close() {
        visionPortal.close();
    }

    /**
     * Gets the location of the Pixel based on the LEFT and RIGHT dividers
     * @return the location of the pixel (LEFT, CENTER, or RIGHT)
     */
    public SPIKE_MARK getPixelLocation() {
        Recognition pixel = getRecognitionByName( "Pixel" );

        //if pixel is not found, return null
        if ( pixel == null ) return null;

        //Now that we know a pixel was identified, let's calculate its x value
        double x = (pixel.getLeft() + pixel.getRight()) / 2 ;

        if ( x < LEFT_DIVIDER ) {

            return SPIKE_MARK.LEFT;

        } else if ( x > RIGHT_DIVIDER ) {

            return SPIKE_MARK.RIGHT;

        }

        return SPIKE_MARK.CENTER;

    }

    /**
     * Returns the first recognition with the name provided
     * @param name the name to look for inside of recognitions
     * @return the recognition that was found
     */
    private Recognition getRecognitionByName(String name ) {

        // Loop through the detections
        for ( Recognition recognition : currentRecognitions ) {

            //If the name given is equal to the label for the detection, then return that detection
            if ( name.equals( recognition.getLabel() ) ) {
                return recognition;
            }

        }

        return null;
    }

}
