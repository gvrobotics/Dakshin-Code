package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Ttele extends OpMode
{
    public DcMotor BR, BL, FR, FL, Shoot, Intake;
    private double powerRY, powerRX, powerLX, powerLY, robotAngle, PowerMultiplier, lf, rb, rf, lb;
    public Servo push;
    private ElapsedTime timer = new ElapsedTime();
    private Boolean motorOn = false, motorOn2 = false, t = false, previousGamepad = false, currentGamepad = false, previousGamepad2 = false, currentGamepad2 = false;

    @Override
    public void init()
    {
        BR = hardwareMap.get(DcMotor.class, "BR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        Shoot = hardwareMap.get(DcMotor.class, "S");
        Intake = hardwareMap.get(DcMotor.class, "I");
        push = hardwareMap.get(Servo.class, "P");









        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        Shoot.setDirection(DcMotorSimple.Direction.FORWARD);
        Intake.setDirection(DcMotorSimple.Direction.REVERSE);
        push. setDirection(Servo.Direction.REVERSE);






        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shoot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);






        BR.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        FL.setPower(0);
        Shoot.setPower(0);
        Intake.setPower(0);
        push.setPosition(0.6);

    }




    @Override
    public void loop()
    {
        powerLX = gamepad1.left_stick_x/1.4;
        powerLY = gamepad1.right_stick_x/1.4;
        powerRX = gamepad1.left_stick_y/1.4;
        powerRY = gamepad1.right_stick_y/1.4;





        robotAngle = Math.atan2(powerLY, powerLX);
        telemetry.addData("Robot angle:", robotAngle);
        telemetry.addData("powerRX: ", gamepad1.right_stick_x);
        telemetry.addData("powerRY: ", gamepad1.right_stick_y);
        telemetry.addData("powerLX: ", gamepad1.left_stick_x);
        telemetry.addData("powerLY: ", gamepad1.left_stick_y);
        telemetry.addData("Shoot: ", gamepad1.right_bumper);
        telemetry.addData("Intake: ", gamepad1.left_bumper);






        telemetry.addData("BR: ", BR.getPower());
        telemetry.addData("BL: ", BL.getPower());
        telemetry.addData("FR: ", FR.getPower());
        telemetry.addData("FL: ", FL.getPower());
        telemetry.addData("Shoot: ", Shoot.getPower());
        telemetry.addData("Intake: ", Intake.getPower());


        telemetry.update();




        PowerMultiplier = Math.sqrt((Math.pow(powerLX, 2) + Math.pow(powerLY, 2)));




        lf = (PowerMultiplier*Math.sin(robotAngle-(Math.PI/4))) - powerRX;
        rb = (PowerMultiplier*Math.sin(robotAngle-(Math.PI/4))) + powerRX;
        lb = (PowerMultiplier*-1*(Math.sin(robotAngle+(Math.PI/4)))) - powerRX;
        rf = (PowerMultiplier*-1*(Math.sin(robotAngle+(Math.PI/4)))) + powerRX;




        BR.setPower(rb);
        BL.setPower(lb);
        FR.setPower(rf);
        FL.setPower(lf);
        if (gamepad1.x && !t) {
            // When Y is pressed, flip down
            push.setPosition(1.4);
            timer.reset();
            t = true;
        } else if (t && !gamepad1.x &&timer.seconds() > 0.3) {
            // After Y is released for 0.1s, flip back up
            push.setPosition(0.6);
            t = false;
        }
        //TOGGLE
        // Stores the previous state of the button
        previousGamepad = currentGamepad;
        // Read the current state of bumper
        currentGamepad = gamepad1.left_bumper;
        if (currentGamepad && !previousGamepad) {
            motorOn = !motorOn;
            if (motorOn) {
                Intake.setPower(0.7);
            } else {
                Intake.setPower(0);
            }
        }
        //TOGGLE
        // Stores the previous state of the button
        previousGamepad2 = currentGamepad2;
        // Read the current state of bumper
        currentGamepad2 = gamepad1.right_bumper;
        if (currentGamepad2 && !previousGamepad2) {
            motorOn2 = !motorOn2;
            if (motorOn2) {
                Shoot.setPower(0.5);
            } else {
                Shoot.setPower(0);
            }
        }





    }
}
