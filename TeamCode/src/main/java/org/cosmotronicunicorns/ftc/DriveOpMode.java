package org.cosmotronicunicorns.ftc;

// import packages
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp
public class DriveOpMode extends LinearOpMode {
// declare components
    private DcMotor mLeftFront;
    private DcMotor mLeftBack;
    private DcMotor mRightFront;
    private DcMotor mRightBack;

    private Servo servoGrabber;
    private ColorSensor fColorSensor;
    private DistanceSensor fDistanceSensor;
    private static double upPos = .55;
    private static double downPos = .95;

    //To check if x or y are pressed
    private boolean prevX = false;
    private boolean prevY = false;
    private boolean prevA = false;
    private boolean prevRB = false;

    @Override
    public void runOpMode() throws InterruptedException {
        //Declare Motors with try-catch
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
            servoGrabber = hardwareMap.get(Servo.class, "servoGrabber");
        } catch (Exception e) {
            telemetry.addData("error", "grabber servo not found");
        }
        try {
            fColorSensor = hardwareMap.get(ColorSensor.class, "fColorSensor");
        } catch (Exception e) {
            telemetry.addData("error", "color sensor front not found");
        }try {
            fDistanceSensor = hardwareMap.get(DistanceSensor.class, "fColorSensor");
        } catch (Exception e) {
            telemetry.addData("error", "color sensor front not found");
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        loop();
        // run until the end of the match (driver presses STOP)
        // Set two opposite motors to reverse
        int beastmode = 1;
        boolean armDown = false;
        mRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        mRightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        mLeftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()) {
            //declare variables for controller input
            double LSY = -gamepad1.left_stick_y;
            double RSX = -gamepad1.right_stick_x;
            double LT = gamepad1.left_trigger;
            double RT = gamepad1.right_trigger;
            double strafe = LT - RT;
            double lfPower = 0;
            double rfPower = 0;
            double lbPower = 0;
            double rbPower = 0;

            // Check if beastmode has changed
            if(yReleased()) {
                beastmode++;
            } else if (xReleased()) {
                beastmode = Math.max(beastmode - 1, 1);
            }
            telemetry.addData("beastmode", beastmode);


            // To move arm up and down with a toggle button A
            if(aReleased()) {
                if(!armDown) {
                    servoGrabber.setPosition(downPos);
                    armDown = true;
                } else if(armDown) {
                    servoGrabber.setPosition(upPos);
                    armDown = false;
                }
            }
            if(bumperReleased()) {
                if(!armDown) {
                    servoGrabber.setPosition(downPos+.25);
                    armDown = true;
                } else if(armDown) {
                    servoGrabber.setPosition(upPos);
                    armDown = false;
                }
            }



            //Declare power to Joystick Position with max of 1 and min of -1
            lfPower = Range.clip(LSY - RSX - strafe, -1.0, 1.0);
            lbPower = Range.clip(LSY - RSX + strafe, -1.0, 1.0);
            rfPower = Range.clip(LSY + RSX + strafe, -1.0, 1.0);
            rbPower = Range.clip(LSY + RSX - strafe, -1.0, 1.0);

            // Set motor power to declared power
            mLeftFront.setPower(lfPower / beastmode);
            mRightFront.setPower(rfPower / beastmode);
            mLeftBack.setPower(lbPower / beastmode);
            mRightBack.setPower(rbPower / beastmode);

            //Output controller input
            telemetry.addData("Status", "running");
            telemetry.addData("Left Stick Y: ", LSY);
            telemetry.addData("Right Stick X: ", RSX);
            telemetry.addData("Left Trigger: ", LT);
            telemetry.addData("Right Trigger ", RT);
            telemetry.addData("ServoGrabber Position: ", servoGrabber.getPosition());
            telemetry.update();
        }
    }

    // Check if pressed and released x/y button to change the value of beastmode
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
    boolean aReleased() {
        boolean released = !gamepad1.a && prevA;
        telemetry.addData("Y/prev/released", "%b/%b/%b", gamepad1.y, prevA, released);
        prevA = gamepad1.a;
        return released;
    }
    boolean bumperReleased() {
        boolean released = !gamepad1.right_bumper && prevRB;
        telemetry.addData("RB/prev/released", "%b/%b/%b", gamepad1.y, prevA, released);
        prevRB = gamepad1.right_bumper;
        return released;
    }
}
