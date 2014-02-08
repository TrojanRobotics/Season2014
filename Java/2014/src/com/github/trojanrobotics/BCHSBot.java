package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.TimerTask;

public class BCHSBot extends IterativeRobot {

    Joystick mainJoystick, secondaryJoystick;
    Servo servoY = new Servo(Config.SERVO_Y);
    Jaguar retrievalMotor, beltMotor, winchMotor;
    Encoder encoder1;
    Winch winch;
    Retrieval retrieval;
    Servo servoX = new Servo(Config.SERVO_X);
    Compressor compressor1 = new Compressor(Config.COMPRESSOR_RIGHT[0], Config.COMPRESSOR_RIGHT[1]);
    double servoAngleY;
    double servoAngleX;
    double x, y;
    Chasis chasis = new Chasis(Config.LDRIVE, Config.RDRIVE, Config.ULTRASONIC);
    LimitSwitch limitSwitch;
    java.util.Timer timer;

    public void robotInit() {
        limitSwitch = new LimitSwitch(Config.DIGITAL_INPUT, chasis.leftSide);
        mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
        secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
        retrievalMotor = new Jaguar(Config.RETRIEVAL_MOTOR);
        beltMotor = new Jaguar(Config.BELT_MOTOR);
        winchMotor = new Jaguar(Config.WINCH_MOTOR);
        servoAngleY = servoY.getAngle();
        servoAngleX = servoX.getAngle();
        retrieval = new Retrieval(retrievalMotor, beltMotor, encoder1, retrieval.winchMotor);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {

        servoAngleY = servoY.get();
        servoAngleX = servoX.get();
        double joyStickY = Math.floor(mainJoystick.getY());
        double joyStickX = Math.floor(mainJoystick.getX());

        if (secondaryJoystick.getRawButton(3)) {                    //UP
            servoAngleY += 0.005;
            System.out.println("Adding to the servoAngle\n");
        } else if (secondaryJoystick.getRawButton(2)) {             //DOWN
            servoAngleY -= 0.005;
            System.out.println("Subtracting from the servoAngle\n");
        }

        if (secondaryJoystick.getRawButton(5)) {
            servoAngleX += 0.005;
            System.out.println("Adding to the servoAngle\n");
        } else if (secondaryJoystick.getRawButton(4)) {
            servoAngleX -= 0.005;
            System.out.println("Subtracting from the servoAngle\n");
        }

        servoY.set(Lib.limitOutput(servoAngleY));
        servoX.set(Lib.limitOutput(servoAngleX));

        SmartDashboard.putNumber("Servo Y:", Lib.limitOutput(servoAngleY));
        SmartDashboard.putNumber("Servo X:", Lib.limitOutput(servoAngleX));

        x = mainJoystick.getX();
        y = mainJoystick.getY();

        x = Lib.signSquare(x);
        y = Lib.signSquare(y);

        //System.out.println(y);
        //System.out.println(x);
        chasis.rightSide.set(Lib.limitOutput(y + x));
        chasis.leftSide.set(-Lib.limitOutput(y - x));

        

        if (mainJoystick.getRawButton(1)) //Shoot
        {
            retrieval.setArmPosition(Retrieval.Direction.up);
            retrieval.winchMotor.release();
            retrieval.startTimer();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }

}
