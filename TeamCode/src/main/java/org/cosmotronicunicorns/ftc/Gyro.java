package org.cosmotronicunicorns.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.util.Range;

public class Gyro extends OpMode {
    private Gyroscope imu;
    private DcMotor mLeftFront;
    private DcMotor mLeftBack;
    private DcMotor mRightFront;
    private DcMotor mRightBack;
    private int powerFactor = 2;
    private boolean prevX;
    private boolean prevY;

    @Override
    public void init() {
        mLeftFront = get(DcMotor.class, "mLeftFront");
        mLeftBack = get(DcMotor.class, "mLeftBack");
        mRightFront = get(DcMotor.class, "mRightFront");
        mRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        mRightBack = get(DcMotor.class, "mRightBack");
        mRightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry.update();
    }

    @Override
    public void loop() {
        updatePowerFactor();
        double drive = -gamepad1.left_stick_y; // logitech y-axis is reversed, so fix it
        double turn = gamepad1.right_stick_x;

        // only read triggers if there is no stick input
        if (drive != 0 || turn != 0) {
            double leftPower = Range.clip(drive - turn, -1.0, 1.0);
            double rightPower = Range.clip(drive + turn, -1.0, 1.0);
            setPower(mLeftFront, leftPower);
            setPower(mLeftBack, leftPower);
            setPower(mRightFront, rightPower);
            setPower(mRightBack, rightPower);
        } else {
            double strafe = gamepad1.left_trigger - gamepad1.right_trigger;
            setPower(mLeftFront, -strafe);
            setPower(mRightFront, strafe);
            setPower(mLeftBack, strafe);
            setPower(mRightBack, -strafe);
        }

        telemetry.update();
    }

    private void updatePowerFactor() {
        if (xReleased()) {
            powerFactor++;
        } else if (yReleased()){
            powerFactor = Math.max(powerFactor - 1, 1);
        }
        telemetry.addData("Power", "1/%d", powerFactor);
    }

    private <T> T get(Class<? extends T> clazz, String name) {
        try {
            return hardwareMap.get(clazz, name);
        } catch (Exception e) {
            telemetry.clearAll();
            telemetry.addLine(String.format("%s '%s' not found"));
            telemetry.addLine("Press 'X' to continue");
            telemetry.update();
            while (!gamepad1.x) {}
            return null;
        }
    }

    private void setPower(DcMotor motor, double power) {
        motor.setPower(powerFactor == 1 ? power : power / powerFactor);
    }

    private boolean xReleased() {
        boolean released = !gamepad1.x && prevX;
//        telemetry.addData("X/prev/released", "%b/%b/%b", gamepad1.x, prevX, released);
        prevX = gamepad1.x;
        return released;
    }

    private boolean yReleased() {
        boolean released = !gamepad1.y && prevY;
//        telemetry.addData("X/prev/released", "%b/%b/%b", gamepad1.x, prevX, released);
        prevY = gamepad1.y;
        return released;
    }
}
