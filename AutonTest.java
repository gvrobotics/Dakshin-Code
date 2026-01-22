package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class AutonTest extends LinearOpMode {

    double cpr = 7;
    double cpi = (7/Math.PI*96)/25.4;
    double cpd = (7*5.2)/360;

    double cprIntake = 537.6;
    double cpiIntake = (537.6/Math.PI*60)/25.4;
    double cpdIntake = (537.6*19.2/360);

    

    public DcMotor BR, BL, FR, FL, IN, OU;

    public enum robotMotion
    {
        right, left;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        // hardware map
        BR = hardwareMap.get(DcMotor.class, "BR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        IN = hardwareMap.get(DcMotor.class, "IN");
        OU = hardwareMap.get(DcMotor.class, "OU");

        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        IN.setDirection(DcMotorSimple.Direction.FORWARD);
        OU.setDirection(DcMotorSimple.Direction.FORWARD);

        // set mode
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IN.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        OU.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        IN.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IN.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        OU.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        OU.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set initial power/position
        BR.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        FL.setPower(0);
        IN.setPower(0);
        OU.setPower(0);


        //motor powers:
        //forward/backward: 0.5
        //strafe: 0.5
        //right/left: 0.3

        waitForStart();
        //TODO: Code Here!
        Outake(0,0.5);
        forward(18, 0.5);
       movementRL(robotMotion.right, 45, 0.3);
       forward(3, 0.5);
       Intake(3, 0.5);
       backward(3, 0.5);
       movementRL(robotMotion.left, 45, 0.3);
       backward(18, 0.5);
       Outake(3, 0.5);

       forward(42, 0.5);
        movementRL(robotMotion.right, 45, 0.3);
        forward(3, 0.5);
        Intake(3, 0.5);
        backward(3, 0.5);
        backward(42, 0.5);
        Outake(3, 0.5);

        forward(66, 0.5);
        movementRL(robotMotion.right, 45, 0.3);
        forward(3, 0.5);
        Intake(3, 0.5);
        backward(3, 0.5);
        backward(66, 0.5);
        Outake(3, 0.5);






    }

    private void forward(double inch,  double power) {
        // calculate new position for (wheel) motors
        int a = (int)(BR.getCurrentPosition() + (inch * cpi));
        int b = (int)(BL.getCurrentPosition() + (inch * cpi));
        int c = (int)(FR.getCurrentPosition() + (inch * cpi));
        int d = (int)(FL.getCurrentPosition() + (inch * cpi));

        // sets new position for motors
        BR.setTargetPosition(a);
        BL.setTargetPosition(b);
        FL.setTargetPosition(c);
        FR.setTargetPosition(d);

        // sets desired power for motors
        BR.setPower(power);
        BL.setPower(power);
        FR.setPower(power);
        FL.setPower(power);

        // make motors run to position
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // loop to get telemetry while motors are running
        while (BR.isBusy() && BL.isBusy() && FR.isBusy() && FL.isBusy()) {
            telemetry.addLine("Forward");

            telemetry.addData("BR Target", a);
            telemetry.addData("BL Target", b);
            telemetry.addData("FR Target", c);
            telemetry.addData("FL Target", d);

            telemetry.addData("BR Current", BR.getCurrentPosition());
            telemetry.addData("BL Current", BL.getCurrentPosition());
            telemetry.addData("FR Current", FR.getCurrentPosition());
            telemetry.addData("FL Current", FL.getCurrentPosition());

            telemetry.update();
        }
        // stop motors
        BR.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        FL.setPower(0);
    }

    private void backward(double inch,  double power) {
        // calculate new position for (wheel) motors
        int a = (int)(BR.getCurrentPosition() - (inch * cpi));
        int b = (int)(BL.getCurrentPosition() - (inch * cpi));
        int c = (int)(FR.getCurrentPosition() - (inch * cpi));
        int d = (int)(FL.getCurrentPosition() - (inch * cpi));

        // sets new position for motors
        BR.setTargetPosition(a);
        BL.setTargetPosition(b);
        FR.setTargetPosition(c);
        FL.setTargetPosition(d);

        // sets desired power for motors
        BR.setPower(power);
        BL.setPower(power);
        FR.setPower(power);
        FL.setPower(power);

        // make motors run to position
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // loop to get telemetry while motors are running
        while (BR.isBusy() && BL.isBusy() && FR.isBusy() && FL.isBusy()) {
            telemetry.addLine("Backward");

            telemetry.addData("BR Target", a);
            telemetry.addData("BL Target", b);
            telemetry.addData("FR Target", c);
            telemetry.addData("FL Target", d);

            telemetry.addData("BR Current", BR.getCurrentPosition());
            telemetry.addData("BL Current", BL.getCurrentPosition());
            telemetry.addData("FR Current", FR.getCurrentPosition());
            telemetry.addData("FL Current", FL.getCurrentPosition());

            telemetry.update();
        }
        // stop motors
        BR.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        FL.setPower(0);
    }

    private void strafeLeft (double inch,  double power) {
        // FL and BR go backward, FR and BL go forward
        BR.setTargetPosition((int) (BR.getCurrentPosition() - (inch * cpi)));
        BL.setTargetPosition((int) (BL.getCurrentPosition() + (inch * cpi)));
        FR.setTargetPosition((int) (FR.getCurrentPosition() + (inch * cpi)));
        FL.setTargetPosition((int) (FL.getCurrentPosition() - (inch * cpi)));

        // sets desired power for motors
        BR.setPower(-power);
        BL.setPower(power);
        FR.setPower(power);
        FL.setPower(-power);

        // make motors run to position
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // loop to get telemetry while motors are running
        while (BR.isBusy() && BL.isBusy() && FR.isBusy() && FL.isBusy()) {
            telemetry.addLine("Strafe Left");

            telemetry.addData("BR Target", BR.getTargetPosition());
            telemetry.addData("BL Target", BL.getTargetPosition());
            telemetry.addData("FR Target", FR.getTargetPosition());
            telemetry.addData("FL Target", FL.getTargetPosition());

            telemetry.addData("BR Current", BR.getCurrentPosition());
            telemetry.addData("BL Current", BL.getCurrentPosition());
            telemetry.addData("FR Current", FR.getCurrentPosition());
            telemetry.addData("FL Current", FL.getCurrentPosition());

            telemetry.update();
        }
        // stop motors
        BR.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        FL.setPower(0);
    }

    private void strafeRight (double inch,  double power) {
        // FR and BL go backward, FL and BR go forward
        BR.setTargetPosition((int) (BR.getCurrentPosition() + (inch * cpi)));
        BL.setTargetPosition((int) (BL.getCurrentPosition() - (inch * cpi)));
        FR.setTargetPosition((int) (FR.getCurrentPosition() - (inch * cpi)));
        FL.setTargetPosition((int) (FL.getCurrentPosition() + (inch * cpi)));

        // sets desired power for motors
        BR.setPower(power);
        BL.setPower(-power);
        FR.setPower(-power);
        FL.setPower(power);

        // make motors run to position
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // loop to get telemetry while motors are running
        while (BR.isBusy() && BL.isBusy() && FR.isBusy() && FL.isBusy()) {
            telemetry.addLine("Strafe Right");

            telemetry.addData("BR Target", BR.getTargetPosition());
            telemetry.addData("BL Target", BL.getTargetPosition());
            telemetry.addData("FR Target", FR.getTargetPosition());
            telemetry.addData("FL Target", FL.getTargetPosition());

            telemetry.addData("BR Current", BR.getCurrentPosition());
            telemetry.addData("BL Current", BL.getCurrentPosition());
            telemetry.addData("FR Current", FR.getCurrentPosition());
            telemetry.addData("FL Current", FL.getCurrentPosition());

            telemetry.update();
        }
        // stop motors
        BR.setPower(0);
        BL.setPower(0);
        FR.setPower(0);
        FL.setPower(0);
    }

    private void movementRL(robotMotion action, double degree,  double power) {

        if(action == robotMotion.left) {
            // left is moving backwards, right is moving forwards
            BR.setTargetPosition((int) (BR.getCurrentPosition() + (degree * cpd)));
            BL.setTargetPosition((int) (BL.getCurrentPosition() - (degree * cpd)));
            FR.setTargetPosition((int) (FR.getCurrentPosition() + (degree * cpd)));
            FL.setTargetPosition((int) (FL.getCurrentPosition() - (degree * cpd)));

            // sets desired power for motors
            BR.setPower(power);
            BL.setPower(-power);
            FR.setPower(power);
            FL.setPower(-power);

            // make motors run to position
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // loop to get telemetry while motors are running
            while (BR.isBusy() && BL.isBusy() && FR.isBusy() && FL.isBusy()) {
                telemetry.addLine("Turning Left");

                telemetry.addData("BR Target", BR.getTargetPosition());
                telemetry.addData("BL Target", BL.getTargetPosition());
                telemetry.addData("FR Target", FR.getTargetPosition());
                telemetry.addData("FL Target", FL.getTargetPosition());

                telemetry.addData("BR Current", BR.getCurrentPosition());
                telemetry.addData("BL Current", BL.getCurrentPosition());
                telemetry.addData("FR Current", FR.getCurrentPosition());
                telemetry.addData("FL Current", FL.getCurrentPosition());

                telemetry.update();
            }
            // stop motors
            BR.setPower(0);
            BL.setPower(0);
            FR.setPower(0);
            FL.setPower(0);
        }

        if(action == robotMotion.right) {
            // right is moving backwards, left is moving forwards
            BR.setTargetPosition((int) (BR.getCurrentPosition() - (degree * cpd)));
            BL.setTargetPosition((int) (BL.getCurrentPosition() + (degree * cpd)));
            FR.setTargetPosition((int) (FR.getCurrentPosition() - (degree * cpd)));
            FL.setTargetPosition((int) (FL.getCurrentPosition() + (degree * cpd)));

            // sets desired power for motors
            BR.setPower(-power);
            BL.setPower(power);
            FR.setPower(-power);
            FL.setPower(power);

            // make motors run to position
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // loop to get telemetry while motors are running
            while (BR.isBusy() && BL.isBusy() && FR.isBusy() && FL.isBusy()) {
                telemetry.addLine("Turning Right");

                telemetry.addData("BR Target", BR.getTargetPosition());
                telemetry.addData("BL Target", BL.getTargetPosition());
                telemetry.addData("FR Target", FR.getTargetPosition());
                telemetry.addData("FL Target", FL.getTargetPosition());

                telemetry.addData("BR Current", BR.getCurrentPosition());
                telemetry.addData("BL Current", BL.getCurrentPosition());
                telemetry.addData("FR Current", FR.getCurrentPosition());
                telemetry.addData("FL Current", FL.getCurrentPosition());

                telemetry.update();
            }
            // stop motors
            BR.setPower(0);
            BL.setPower(0);
            FR.setPower(0);
            FL.setPower(0);
        }
    }

    private void Outake (double inch,  double power) {
        // FR and BL go backward, FL and BR go forward
        OU.setTargetPosition((int) (OU.getCurrentPosition() + (inch * cpi)));


        // sets desired power for motors
        OU.setPower(power);


        // make motors run to position
        OU.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // loop to get telemetry while motors are running
        while (OU.isBusy() && IN.isBusy() && IN.isBusy() && IN.isBusy()) {
            telemetry.addLine("Outake");

            telemetry.addData("OU Target", OU.getTargetPosition());

            telemetry.addData("OU Current", OU.getCurrentPosition());


            telemetry.update();
        }
    }
    private void Intake (double inch,  double power) {
            // FR and BL go backward, FL and BR go forward
            IN.setTargetPosition((int) (IN.getCurrentPosition() + (inch * cpi)));


            // sets desired power for motors
            IN.setPower(power);


            // make motors run to position
            IN.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // loop to get telemetry while motors are running
            while (IN.isBusy() && IN.isBusy() && IN.isBusy() && IN.isBusy()) {
                telemetry.addLine("Intake");

                telemetry.addData("IN Target", IN.getTargetPosition());

                telemetry.addData("IN Current", IN.getCurrentPosition());


                telemetry.update();
            }
}   }
