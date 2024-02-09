package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="PropTracker")
//@Disabled
public class PropTracker extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Finder finder;

    private Spark.Team team = Spark.Team.BLUE;


    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MISTRO);

        finder = new Finder( this, robot.webcamName, team);
        runtime.reset();

        finder.init();

        waitForStart();

        while ( opModeIsActive() ) {

            //This updates the array of current detections inside tagger.
            finder.scanWithTelemetry();

            telemetry.addData("Team Prop Spike Location: ", finder.getPropLocation());

            telemetry.update();

            // Share the CPU.
            sleep(20);

        }


        // Close out tagger.
        finder.close();

    }

}