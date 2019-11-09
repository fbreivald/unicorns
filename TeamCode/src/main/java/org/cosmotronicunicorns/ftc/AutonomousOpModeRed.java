package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutonomousOpModeRed extends LinearOpMode {


    // declare components
    private Gyroscope imu;
    private DcMotor mLeftFront;
    private DcMotor mLeftBack;
    private DcMotor mRightFront;
    private DcMotor mRightBack;

    private Servo servoTest;

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

    @Override
    public void runOpMode() throws InterruptedException {
//        imu = hardwareMap.get(Gyroscope.class, "imu");
        //Declare Motors with try-catch
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
        // Set two opposite motors to reverse
        mRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        mRightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        // Actual commands using functions declared lines 24 - 60
        strafe(-.5,2200);
        turn(.10,120);
        move(1,4000);
        turn(1,200);
        move(1,1000);
        move(-1,300);
        move(1,1500);
        move(-1,100);
        turn(-1,200);
        move(-1,300);


    }


}

