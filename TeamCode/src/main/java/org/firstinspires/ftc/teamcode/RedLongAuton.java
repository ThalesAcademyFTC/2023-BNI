package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
//@Disabled
@Autonomous(name="RedLongAuton")
public class RedLongAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    //private Finder finder;

    private Spark.Team team = Spark.Team.RED;

    private Finder.SPIKE_MARK pixelLocation;

    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        //finder = new Finder(this, robot.webcamName, team);
        runtime.reset();

        waitForStart();

        //variables for auton

        double speed = 0.5;
        int rest = 100;
        //more code needed where indicate
        //robot scans for the spike mark, and places purple pixel
        //pixelLocation = finder.getPixelLocation();

        //while ( opModeIsActive() && pixelLocation == null ) {

            //pixelLocation = finder.getPixelLocation();
            //This updates the array of current detections inside tagger.
            //finder.scanWithTelemetry();

            //telemetry.addData("Pixel Spike Location: ", pixelLocation);

            //telemetry.update();

            // Share the CPU.
            //sleep(20);

        //}

        //Now you know the pixel... so you can make a switch statement to process it
        /*switch( pixelLocation ) {
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
        finder.close();*/

        //robot.moveForwardInches(37, speed);
        //sleep(rest);

        //robot.moveBackwardInches(34, speed);
        //sleep(rest);

        robot.turnLeftDegrees(90, speed);
        sleep(rest);

        robot.moveRightInches(48, speed);
        sleep(rest);

        robot.moveBackwardInches(96, speed);

        /*robot.moveLeftInches(24, speed);
        sleep(rest);


        //robot scans for the right april tag, moves forward to the right position, and places the yellow pixel
        //more code needed where indicated

        robot.moveForwardInches(18, speed);
        sleep(rest);

        //robot scans for the right april tag
        sleep(rest);

        robot.moveForwardInches(20, speed);
        sleep(rest);

        //robot turns to correct position
        sleep(rest);

        //robot places yellow pixel
        sleep(rest);


        //robot then moves into the parking area

        robot.moveLeftInches(24, speed);
        sleep(rest);

        robot.moveForwardInches(12, speed);*/

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}
//name robot billy dignam            