package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@Autonomous(name="RedPlacePurple")
public class RedPlacePurple extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Finder finder;

    private Spark.Team team = Spark.Team.RED;

    private Finder.SPIKE_MARK propLocation;

    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        finder = new Finder(this, robot.webcamName, team);
        runtime.reset();

        finder.init();



        waitForStart();

        //variables for auton

        double speed = 0.25;
        int rest = 100;
        //more code needed where indicate
        //robot scans for the spike mark, and places purple pixel
        propLocation = null;

        while ( opModeIsActive() && propLocation == null ) {

            finder.scanWithTelemetry();

            propLocation = finder.getPropLocation();
            //This updates the array of current detections inside tagger

            telemetry.addData("Pixel Spike Location: ", propLocation);

            telemetry.update();

            // Share the CPU.

            sleep(20);

        }
        robot.closeClawL();
        sleep(20);
        robot.moveForwardInches(25, speed);

        //Now you know the pixel... so you can make a switch statement to process it
        switch( propLocation ) {
            case LEFT:
                robot.turnLeftDegrees(90,speed);
                sleep(20);
                robot.moveForwardInches(8,speed);
                sleep(20);

                break;
            case CENTER:
                robot.moveForwardInches(5,speed);
                sleep(20);

                break;
            case RIGHT:
                robot.turnRightDegrees(90,speed);
                sleep(20);
                robot.moveForwardInches(5,speed);
                sleep(20);

                break;

        }
       robot.openClawL();

        robot.moveBackwardInches(5, speed);
        // Close out tagger.
        finder.close();

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}