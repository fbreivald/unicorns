package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonomousOpMode extends LinearOpMode {
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
        servoTest = hardwareMap.get(Servo.class, "test servo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

    }
}
