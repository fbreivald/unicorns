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
        boolean correctDistance = false;
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
                    servoGrabber.setPosition(downPos);
                    move(.5, 250);


                    sFound = true;

                } else {
                    strafe(.5, 200);
                    while (!correctDistance) {
                        double distance1 = distanceSensor.getDistance(DistanceUnit.MM);
                        if (distance1 > 29) {
                            move(.25);
                        } else if(distance1 < 31) {
                            move(-.25);
                        } else {
                            correctDistance = true;
                        }
                    }
                    stones += 1;
                }

                strafe(-.5, (stones * 200) + 4050);
                servoGrabber.setPosition(upPos);
            }
        }
    }
}

