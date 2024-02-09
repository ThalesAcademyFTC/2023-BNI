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
        robot.closeClawL();
        sleep(20);
        robot.moveForwardInches(28, speed);

        //Now you know the pixel... so you can make a switch statement to process it
        switch( pixelLocation ) {
            case LEFT:
                robot.turnLeftDegrees(90,speed);
                robot.moveForwardInches(5,speed);
                robot.openClawL();

                break;
            case CENTER:
                robot.moveForwardInches(5,speed);
                robot.openClawL();

                break;
            case RIGHT:
                robot.turnRightDegrees(90,speed);
                robot.moveForwardInches(5,speed);
                robot.openClawL();

                break;

        }
       robot.openClawL();

        // Close out tagger.
        finder.close();

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}