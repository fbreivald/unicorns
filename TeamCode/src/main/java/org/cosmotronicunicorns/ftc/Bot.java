package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Bot {

    private HardwareMap hardwareMap;
    private Gyroscope imu;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;
    private TouchSensor digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    public Bot(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

}


