package org.cosmotronicunicorns.ftc;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.MM;

@Autonomous
public class FoundationParkRed extends Bot {

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();

        // Actual commands using functions declared lines 24 - 60
        move(.5,100);
        strafe(.35, 3000);
        strafe(-.35, 500);
        move(.5, 400);
        while(opModeIsActive() && distanceSensorRight.getDistance(MM) > 11) {
            move(.2);
        }
        halt();
        servoGrabber.setPosition(downPos+.05);
        sleep(1000);
        move(-.25, 5000);
        servoGrabber.setPosition(upPos);
        strafe(-1, 900);


    }


}

