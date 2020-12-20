package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.MM;

@Autonomous
public class PushGoal extends Bot {

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();
        boolean oneRing = false;

        // Actual commands using functions declared lines 24 - 60
        move(.25, 2500); // 8.5 in
        strafe(-.25, 850);
        move(.15, 750);
        if (distanceSensorLeft.getDistance(MM) < 40) {
            telemetry.addData("4 Found", "");
            positionA();

        } else {
            telemetry.addData("None Found", "");
            positionC();
        }


    }

    public  void positionA () {
        //move against wall
        strafe(.25, 5000);
        strafe(-.25, 400);
        move(.25, 5300);
    }

    public void positionC () {
        strafe(.25, 5000);
        strafe(-.25, 500);
        move(.4, 10000);
        strafe(.25, 2000);
        move(-1, 750);
    }

}


