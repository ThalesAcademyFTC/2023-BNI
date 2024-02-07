package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CompetitionTeleop")
public class CompetitionTeleop extends OpMode {

    Spark robot;
    
    /** Allows for driver customization of movement */
    static final double STRAFE_FACTOR = 1.1;

    @Override
    public void init() {

        // INITIALIZE the library object
        robot = new Spark(this, Spark.Drivetrain.MECHANUM );

    }

    @Override
    public void loop() {

        //Buttons for actions on gamepad1
        
        //Right trigger arm up
        //Left trigger arm down
        //Right bumper claw open
        //Left bumper claw close

        telemetry.addData("front left encoder:", robot.motorFrontLeft.getCurrentPosition());
        telemetry.addData("front right encoder:", robot.motorFrontRight.getCurrentPosition());
        telemetry.addData("back left encoder:", robot.motorBackLeft.getCurrentPosition());
        telemetry.addData("back right encoder:", robot.motorBackRight.getCurrentPosition());

        telemetry.update();


        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y; // Y gamepad is reversed, so reverse this value
        double x = gamepad1.left_stick_x * STRAFE_FACTOR; // Scaling to fix
        double turn = gamepad1.right_stick_x; // Turn value
       
        //Now, set motor powers using x, y, and turn variables
        
        //Movement
        robot.move(x, y, turn);



            //GAMEPAD 2

        //Moves large arm down
        if (gamepad2.right_stick_y > 0.3 ){
            robot.setArmMotor(gamepad2.right_stick_y / 2);
        
        } else if (gamepad2.right_stick_y < -0.3 ){
            robot.setArmMotor(gamepad2.right_stick_y / 5);

        } else {
            robot.setArmMotor(0);
        }

        if (gamepad2.left_trigger > 5){
            robot.setClawServoLeft(1);

        } else {
            robot.setClawServoLeft(0);

        }

        if (gamepad2.right_trigger < 5){
            robot.setClawServoRight(1);

        } else {
            robot.setClawServoRight(0);

        }

        if (gamepad2.left_stick_y < -0.3 ){
            robot.dropHook(0.2);

        } else if (gamepad2.left_stick_y > 0.3 ){
            robot.pickUpHook(0);

        }


        //robot suspends on bar
        /*if (gamepad2.x) {
            robot.setMotorSuspend(1);

        } else if (gamepad2.b) { //Robot releases from bar
            robot.setMotorSuspend(-1);
        
        } else {
            robot.setMotorSuspend(0);

        }*/
        



        if (gamepad1.atRest()) robot.rest();
    
    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               //For Billy Dignam. Bless his soul. :)