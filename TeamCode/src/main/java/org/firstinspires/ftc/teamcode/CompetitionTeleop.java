package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CompetitionTeleop")
public class CompetitionTeleop extends OpMode {

    Spark robot;

    boolean rightToggleToggle = false;

    boolean leftToggleToggle = false;

    boolean leftClawToggle = false;
    boolean rightClawToggle = false;

    boolean redMode = false;

    boolean redModeToggle = false;

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

        telemetry.addData("BackRight", robot.motorBackRight.getPower());
        telemetry.addData("BackLeft", robot.motorBackLeft.getPower());
        telemetry.addData("FrontRight", robot.motorFrontRight.getPower());
        telemetry.addData("FrontLeft", robot.motorFrontLeft.getPower());
        telemetry.addData( "RightClaw0Postion", robot.clawServoR.getPosition());
        telemetry.addData( "LeftClaw0Postion", robot.clawServoL.getPosition());
        telemetry.addData("DroneMotor", robot.droneMotor.getPower());
        telemetry.addData("AngleServo", robot.angleServo.getPosition());

        telemetry.update();


        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y / 2; // Y gamepad is reversed, so reverse this value
        double x = (gamepad1.left_stick_x * STRAFE_FACTOR) / 2; // Scaling to fix
        double turn = gamepad1.right_stick_x / 2; // Turn value


        //Now, set motor powers using x, y, and turn variables
        
        //Movement
        robot.move(x, y, turn);

        //GAMEPAD 2


        //Right trigger toggles between claw open and claw close

        if (gamepad2.right_trigger > 0.1 && !rightToggleToggle) {
            rightToggleToggle = true;
            rightClawToggle = !rightClawToggle;
        } else if (gamepad2.right_trigger <= 0.1 && rightToggleToggle) {
            rightToggleToggle = false;
        }

        //left trigger toggles between claw open and claw close

        if (gamepad2.left_trigger > 0.1 && !leftToggleToggle) {
            leftToggleToggle = true;
            leftClawToggle = !leftClawToggle;
        } else if (gamepad2.left_trigger <= 0.1 && leftToggleToggle) {
            //allows the toggle to toggle again
            leftToggleToggle = false;
        }

        //right claw servo toggle code, claw starts as open and closes on first button press
        if (!rightClawToggle) {
            robot.openClawR();
        } else {
            robot.closeClawR();
        }

        //left claw servo toggle code, claw starts as open and closes on first button press
        if (!leftClawToggle) {
            robot.openClawL();
        } else {
            robot.closeClawL();
        } 

        //Claw tilt 
        if (gamepad2.a) {
            robot.tiltClaw();
        } else if (gamepad2.b) {
            robot.resetClaw();
        }

        //Drone launch while y is held
        if (gamepad1.y) {
            robot.launchDrone();
        } else {
            robot.setDroneMotor(0);
        }

        //Moves large arm down
        if (gamepad2.right_stick_y > 0.3 ){
            robot.liftArm();
        
        } else if (gamepad2.right_stick_y < -0.3 ){
            robot.lowerArm();

        } else {
            robot.setArmMotor(0);
        }

        //led control for robot
    //    if (gamepad1.a && !redModeToggle) {
        //    redModeToggle = true;
      //      redMode = !redMode;
    //    } else {
            //    redModeToggle = false;
        //  }

        //  if (redMode) {

      //  }


        if (gamepad1.atRest()) robot.rest();
    
    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               //For Billy Dignam. Bless his soul. :)