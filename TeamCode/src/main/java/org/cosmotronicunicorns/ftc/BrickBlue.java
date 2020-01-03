package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.MM;

@Autonomous
public class BrickBlue extends Bot {

    private static double POWER = 0.1;
    private static double STRAFEPOWER = 0.15;

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();
        boolean sFound = false;
        boolean correctDistance = false;
        int stones = 0;

        telemetry.addData("Distance (mm)", () -> distanceSensor.getDistance(MM));
        Telemetry.Item colorItem = telemetry.addData("color:", "%d, %d, %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
        colorItem.setValue(() -> colorSensor.argb());
        while (opModeIsActive()) {

            move(POWER);
            while(opModeIsActive() && distanceSensor.getDistance(MM) > 48) {
                telemetry.update();
                idle();
//                double distance = distanceSensor.getDistance(MM);
//                telemetry.addData("Distance (mm):", distance);
            }
            halt();
            double startTime = getRuntime();

//            while(!yellow()) {
//                telemetry.addData("color:", "%d, %d, %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
//                telemetry.update();
//                move(.1);
//            }
            sleep(100);
            while (opModeIsActive() && !sFound) {
                while(opModeIsActive() && distanceSensor.getDistance(MM) > 13) {
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
                    move(-.25, 50);
                    strafe(STRAFEPOWER, 1700);
                    move(.25, 50);
                    sleep(50);
                }
            }
            halt();
            double endTime = getRuntime();
            strafe(-.3,200);
            sleep(500);
            //Shimmy
            move(.25, 200);
            strafe(-.5,200);
            move(.25, 200);
            strafe(.5,200);

            setGrabberPos(downPos);
            sleep(1000);
            moveFastSlow(-.5, 1100);
            move(.5, 30);
            strafeFastSlow(-1, 1700);
            move(.5, 800);
            setGrabberPos(upPos);
            moveFastSlow(-1,1000);
            strafeFastSlow(-.35, 2000);
            strafe(.35, 500);
            move(.5, 500);
            while(opModeIsActive() && distanceSensor.getDistance(MM) > 11) {
                move(POWER+.1);
            }
            setGrabberPos(downPos+.05);
            sleep(1000);
            move(-.45, 5000);
            setGrabberPos(upPos);
            strafe(1, 950);

            break;
        }
    }


    boolean yellow () {
        return colorSensor.red() > 7000 && colorSensor.green() > 10000 && colorSensor.blue() < 10000;

    }

    void setGrabberPos(double pos) {
        servoGrabber.setPosition(pos);
        while (Math.abs(servoGrabber.getPosition() - pos) > 0.01) {
            sleep(50);
        }
    }
}

