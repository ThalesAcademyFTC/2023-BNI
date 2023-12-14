package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RoughLeftSideAuton")
@Disabled
public class RoughLeftSideAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
        runtime.reset();

        waitForStart();
  
        double speed = 0.5;
        int rest = 100;

        //Code for auton
        robot.moveForwardInches(12 , speed);   
        //robot.sleep(rest);
        
         //code for scanning spike mark goes here
        //robot.sleep(rest);
                //code for placing purple pixel goes here
        //robot.sleep(rest);
        robot.moveBackwardInches(9, speed);    
        //robot.sleep(rest);
        
        robot.turnLeftDegrees(90, speed * 3/2);         
        //robot.sleep(rest);
            
        robot.moveForwardInches(24 , speed); 
        //robot.sleep(rest);
        
        robot.moveRightInches(24 , speed);  
        //robot.sleep(rest);
        //robot scans for the left april tag, moves forward to the correct position, and places the yellow pixel
        //more code needed where indicated

        //code for scanning april tags goes here
        //robot.sleep(rest);
        robot.moveForwardInches(6, speed);
        //robot.sleep(rest);
         //code for positioning to place yellow pixel goes here
     
        //robot.sleep(rest);

        robot.moveLeftInches(24 , speed * 3/2);
        //robot.sleep(rest);
        
        robot.moveForwardInches(18, speed);
        //robot.sleep(rest);

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}