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
        robot = new Spark(this, Spark.Drivetrain.TEST);
        runtime.reset();

        waitForStart();

        //variables for auton

        double speed = 0.5;
        int rest = 100;
        //more code needed where indicate
        //robot scans for the spike mark, and places purple pixel
        

        robot.moveForwardInches(37, speed);
        sleep(rest);

        robot.moveBackwardInches(34, speed);
        sleep(rest);

        robot.turnRightDegrees(90, 0.75);
        sleep(rest);

        robot.moveForwardInches(50, speed);
        sleep(rest);

        robot.moveLeftInches(24, speed);
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

        robot.moveForwardInches(12, speed);

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}
//name robot billy dignam            