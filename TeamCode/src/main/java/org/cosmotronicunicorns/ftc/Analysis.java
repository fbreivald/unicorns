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
        waitForStart();

  /*      telemetry.addData("Status", "Initialized");
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
        sleep(1000); */

        while(opModeIsActive()) {
            telemetry.addData("Color Sensor R: ", colorSensor.red());
            telemetry.addData("Color Sensor G: ", colorSensor.green());
            telemetry.addData("Color Sensor B: ", colorSensor.blue());
            telemetry.addData("DistanceMM: ", distanceSensor.getDistance(DistanceUnit.MM));
            telemetry.update();
        }

    }
}

