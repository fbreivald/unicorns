package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp
public class TestOpMode extends LinearOpMode {
// declare components
    private Gyroscope imu;
    private DcMotor mLeftFront;
    private DcMotor mLeftBack;
    private DcMotor mRightFront;
    private DcMotor mRightBack;

    private Servo servoTest;

    private boolean prevX = false;
    private boolean prevY = false;

    @Override
    public void runOpMode() throws InterruptedException {
        try {
            mLeftFront = hardwareMap.get(DcMotor.class, "mLeftFront");
        } catch (Exception e) {
            telemetry.addData("error", "LeftFront Motor not found");
        }
        try {
            mLeftBack = hardwareMap.get(DcMotor.class, "mLeftBack");
        } catch (Exception e) {
            telemetry.addData("error", "LeftBack Motor not found");
        }
        try {
            mRightFront = hardwareMap.get(DcMotor.class, "mRightFront");
        } catch (Exception e) {
            telemetry.addData("error", "RightFront Motor not found");
        }
        try {
            mRightBack = hardwareMap.get(DcMotor.class, "mRightBack");
        } catch (Exception e) {
            telemetry.addData("error", "RightBack Motor not found");
        }
        try {
            servoTest = hardwareMap.get(Servo.class, "test servo");
        } catch (Exception e) {
            telemetry.addData("error", "test servo not found");
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        loop();
        // run until the end of the match (driver presses STOP)

        int beastmode = 1;
        mRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        mRightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()) {
            double LSY = -gamepad1.left_stick_y;
            double RSX = -gamepad1.right_stick_x;
            double LT = gamepad1.left_trigger;
            double RT = gamepad1.right_trigger;
            double strafe = LT - RT;
            double lfPower = 0;
            double rfPower = 0;
            double lbPower = 0;
            double rbPower = 0;

            if(yReleased()) {
                beastmode++;
            } else if (xReleased()) {
                beastmode = Math.max(beastmode - 1, 1);
            }
            telemetry.addData("beastmode", beastmode);

            lfPower = Range.clip(LSY - RSX - strafe, -1.0, 1.0);
            lbPower = Range.clip(LSY - RSX + strafe, -1.0, 1.0);
            rfPower = Range.clip(LSY + RSX + strafe, -1.0, 1.0);
            rbPower = Range.clip(LSY + RSX - strafe, -1.0, 1.0);

//            if (LSY != 0 || RSX != 0) {
//            } else {
//                telemetry.addData("strafe", strafe);
//                lfPower = -strafe;
//                rfPower = strafe;
//                lbPower = strafe;
//                rbPower = -strafe;
//            }

            mLeftFront.setPower(lfPower / beastmode);
            mRightFront.setPower(rfPower / beastmode);
            mLeftBack.setPower(lbPower / beastmode);
            mRightBack.setPower(rbPower / beastmode);

            telemetry.addData("Status", "running");
            telemetry.addData("Left Stick Y: ", LSY);
            telemetry.addData("Right Stick X: ", RSX);
            telemetry.addData("Left Trigger: ", LT);
            telemetry.addData("Right Trigger ", RT);
            telemetry.update();
        }
    }

    boolean xReleased() {
        boolean released = !gamepad1.x && prevX;
        telemetry.addData("X/prev/released", "%b/%b/%b", gamepad1.x, prevX, released);
        prevX = gamepad1.x;
        return released;
    }

    boolean yReleased() {
        boolean released = !gamepad1.y && prevY;
        telemetry.addData("Y/prev/released", "%b/%b/%b", gamepad1.y, prevY, released);
        prevY = gamepad1.y;
        return released;
    }
}
