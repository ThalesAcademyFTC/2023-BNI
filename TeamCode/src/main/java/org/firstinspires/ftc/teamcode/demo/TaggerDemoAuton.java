package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Spark;
import org.firstinspires.ftc.teamcode.Tagger;

@Autonomous(name="TaggerDemoAuton")
public class TaggerDemoAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Tagger tagger;


    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        // TODO Change the team to whichever team this auton is for
        tagger = new Tagger( this, robot.webcamName, Tagger.Team.BLUE);
        runtime.reset();

        tagger.init();

        waitForStart();

        // First, here would be where you move to the pixel art

        //Now, we want to start scanning...

        //Let's say we want to place it in left...
        while ( opModeIsActive() && !tagger.leftDetected() ) {

            //This updates the array of current detections inside tagger.
            tagger.scanWithTelemetry();

            //Move left slowly until you see the tag?
            robot.moveLeft( 0.1 );

            telemetry.update();

            // Share the CPU.
            sleep(20);

        }

        // Remember, you have to stop the robot once you find what you are looking for.
        robot.rest();

        //You could get close enough that this would be where you use the arm...

        // ! Arm move and place pixel !

        //Let's say for some reason you want to be more precise.

        //You can use getLeftLocation(), and then use x, y, z, or yaw, pitch, and roll
        //This can help you determine where the april tag is relative to the robot.
        //I recommend testing using telemetry to see the results you get :)
        while ( tagger.leftDetected() && tagger.getLeftLocation().x < 10 ) {

            // Move left until in precise position.
            robot.moveLeft( 0.1 );

            // Sleeping robot for a few milliseconds to give some time in between frames.
            sleep(20);

        }

        // Stopping robot movement
        robot.rest();

        // Now you can place the cube, with 99.9999999% certainty, because you checked the location too
        // ! Arm move and place pixel !


        // Close out tagger.
        tagger.close();

    }

}