package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RedPlacePurple")
public class RedPlacePurple extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Finder finder;

    private Spark.Team team = Spark.Team.RED;

    private Finder.SPIKE_MARK pixelLocation;

    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.TEST);

        finder = new Finder(this, robot.webcamName, team);
        runtime.reset();

        robot.liftHook();

        waitForStart();

        //variables for auton

        double speed = 0.5;
        int rest = 100;
        //more code needed where indicate
        //robot scans for the spike mark, and places purple pixel
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
        robot.moveForwardInches(28, speed);

        //Now you know the pixel... so you can make a switch statement to process it
        switch( pixelLocation ) {
            case LEFT:
                robot.turnRightDegrees(90,speed);

                break;
            case CENTER:
                robot.turnLeftDegrees(180,speed);

                break;
            case RIGHT:
                robot.turnLeftDegrees(90,speed);
                break;

        }
       //robot.dropHook();

        // Close out tagger.
        finder.close();

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}