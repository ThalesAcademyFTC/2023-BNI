package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
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
        robot = new Spark(this, Spark.Drivetrain.TEST);

        // TODO Change the team to whichever team this auton is for
        tagger = new Tagger( this, robot.webcamName, team);
        runtime.reset();

        tagger.init();

        waitForStart();

        robot.moveForwardInches(48, 0.5);

        
        // This just fills telemetry data with the detected tag.
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (runtime.seconds() < 30) {
                    //LAVA PALLETTE IS A TEST TO SEE HOW IT BLINKS, THIS WILL BE GREEN IN THE FUTURE
                    robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_LAVA_PALETTE);
                }else if (runtime.seconds() >= 20) {
                    robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED);
                }else if (runtime.seconds() >= 25) {
                    robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED);
                }else if (runtime.seconds() == 30) {
                    robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
                }

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