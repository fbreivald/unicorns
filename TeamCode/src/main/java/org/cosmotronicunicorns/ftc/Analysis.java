package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class Analysis extends Bot {


    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        // Actual commands using functions declared lines 24 - 60
        telemetry.addData("Left Front Motor: ", "Running");
        telemetry.update();
        mLeftFront.setPower(1);
        sleep(500);
        halt();
        sleep(1000);

        telemetry.addData("Left Back Motor: ", "Running");
        telemetry.update();
        mLeftBack.setPower(1);
        sleep(500);
        halt();
        sleep(1000);

        telemetry.addData("Right Front Motor: ", "Running");
        telemetry.update();
        mRightFront.setPower(1);
        sleep(500);
        halt();
        sleep(1000);

        telemetry.addData("Right Back Motor: ", "Running");
        telemetry.update();
        mRightBack.setPower(1);
        sleep(500);
        halt();
        sleep(1000);

//        for(int i = 0; i < 10000; i++) {
//            telemetry.addData("Color Sensor R: ", fColorSensor.red());
//            telemetry.addData("Color Sensor G: ", fColorSensor.green());
//            telemetry.addData("Color Sensor B: ", fColorSensor.blue());
//            telemetry.addData("DistanceMM: ", fDistanceSensor.getDistance(DistanceUnit.MM));
//            telemetry.update();
//        }
    }
}

