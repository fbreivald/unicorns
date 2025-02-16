package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class MoveForward extends Bot {

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();

        // Actual commands using functions declared lines 24 - 60
        sleep(25000);
        move(.5,400);
    }


}

