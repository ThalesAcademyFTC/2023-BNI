package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutonTest")
public class AutonTest extends LinearOpMode {

    Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Tagger tagger;

    private Spark.Team team = Spark.Team.BLUE;

    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        // TODO Change the team to whichever team this auton is for
        tagger = new Tagger( this, robot.webcamName, team);
        runtime.reset();

        tagger.init();

        waitForStart();

        robot.moveForwardInches(48, 0.5);

        
        // This just fills telemetry data with the detected tag.
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                tagger.scanWithTelemetry();

                // Push telemetry to the Driver Station.
                telemetry.update();

                // Share the CPU.
                sleep(20);
            }
        }

        // Save more CPU resources when camera is no longer needed.
        tagger.close();

    }

}