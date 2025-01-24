package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
//@Disabled
@Autonomous(name="BlueLongAuton")
public class BlueLongAuton extends LinearOpMode {

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

        // scans for the spike mark, and places purple pixel
        //more code needed where indicated



        /*robot scans for spike mark
        robot rests

        robot places purple pixel
        robot rests*

         */


        //robot moves back and approaches backboard
               
        /*robot.moveBackwardInches(9, speed);
        sleep(100);
        
        robot.turnLeftDegrees(90, speed * 3/2);
        sleep(100);
            
        robot.moveForwardInches(50 , speed);
        sleep(100);
        
        robot.moveRightInches(24 , speed);
        sleep(100);


        //robot scans for the right april tag, moves forward to the right position, and places the yellow pixel
        //more code needed where indicated

        //robot scans for the right april tag
        //sleep(100);

        robot.moveForwardInches(6, speed);
        sleep(100);

        //robot turns to the right position
        sleep(100);
        
        //place the  yellow pixel
        //robot.sleep(rest);

        //robot then moves into the parking area

        robot.moveLeftInches(24 , speed * 3/2);
        sleep(100);
        
        robot.moveForwardInches(18, speed);
        sleep(100);*/

        robot.turnRightDegrees(90, speed);
        sleep(rest);

        robot.moveLeftInches(48, speed);
        sleep(rest);

        robot.moveBackwardInches(96, speed);

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}