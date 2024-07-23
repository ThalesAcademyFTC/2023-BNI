package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp(name="PracticeTelop")
public class PracticeTelop extends OpMode {

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

        telemetry.addData("front left encoder:", robot.motorFrontLeft.getCurrentPosition());
        telemetry.addData("front right encoder:", robot.motorFrontRight.getCurrentPosition());
        telemetry.addData("back left encoder:", robot.motorBackLeft.getCurrentPosition());
        telemetry.addData("back right encoder:", robot.motorBackRight.getCurrentPosition());

        telemetry.update();

        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y / 2; // Y gamepad is reversed, so reverse this value
        double x = (gamepad1.left_stick_x * STRAFE_FACTOR) / 2; // Scaling to fix
        double turn = gamepad1.right_stick_x / 2; // Turn value

        //Now, set motor powers using x, y, and turn variables
        
        //Movement
        robot.move(x, y, turn);

        if (gamepad1.atRest()) robot.rest();
    
    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               //For Billy Dignam. Bless his soul. :)