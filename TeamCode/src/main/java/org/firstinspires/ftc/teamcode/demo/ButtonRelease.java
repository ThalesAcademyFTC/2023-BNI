package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Spark;

@TeleOp()
public class ButtonRelease extends OpMode {

    //Create current and previous gamepads.
    // This allows us to store the current value of gamepad1 and gamepad2
    // It also allows us to see what the gamepad was in the previous iteration of the loop
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();

    Spark robot;

    @Override
    public void init() {
        robot = new Spark( this, Spark.Drivetrain.MECHANUM );
    }

    @Override
    public void loop() {

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

        //Now, teleop code goes below!

        //Basically, if the button was released in this iteration but not the previous one.
        // Only runs once, even if button continues to be released
        if (!currentGamepad1.a && previousGamepad1.a) {
            robot.setClawServo( robot.clawServo.getPosition() - 0.05 );
        }


    }
}
