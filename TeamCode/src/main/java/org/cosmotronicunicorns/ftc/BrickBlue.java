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

//            while(!yellow()) {
//                telemetry.addData("color:", "%d, %d, %d", colorSensorLeft.red(), colorSensorLeft.green(), colorSensorLeft.blue());
//                telemetry.update();
//                move(.1);
//            }
            sleep(100);
            while (opModeIsActive() && !sFound) {
                while(opModeIsActive() && distanceSensorLeft.getDistance(MM) > 10) {
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
                    move(-.25, 50);
                    strafe(STRAFEPOWER, 1350);
                    move(.25, 50);
                    sleep(50);
                }
            }
            halt();
            strafe(-.3,180);
            sleep(500);
            //Shimmy
            move(.25, 300);
            strafe(-.5,150);
            move(.25, 250);
            strafe(.5,250);

            setGrabberPos(downPos);
            telemetry.log().add("about to sleep");
            sleep(1000);
            move(1,150);
            sleep(500);
            telemetry.log().add("back from sleep");
            moveFastSlow(-.35, 2000);
            move(.5, 30);
            strafeFastSlow(-1, 1700);
            move(.5, 700);
            setGrabberPos(upPos);
            moveFastSlow(-1,1000);
            strafeFastSlow(-.35, 2000);
            strafe(.35, 500);
            move(.5, 500);
            while(opModeIsActive() && distanceSensorLeft.getDistance(MM) > 11) {
                move(POWER+.1);
            }
            halt();
            setGrabberPos(downPos+.25);
            sleep(1000);
            move(-.45, 2750);
            setGrabberPos(upPos);
            strafe(1, 950);

            break;
        }
    }


    boolean yellow () {
        //return colorSensorLeft.red() > 7000 && colorSensorLeft.green() > 10000 && colorSensorLeft.blue() < 10000;
        return colorSensorRight.red()/colorSensorRight.blue() > 1.85;

    }
}

