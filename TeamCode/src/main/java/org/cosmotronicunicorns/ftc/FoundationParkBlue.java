package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.MM;

@Autonomous
public class FoundationParkBlue extends Bot {

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();

        // Actual commands using functions declared lines 24 - 60
        move(.5,100);
        strafe(-.35, 3000);
        strafe(.35, 500);
        move(.5, 400);
        while(opModeIsActive() && distanceSensorLeft.getDistance(MM) > 11) {
            move(.2);
        }
        halt();
        servoGrabber.setPosition(downPos+.05);
        sleep(1000);
        move(-.25, 5000);
        servoGrabber.setPosition(upPos);
        strafe(1, 900);


    }


}

