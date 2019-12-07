package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class BrickRed extends Bot {

    private static double POWER = 0.15;
    private static double STRAFEPOWER = 0.15;

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
                telemetry.addData("Distamce (mm):", distance);
                telemetry.update();
                if (distance < 48) break;
                move(POWER);
            }
            halt();
            double startTime =getRuntime();

            while(!yellow()) {
                telemetry.addData("color:", "%d, %d, %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
                telemetry.update();
                move(.1);
            }
            halt();
            move(-.25,30);
            sleep(2000);
            while (!sFound) {
                telemetry.addData("color:", "%d, %d, %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
                telemetry.update();
                if (!yellow()) {
                    sFound = true;
                    halt();
                } else {
                    strafe(-STRAFEPOWER, 200);
                    strafe(-STRAFEPOWER);
                }
            }
            halt();
            double endTime = getRuntime();
            strafe(.2,150);
            move(.25, 200);
            setGrabberPos(downPos);
            sleep(1000);
            move(-.5, 500);
            strafe(STRAFEPOWER , ((int)((endTime - startTime) * 1000)) + 4050);
            setGrabberPos(upPos);
        }
    }

    boolean yellow () {
        return colorSensor.red() > 25000 && colorSensor.green() > 25000 && colorSensor.blue() < 10000;

    }

    void setGrabberPos(double pos) {
        servoGrabber.setPosition(pos);
        while (Math.abs(servoGrabber.getPosition() - pos) > 0.01) {
            sleep(50);
        }
    }
}

