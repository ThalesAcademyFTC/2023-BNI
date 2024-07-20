package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@Autonomous(name="BlueShortNoArmAuton")
public class BlueShortNoArmAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Tagger tagger;

    private Finder finder;

    private Spark.Team team = Spark.Team.BLUE;




    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        // TODO Change the team to whichever team this auton is for
       // tagger = new Tagger( this, robot.webcamName, team);

        //finder = new Finder(this, robot.webcamName, team);

        runtime.reset();

        //tagger.init();

        waitForStart();
  
        double speed = 0.5;
        int rest = 100;

        robot.moveRightInches(48,speed);

        
//        //Code for auton
//        robot.moveForwardInches(12 , speed);
//        sleep(100);
//
//         //code for scanning spike mark goes here
//        sleep(100);
//                //code for placing purple pixel goes here
//        //robot.sleep(rest);
//        robot.moveBackwardInches(9, speed);
//        sleep(100);
//
//        robot.turnLeftDegrees(90, speed * 3/2);
//        //robot.sleep(rest);
//
//        robot.moveForwardInches(24 , speed);
//        //robot.sleep(rest);
//
//        robot.moveRightInches(24 , speed);
//        //robot.sleep(rest);
//        //robot scans for the correct april tag, moves forward to the correct position, and places the yellow pixel
//        //more code needed where indicated
//
//        //code for scanning april tags goes here
//        //robot.sleep(rest);
//        robot.moveForwardInches(6, speed);
//        //robot.sleep(rest);
//        //code for positioning to place yellow pixel goes here
//
//        //robot.sleep(rest);
//
//        robot.moveLeftInches(24 , speed * 3/2);
//        //robot.sleep(rest);
//
//        robot.moveForwardInches(18, speed);
//        //robot.sleep(rest);

        
        // This just fills telemetry data with the detected tag.
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                //tagger.scanWithTelemetry();

                // Push telemetry to the Driver Station.
                telemetry.update();

                // Share the CPU.
                //sleep(20);
            }
        }

        // Save more CPU resources when camera is no longer needed.
        //tagger.close();

    }

}