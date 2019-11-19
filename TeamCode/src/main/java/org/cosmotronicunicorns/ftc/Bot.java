package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Bot {

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

    public Bot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }

    public void init() {
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

}


