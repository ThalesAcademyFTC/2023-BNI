package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Spark;

@TeleOp()
public class NewFeatureDemo extends OpMode {

    //Create current and previous gamepads.
    // This allows us to store the current value of gamepad1 and gamepad2
    // It also allows us to see what the gamepad was in the previous iteration of the loop
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();

    /** DECLARE library object used to call functions related to the robot */
    Spark robot;

    /**
     * DECLARE and INITIALIZE the clawToggle variable.
     * This variable will be used to swap between open and closed claw
     */
    boolean clawToggle = false;

    static final double STRAFE_FACTOR = 1.1;

    @Override
    public void init() {

        // INITIALIZE the library object
        robot = new Spark( this, Spark.Drivetrain.MECHANUM );

    }

    @Override
    public void loop() {

        telemetry.addLine( "ButtonPress: A" );
        telemetry.addLine( "ButtonRelease: B" );
        telemetry.addLine( "ButtonToggle: Y" );

        // Store the gamepad values from the previous loop iteration in
        // previousGamepad1/2 to be used in this loop iteration.
        // This is equivalent to doing this at the end of the previous
        // loop iteration, as it will run in the same order except for
        // the first/last iteration of the loop.
        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);


        // Store the gamepad values from this loop iteration in
        // currentGamepad1/2 to be used for the entirety of this loop iteration.
        // This prevents the gamepad values from changing between being
        // used and stored in previousGamepad1/2.
        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);

        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y; // Y gamepad is reversed, so reverse this value
        double x = gamepad1.left_stick_x;
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

        //If the button is pressed in this iteration, then swap the clawToggle.
        //Only swaps once per press
        if (currentGamepad1.y && !previousGamepad1.y) {
            clawToggle = !clawToggle;
        }

        //If claw toggle is true, then open the claw. If it is false, close the claw
        if ( clawToggle ) {
            robot.openClaw();
        } else {
            robot.closeClaw();
        }

        //Basically, if the button was pressed in this iteration but not the previous one.
        // Only runs once, even if button is held down.
        if (currentGamepad1.a && !previousGamepad1.a) {
            robot.setClawServo( robot.clawServo.getPosition() + 0.05 );
        }

        //Basically, if the button was released in this iteration but not the previous one.
        // Only runs once, even if button continues to be released
        if (!currentGamepad1.b && previousGamepad1.b) {
            robot.setClawServo( robot.clawServo.getPosition() - 0.05 );
        }

    }
}
