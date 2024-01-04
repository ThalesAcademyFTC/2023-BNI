package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RoughLongRightSideAuton")
public class RoughLongRightSideAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
        runtime.reset();

        waitForStart();

        //variables for auton

        double speed = 0.5;
        int rest = 100;

        //robot moves forward, scans for the spike mark, and places purple pixel
        //more code needed where indicated

        robot.moveForwardInches(12, speed);
        //robot.sleep(rest);

        /*robot scans for spike mark
        robot rests

        robot places purple pixel
        robot rests*/


        //robot moves back and approaches backboard

        robot.moveBackwardInches(9, speed);
        //robot.sleep(rest);

        robot.turnRightDegrees(90, 0.75);
        //robot.sleep(rest);

        robot.moveForwardInches(50, speed);
        //robot.sleep(rest);

        robot.moveRightInches(24, speed);
        //robot.sleep(rest);


        //robot scans for the right april tag, moves forward to the right position, and places the yellow pixel
        //more code needed where indicated

        //robot scans for the right april tag
        //robot.sleep(rest);

        robot.moveForwardInches(6, speed);
        //robot.sleep(rest);

        //robot turns to the right position
        //robot.sleep(rest);

        //robot places yellow pixel
        //robot.sleep(rest);


        //robot then moves into the parking area

        robot.moveLeftInches(24, speed);
        //robot.sleep(rest);

        robot.moveForwardInches(18, speed);

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}
//name robot billy dignam            