package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class BrickRed extends Bot {

    private static double POWER = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();
        boolean sFound = false;
        int stones = 0;

        while (opModeIsActive()) {
            while(true) {
                double distance = distanceSensor.getDistance(DistanceUnit.MM);
                if (!(distance > 30)) break;
                telemetry.addData("Distamce (mm):", distance);
                move(POWER);
            }
            halt();
            while (!sFound) {
                if (colorSensor.red() <= 300 && colorSensor.green() <= 550 && colorSensor.blue() <= 275) {
                    strafe(.2,50);
                    move(.25, 100);

                    move(.5, 250);


                    sFound = true;

                } else {
                    strafe(.5, 200);
                    stones += 1;
                }

                strafe(-.5, (stones * 200) + 4050);
            }
        }
    }
}

