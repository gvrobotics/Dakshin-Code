package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Btele extends OpMode
{
    public DcMotor Shoot;

    @Override
    public void init()
    {
        Shoot = hardwareMap.get(DcMotor.class, "Shoot");









        Shoot.setDirection(DcMotorSimple.Direction.FORWARD);








        Shoot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);








        Shoot.setPower(0);


    }




    @Override
    public void loop()
    {







        if(gamepad1.x) {
            Shoot.setPower(0.4);
        }
        if(gamepad1.b){
            Shoot.setPower(0);
        }
        if(gamepad1.y){

            Shoot.setPower(0.7);
        }






    }
}
