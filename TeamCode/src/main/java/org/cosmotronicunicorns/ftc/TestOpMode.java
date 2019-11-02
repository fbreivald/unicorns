package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.security.spec.ECField;

@TeleOp
public class TestOpMode extends LinearOpMode {
// declare components
    private Gyroscope imu;
    private DcMotor mLeftFront;
    private DcMotor mLeftBack;
    private DcMotor mRightFront;
    private DcMotor mRightBack;

    private Servo servoTest;

    @Override
    public void runOpMode() throws InterruptedException {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
        try {
            mLeftFront = hardwareMap.get(DcMotor.class, "mLeftFront");
        } catch (Exception e) {
            telemetry.addData("error", "LeftFront Motor not found");
        }
        try {
            mLeftBack = hardwareMap.get(DcMotor.class, "mLeftBack");
        } catch (Exception e) {
            telemetry.addData("error", "LeftBack Motor not found");
        }
        try {
            mRightFront = hardwareMap.get(DcMotor.class, "mRightFront");
        } catch (Exception e) {
            telemetry.addData("error", "RightFront Motor not found");
        }
        try {
            mRightBack = hardwareMap.get(DcMotor.class, "mRightBack");
        } catch (Exception e) {
            telemetry.addData("error", "RightBack Motor not found");
        }
//        digitalTouch = hardwareMap.get(TouchSensor.class, "test touch");
//        sensorColorRange = hardwareMap.get(DistanceSensor.class, "color");
        try {
            servoTest = hardwareMap.get(Servo.class, "test servo");
        } catch (Exception e) {
            telemetry.addData("error", "test servo not found");
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        loop();
        // run until the end of the match (driver presses STOP)

        while (opModeIsActive()) {
            double r = Math.hypot(gamepad1.right_stick_x, gamepad1.right_stick_y);
            double robotAngle = Math.atan2(gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.PI / 4;
            double rightX = gamepad1.left_stick_y;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            mLeftFront.setPower(v1/2);
            mRightFront.setPower(v2/2);
            mLeftBack.setPower(v3/2);
            mRightBack.setPower(v4/2);

            telemetry.addData("Status", "running");
            telemetry.addData("LeftFront Motor: ", v1);
            telemetry.addData("RightFront Motor: ", v2);
            telemetry.addData("LeftBack Motor: ", v3);
            telemetry.addData("RightBack Motor: ", v4);
            telemetry.addData("Robot Angle: ", robotAngle);
            telemetry.update();


        }
    }
}
