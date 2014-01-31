package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BCHSBot extends IterativeRobot {
	Joystick mainJoystick, secondaryJoystick;
	Servo servoY = new Servo(Config.SERVO_Y);
	Servo servoX = new Servo(Config.SERVO_X);
	Compressor compressor1 = new Compressor(Config.COMPRESSOR_RIGHT[0], Config.COMPRESSOR_RIGHT[1]);
	double servoAngleY;
	double servoAngleX;
        
	double x, y;
	Chasis chasis = new Chasis(Config.LDRIVE, Config.RDRIVE, Config.ULTRASONIC);
        
	public void robotInit() {
		mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
		secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
		
		servoAngleY = servoY.getAngle();
		servoAngleX = servoX.getAngle();
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

		if (joyStickY > 0.5) {
			servoAngleY += 0.005;
			System.out.println("Adding to the servoAngle\n");
		} else if (joyStickY < -0.5) {
			servoAngleY -= 0.005;
			System.out.println("Subtracting from the servoAngle\n");
		}

		if (joyStickX > 0.5) {
			servoAngleX += 0.005;
			System.out.println("Adding to the servoAngle\n");
		} else if (joyStickX < -0.5) {
			servoAngleX -= 0.005;
			System.out.println("Subtracting from the servoAngle\n");
		}

		System.out.println("servoAngle " + servoAngleX);
		
		servoY.set(Lib.limitOutput(servoAngleY));
		servoX.set(Lib.limitOutput(servoAngleX));
		
		SmartDashboard.putNumber("Servo Y:", Lib.limitOutput(servoAngleY));
		SmartDashboard.putNumber("Servo X:", Lib.limitOutput(servoAngleX));
		
		x = mainJoystick.getX();
        y = mainJoystick.getY();

        x = Lib.signSquare(x);
        y = Lib.signSquare(y);
        
        System.out.println(y);
        System.out.println(x);
        
        chasis.rightSide.set(Lib.limitOutput(y + x));
        chasis.leftSide.set(-Lib.limitOutput(y - x));

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		
	}
}