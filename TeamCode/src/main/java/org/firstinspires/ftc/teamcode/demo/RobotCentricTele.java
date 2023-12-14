package org.firstinspires.ftc.teamcode.demo;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Spark;

@TeleOp()
public class RobotCentricTele extends OpMode {

    /** DECLARE library object used to call functions related to the robot */
    Spark robot;

    /**
     * This is a factor for the drivers that customizes the drive control.
     * Up to driver preference.
     */

    static final double STRAFE_FACTOR = 1.1;

    @Override
    public void init() {

        // INITIALIZE the library object
        robot = new Spark( this, Spark.Drivetrain.MECHANUM );

    }

    @Override
    public void loop() {

        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y; // Y gamepad is reversed, so reverse this value
        double x = gamepad1.left_stick_x * STRAFE_FACTOR; // Scaling to fix
        double turn = gamepad1.right_stick_x; // Turn value

        //Now, set motor powers using x, y, and turn variables
        robot.move( x, y, turn );


    }

}
