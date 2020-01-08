package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class StrafeLeftMoveForward extends Bot {

    @Override
    public void runOpMode() throws InterruptedException {
        initDevices();
        waitForStart();

        // Actual commands using functions declared lines 24 - 60
        sleep(25000);
        strafe(-.5, 750);
        move(.5,400);
    }


}

