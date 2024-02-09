package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Finder;
import org.firstinspires.ftc.teamcode.Spark;
@Autonomous(name="FinderDemoAuton")
@Disabled
public class FinderDemoAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Finder finder;

    private Spark.Team team = Spark.Team.BLUE;

    private Finder.SPIKE_MARK pixelLocation;


    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        finder = new Finder( this, robot.webcamName, team);
        runtime.reset();

        finder.init();

        waitForStart();

        //Run until the opmode stops, or until you find the pixel and calculate its location.

        pixelLocation = finder.getPixelLocation();

        while ( opModeIsActive() && pixelLocation == null ) {

            pixelLocation = finder.getPixelLocation();
            //This updates the array of current detections inside tagger.
            finder.scanWithTelemetry();

            telemetry.addData("Pixel Spike Location: ", pixelLocation);

            telemetry.update();

            // Share the CPU.
            sleep(20);

        }

        //Now you know the pixel... so you can make a switch statement to process it
        switch( pixelLocation ) {
            case LEFT:
                //Do these things if the pixel is on the left mark...
                //Note: You may find it helpful to create a function called moveToPixelBoard()
                //And place your robot.moveLeftInches, etc. inside of that function.
                //That way you do not have to rewrite the code for each switch case!
                break;
            case CENTER:
                //Do these things if the pixel is on the center mark...
                break;
            case RIGHT:
                //Do these things if the pixel is on the right mark...
                break;

        }


        // Close out tagger.
        finder.close();

    }

}