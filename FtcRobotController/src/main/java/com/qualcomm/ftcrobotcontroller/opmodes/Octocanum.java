package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;


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

    DcMotor frontLeftActuator;
    DcMotor frontRightActuator;
    DcMotor backLeftActuator;
    DcMotor backRightActuator;

    //Actuator position value
    int frontRight = 0;
    int frontLeft = 0;
    int backRight = 0;
    int backLeft = 0;

    double motorDelta = 50;

    boolean bTriggered = false;
    boolean aTriggered = false;
    boolean yTriggered = false;
    boolean xTriggered = false;
    boolean upPadTriggered = false;
    boolean leftPadTriggered = false;
    boolean rightPadTriggered = false;
    boolean downPadTriggered = false;

    int decrement(int position){
        position -= motorDelta;
        return position;
    }

    int increment(int position){
        position += motorDelta;
        return position;
    }

    /*
    //Copy and Pasted Mecanum Drive Body
    boolean tractionDown = true;
    double deadBand = .05;
    double speedModifier = 1;
    double leftXvalue = gamepad1.left_stick_x;
    double leftYvalue = gamepad1.left_stick_y;
    double rightXvalue = gamepad1.right_stick_x;
    double rightYvalue = gamepad1.right_stick_y;

    double powerFrontLeft = (leftXvalue + leftYvalue - rightXvalue) * speedModifier;
    double powerFrontRight = (-leftXvalue + leftYvalue - rightXvalue) * speedModifier;
    double powerBackLeft = (-leftXvalue + leftYvalue + rightXvalue) * speedModifier;
    double powerBackRight = (leftXvalue + leftYvalue + rightXvalue) * speedModifier;

    void mecanumDrive(){
        frontLeftMotor.setPower((powerFrontLeft < deadBand && powerFrontLeft > -deadBand) ? 0:powerFrontLeft);
        frontRightMotor.setPower((powerFrontRight < deadBand && powerFrontRight > -deadBand) ? 0:powerFrontRight);
        backLeftMotor.setPower((powerBackLeft < deadBand && powerBackLeft > -deadBand) ? 0:powerBackLeft);
        backRightMotor.setPower((powerBackRight < deadBand && powerBackRight > -deadBand) ? 0:powerBackRight);
    }
    */

    @Override
    public void init() {


        frontRightMotor = hardwareMap.dcMotor.get("motor_front_right");
        frontLeftMotor = hardwareMap.dcMotor.get("motor_front_left");
        backRightMotor = hardwareMap.dcMotor.get("motor_back_right");
        backLeftMotor = hardwareMap.dcMotor.get("motor_back_left");

        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftActuator = hardwareMap.dcMotor.get("actuator_front_left");
        frontRightActuator = hardwareMap.dcMotor.get("actuator_front_right");
        backLeftActuator = hardwareMap.dcMotor.get("actuator_back_left");
        backRightActuator = hardwareMap.dcMotor.get("actuator_back_right");

        frontLeftActuator.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        frontRightActuator.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        backLeftActuator.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        backRightActuator.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        frontLeft = frontLeftActuator.getCurrentPosition();
        frontRight = frontRightActuator.getCurrentPosition();
        backLeft = backLeftActuator.getCurrentPosition();
        backRight = backRightActuator.getCurrentPosition();

    }

    @Override
    public void loop() {
        double leftVal = gamepad1.left_stick_y;
        double rightVal = gamepad1.right_stick_y;

        frontLeftMotor.setPower(leftVal);
        backLeftMotor.setPower(leftVal);
        frontRightMotor.setPower(rightVal);
        backRightMotor.setPower(rightVal);

        if(gamepad1.left_stick_button){
            int targetPosition = 0;
            frontRight = 0;
            frontLeft = 0;
            backRight = 0;
            backLeft = 0;
            frontLeftActuator.setTargetPosition(targetPosition);
            backLeftActuator.setTargetPosition(targetPosition);
            frontRightActuator.setTargetPosition(targetPosition);
            backRightActuator.setTargetPosition(targetPosition);
        }

        if(gamepad1.right_stick_button){
            int targetPosition =100;
            frontRight = 100;
            frontLeft = 100;
            backRight = 100;
            backLeft = 100;
            frontLeftActuator.setTargetPosition(targetPosition);
            backLeftActuator.setTargetPosition(targetPosition);
            frontRightActuator.setTargetPosition(targetPosition);
            backRightActuator.setTargetPosition(targetPosition);
        }

        if(gamepad1.y && !yTriggered){
            frontLeft = increment(frontLeft);
            frontLeftActuator.setTargetPosition(frontLeft);
            yTriggered = true;
        } else if(!gamepad1.y){
            yTriggered = false;
        }

        if(gamepad1.dpad_up && !upPadTriggered){
            frontLeft = decrement(frontLeft);
            frontLeftActuator.setTargetPosition(frontLeft);
            upPadTriggered = true;
        } else if(!gamepad1.dpad_up){
            upPadTriggered = false;
        }

        if(gamepad1.x && !xTriggered){
            backLeft = increment(backLeft);
            backLeftActuator.setTargetPosition(backLeft);
            xTriggered =true;
        } else if (!gamepad1.x){
            xTriggered = false;
        }

        if(gamepad1.dpad_left && !leftPadTriggered){
            backLeft = decrement(backLeft);
            backLeftActuator.setTargetPosition(backLeft);
            leftPadTriggered =true;
        } else if (!gamepad1.dpad_left){
            leftPadTriggered =false;
        }

        if(gamepad1.a &&!aTriggered){
            backRight = increment(backRight);
            backRightActuator.setTargetPosition(backRight);
            aTriggered = true;
        } else if (!gamepad1.a){
            aTriggered = false;
        }

        if(gamepad1.dpad_down && !downPadTriggered){
            backRight = decrement(backRight);
            backRightActuator.setTargetPosition(backRight);
            downPadTriggered = true;
        } else if (!gamepad1.dpad_down){
            downPadTriggered = false;
        }

        if(gamepad1.b && !bTriggered){
            frontRight = increment(frontRight);
            frontRightActuator.setTargetPosition(frontRight);
            bTriggered =true;
        } else if(!gamepad1.b){
            bTriggered =false;
        }

        if(gamepad1.dpad_right && !rightPadTriggered){
            frontRight = decrement(frontRight);
            frontRightActuator.setTargetPosition(frontRight);
            rightPadTriggered =true;
        } else if(!gamepad1.dpad_right){
            rightPadTriggered =false;
        }
    }
}
