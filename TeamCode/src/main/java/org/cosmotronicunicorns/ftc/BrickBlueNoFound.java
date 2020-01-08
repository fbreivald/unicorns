package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.MM;

@Autonomous
public class BrickBlueNoFound extends Bot {

    private static double POWER = 0.1;
    private static double STRAFEPOWER = 0.15;

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();
        boolean sFound = false;
        boolean correctDistance = false;
        int stones = 0;

        telemetry.addData("Distance (mm)", () -> distanceSensorLeft.getDistance(MM));
        Telemetry.Item colorItem = telemetry.addData("color:", "%d, %d, %d", colorSensorLeft.red(), colorSensorLeft.green(), colorSensorLeft.blue());
        colorItem.setValue(() -> colorSensorLeft.argb());
        while (opModeIsActive()) {

            move(POWER);
            while(opModeIsActive() && distanceSensorLeft.getDistance(MM) > 48) {
                telemetry.update();
                idle();
//                double distance = distanceSensorLeft.getDistance(MM);
//                telemetry.addData("Distance (mm):", distance);
            }
            halt();
            double startTime = getRuntime();

//            while(!yellow()) {
//                telemetry.addData("color:", "%d, %d, %d", colorSensorLeft.red(), colorSensorLeft.green(), colorSensorLeft.blue());
//                telemetry.update();
//                move(.1);
//            }
            sleep(100);
            while (opModeIsActive() && !sFound) {
                while(opModeIsActive() && distanceSensorLeft.getDistance(MM) > 11) {
                    move(POWER);
                }
                halt();
                telemetry.addData("color:", "%d, %d, %d", colorSensorLeft.red(), colorSensorLeft.green(), colorSensorLeft.blue());
                telemetry.update();
                if (!yellow()) {
                    sFound = true;
                    halt();
                    telemetry.addData("Skystone ", " Found");
                    telemetry.update();
                    sleep(1000);
                } else {
                    strafe(STRAFEPOWER, 400);
                    sleep(50);
                }
            }
            halt();
            double endTime = getRuntime();
            strafe(.3,200);
            sleep(500);
            //Shimmy
            move(.25, 300);
            strafe(-.5,100);
            move(.25, 100);
            strafe(.5,100);

            setGrabberPos(downPos);
            sleep(1000);
            move(-.5, 2000);
            strafe(-STRAFEPOWER*2 , ((int)((endTime - startTime) * 500)) + 700);
            move(.5, 100);
            setGrabberPos(upPos);
            move(-5,400);
            strafe(.5, 800);
            break;
        }
    }


    boolean yellow () {
        return colorSensorLeft.red() > 7000 && colorSensorLeft.green() > 10000 && colorSensorLeft.blue() < 10000;

    }

    void setGrabberPos(double pos) {
        servoGrabber.setPosition(pos);
        while (Math.abs(servoGrabber.getPosition() - pos) > 0.01) {
            sleep(50);
        }
    }
}

