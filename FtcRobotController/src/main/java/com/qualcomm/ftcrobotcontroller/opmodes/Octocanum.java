package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Jordan Burklund on 7/30/2015.
 * An example linear op mode where the pushbot 
 * will run its motors unless a touch sensor 
 * is pressed.
 */
public class Octocanum extends OpMode {
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;

    @Override
    public void init() {
        frontRightMotor = hardwareMap.dcMotor.get("motor_front_right");
        frontLeftMotor = hardwareMap.dcMotor.get("motor_front_left");
        backRightMotor = hardwareMap.dcMotor.get("motor_back_left");
        backLeftMotor = hardwareMap.dcMotor.get("motor_back_right");

        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double leftVal = gamepad1.left_stick_y;
        double rightVal = gamepad1.right_stick_y;

        frontLeftMotor.setPower(leftVal);
        backLeftMotor.setPower(leftVal);
        frontRightMotor.setPower(rightVal);
        backRightMotor.setPower(rightVal);
    }
}
