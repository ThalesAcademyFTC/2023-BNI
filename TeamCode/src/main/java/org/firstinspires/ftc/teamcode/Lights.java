package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Lights")
public class Lights extends OpMode {

    Spark robot;


    public void init() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM );
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE);
        }
        if (gamepad1.b) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_LAVA_PALETTE);
        }
        if(gamepad1.x) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_FOREST_PALETTE);
        }
        if (gamepad1.y) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_RAINBOW_PALETTE);
        }
        if (gamepad1.dpad_down) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_RAINBOW_PALETTE);
        }
        if(gamepad1.dpad_up) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.SINELON_RAINBOW_PALETTE);
        }
        if (gamepad1.dpad_left) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_RAINBOW_PALETTE);
        }
        if (gamepad1.dpad_right) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_PARTY_PALETTE);
        }
        if(gamepad1.left_bumper) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
        }
        if (gamepad1.right_bumper) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_WITH_GLITTER);
        }
        if (gamepad1.left_trigger > 0.25) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_WHITE);
        }
        if(gamepad1.right_trigger > 0.25) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_STROBE);
        }
        if (gamepad1.left_stick_button) {
            robot.lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_OCEAN_PALETTE);
        }


    }

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 //For Billy Dignam. Bless his soul. :)