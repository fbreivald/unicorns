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
            boolean beastmode = false;
            double LSY = gamepad1.left_stick_y;
            double RSX = gamepad1.right_stick_x;
            double LT = gamepad1.left_trigger;
            double RT = gamepad1.right_trigger;
            double v1 = 0;
            double v2 = 0;
            double v3 = 0;
            double v4 = 0;

            if(gamepad1.y) {
                beastmode = true;
            } else if (gamepad1.x) {
                beastmode = false;
            }

            if(LSY != 0) {
                v1 = LSY;
                v2 = LSY;
                v3 = LSY;
                v4 = LSY;
            } else if(RSX > 0) {
                v1 = RSX;
                v2 = -RSX;
                v3 = RSX;
                v4 = -RSX;
            } else if(RSX < 0) {
                v1 = -RSX;
                v2 = RSX;
                v3 = -RSX;
                v4 = RSX;
            } else if(LT != 0) {
                v1 = -LT;
                v2 = LT;
                v3 = LT;
                v4 = -LT;
            } else if(RT != 0) {
                v1 = RT;
                v2 = -RT;
                v3 = -RT;
                v4 = RT;
            }


            if(beastmode) {
                mLeftFront.setPower(v1);
                mRightFront.setPower(v2);
                mLeftBack.setPower(v3);
                mRightBack.setPower(v4);
            } else {
                mLeftFront.setPower(v1 / 2);
                mRightFront.setPower(v2 / 2);
                mLeftBack.setPower(v3 / 2);
                mRightBack.setPower(v4 / 2);
            }

            telemetry.addData("Status", "running");
            telemetry.addData("LeftFront Motor: ", v1);
            telemetry.addData("RightFront Motor: ", v2);
            telemetry.addData("LeftBack Motor: ", v3);
            telemetry.addData("RightBack Motor: ", v4);
            telemetry.update();


        }
    }
}
