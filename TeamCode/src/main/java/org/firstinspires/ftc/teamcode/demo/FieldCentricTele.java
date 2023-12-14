package org.firstinspires.ftc.teamcode.demo;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Spark;

@TeleOp()
public class FieldCentricTele extends OpMode {

    /** DECLARE library object used to call functions related to the robot */
    Spark robot;

    /**
     * This is a factor for the drivers that customizes the drive control.
     * Up to driver preference.
     */
    static final double STRAFE_FACTOR = 1.1;

    @Override
    public void init() {

        /** INITIALIZE the library object */
        robot = new Spark( this, Spark.Drivetrain.MECHANUM );

    }

    @Override
    public void loop() {

        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y; // Y gamepad is reversed, so reverse this value
        double x = gamepad1.left_stick_x; // Scaling to fix
        double turn = gamepad1.right_stick_x; // Turn value

        // Resets the robot yaw orientation. Typically will not be used unless an emergency
        if (gamepad1.options) {
            robot.resetYaw();
        }

        // Gets the heading from the IMU
        double heading = robot.getHeading();

        // Creates an adjusted X value based on the field position
        double adjustedX = x * Math.cos(-heading) - y * Math.sin(-heading);

        // Creates an adjusted Y value based on the field position
        double adjustedY = x * Math.sin(-heading) + y * Math.cos(-heading);

        //Adjust for imperfect strafing
        adjustedX *= STRAFE_FACTOR;

        //Now, set motor powers using x, y, and turn variables
        robot.move( adjustedX, adjustedY, turn );


    }

}
