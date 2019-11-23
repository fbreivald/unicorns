package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Bot extends LinearOpMode {

    private HardwareMap hardwareMap;
    private Gyroscope imu;
    private DcMotor mLeftFront;
    private DcMotor mLeftBack;
    private DcMotor mRightFront;
    private DcMotor mRightBack;
    private TouchSensor digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private Telemetry telemetry;

    public void loadComponents() {
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
        try {
            servoTest = hardwareMap.get(Servo.class, "test servo");
        } catch (Exception e) {
            telemetry.addData("error", "test servo not found");
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    //Declare Funtion for stopping robot
    private void halt() {
        mLeftFront.setPower(0);
        mRightFront.setPower(0);
        mLeftBack.setPower(0);
        mRightBack.setPower(0);
    }

    //Declare move function with settings power and time
    private void move(double power, int time) {
        mLeftFront.setPower(power);
        mRightFront.setPower(power);
        mLeftBack.setPower(power);
        mRightBack.setPower(power);
        sleep(time);
        halt();
    }
    //Declare turn function with settings power and time
    // Positive power turns robot clockwise
    private void turn(double power, int time) {
        mLeftFront.setPower(power);
        mRightFront.setPower(-power);
        mLeftBack.setPower(power);
        mRightBack.setPower(-power);
        sleep(time);
        halt();
    }
    //Declare strafe function with settings power and time
    // Positive power strafes the robot left
    private void strafe(double power, int time) {
        mLeftFront.setPower(power);
        mRightFront.setPower(-power);
        mLeftBack.setPower(-power);
        mRightBack.setPower(power);
        sleep(time);
        halt();
    }
}


