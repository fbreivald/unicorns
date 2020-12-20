package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class Bot extends LinearOpMode {

    protected Gyroscope imu;
    protected DcMotor mLeftFront;
    protected DcMotor mLeftBack;
    protected DcMotor mRightFront;
    protected DcMotor mRightBack;
    protected DcMotor mLeftShooter;
    protected DcMotor mRightShooter;
    protected ColorSensor colorSensorRight;
    protected ColorSensor colorSensorLeft;
    protected DistanceSensor distanceSensorRight;
    protected DistanceSensor distanceSensorLeft;

    //protected Servo servoGrabber;
    protected static double upPos = .15;
    protected static double downPos = .45;

    //To check if x or y are pressed
    private boolean prevX = false;
    private boolean prevY = false;
    private boolean prevA = false;

    public void initDevices () {
        mLeftFront = initDevice(DcMotor.class, "mLeftFront");
        mLeftBack = initDevice(DcMotor.class, "mLeftBack");
        mRightFront = initDevice(DcMotor.class, "mRightFront");
        mRightBack = initDevice(DcMotor.class, "mRightBack");
        mLeftShooter = initDevice(DcMotor.class, "mLeftShooter");
        mRightShooter = initDevice(DcMotor.class, "mRightShooter");
        //servoGrabber = initDevice(Servo.class, "servoGrabber");
        colorSensorRight = initDevice(ColorSensor.class, "fColorSensor");
        colorSensorLeft = initDevice(ColorSensor.class, "fColorSensor2");
        distanceSensorRight = initDevice(DistanceSensor.class, "fColorSensor");
        distanceSensorLeft = initDevice(DistanceSensor.class, "fColorSensor2");
        //servoGrabber.setPosition(upPos);

        mRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        mRightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        mLeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        mLeftShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }



    private <T> T initDevice(Class<? extends T> type, String name) {
        T device = null;
        try {
            device = hardwareMap.get(type, name);
            telemetry.addData("initialized", "%s %s", type, name);
        } catch (Exception e) {
            telemetry.addData("error", "%s %s not found", type, name);
        } finally {
            telemetry.update();
        }
        return device;
    }

    //Declare Funtion for stopping robot
    protected void halt() {
        move(0);
    }

    //Declare move function with settings power and time
    protected void move(double power, int time) {
        move(power);
        sleep(time);
        halt();
    }

    protected void move(double power) {
        mLeftFront.setPower(power);
        mRightFront.setPower(power);
        mLeftBack.setPower(power);
        mRightBack.setPower(power);
    }

    //Declare turn function with settings power and time
    // Positive power turns robot clockwise
    protected void turn(double power, int time) {
        mLeftFront.setPower(power);
        mRightFront.setPower(-power);
        mLeftBack.setPower(power);
        mRightBack.setPower(-power);
        sleep(time);
        halt();
    }
    //Declare strafe function with settings power and time
    // Positive power strafes the robot right
    protected void strafe(double power, int time) {
        mLeftFront.setPower(power);
        mRightFront.setPower(-power);
        mLeftBack.setPower(-power);
        mRightBack.setPower(power);
        sleep(time);
        halt();
    }
    protected void moveFastSlow(double power, int time) {
        telemetry.log().add("moveFastSlow");
        mLeftFront.setPower(power);
        mRightFront.setPower(power);
        mLeftBack.setPower(power);
        mRightBack.setPower(power);
        telemetry.log().add("about to sleep");
        sleep(time-300);
        telemetry.log().add("back from sleep");
        mLeftFront.setPower(power > 0 ? 0.1 : -0.1);
        mRightFront.setPower(power > 0 ? 0.1 : -0.1);
        mLeftBack.setPower(power > 0 ? 0.1 : -0.1);
        mRightBack.setPower(power > 0 ? 0.1 : -0.1);
        telemetry.log().add("about to sleep");
        sleep(300);

    }

    protected void strafeFastSlow(double power, int time) {
        mLeftFront.setPower(power);
        mRightFront.setPower(-power);
        mLeftBack.setPower(-power);
        mRightBack.setPower(power);
        sleep(time -300);
        mLeftFront.setPower(power > 0 ? 0.1 : -0.1);
        mRightFront.setPower(power > 0 ? -0.1 : 0.1);
        mLeftBack.setPower(power > 0 ? -0.1 : 0.1);
        mRightBack.setPower(power > 0 ? 0.1 : -0.1);
        sleep(300);
    }

    protected <T> boolean activeAnd(Supplier<Boolean> test) {
        return opModeIsActive() && test.get();
    }


}