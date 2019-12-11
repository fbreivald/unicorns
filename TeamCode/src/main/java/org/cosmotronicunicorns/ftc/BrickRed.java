package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class BrickRed extends Bot {

    private static double POWER = 0.1;
    private static double STRAFEPOWER = 0.15;

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();
        boolean sFound = false;
        boolean correctDistance = false;
        int stones = 0;

        while (opModeIsActive()) {

            move(POWER);
            while(true) {
                double distance = distanceSensor.getDistance(DistanceUnit.MM);
                telemetry.addData("Distamce (mm):", distance);
                telemetry.update();
                if (distance < 48) break;
            }
            halt();
            double startTime =getRuntime();

//            while(!yellow()) {
//                telemetry.addData("color:", "%d, %d, %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
//                telemetry.update();
//                move(.1);
//            }
            halt();
            sleep(100);
            while (!sFound) {
                while(distanceSensor.getDistance(DistanceUnit.MM) > 7) {
                    move(POWER);
                }
                halt();
                telemetry.addData("color:", "%d, %d, %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
                telemetry.update();
                if (!yellow()) {
                    sFound = true;
                    halt();
                    telemetry.addData("Skystone ", " Found");
                    telemetry.update();
                    sleep(1000);
                } else {
                    strafe(-STRAFEPOWER, 1500);
                    sleep(50);
                }
            }
            halt();
            double endTime = getRuntime();
            strafe(.3,200);
            sleep(500);
            move(.25, 200);
            strafe(-5,100);
            move(.25, 100);
            strafe(.5,100);
            setGrabberPos(downPos);
            sleep(1000);
            move(-.5, 500);
            strafe(STRAFEPOWER*2 , ((int)((endTime - startTime) * 500)) + 1000);
            setGrabberPos(upPos);
            break;
        }
    }

    boolean yellow () {
        return colorSensor.red() > 15000 && colorSensor.green() > 15000 && colorSensor.blue() < 10000;

    }

    void setGrabberPos(double pos) {
        servoGrabber.setPosition(pos);
        while (Math.abs(servoGrabber.getPosition() - pos) > 0.01) {
            sleep(50);
        }
    }
}

