/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.platform.posix.natives.Time;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
//	Jaguar jag9 = new Jaguar(9);
	Joystick joystickRight = new Joystick(Config.JOYSTICK_RIGHT);
	Servo servoY = new Servo(Config.SERVO_Y);
	Servo servoX = new Servo(Config.SERVO_X);
	Compressor compressor1 = new Compressor(Config.COMPRESSOR_RIGHT[0], Config.COMPRESSOR_RIGHT[1]);
	JoystickButton button1 = new JoystickButton(joystickRight, 1);
	double servoAngleY;
	double servoAngleX;

	public void robotInit() {
		servoAngleY = servoY.getAngle();
		servoAngleX = servoX.getAngle();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	public double getThreshold(double input) {
		if (input > 1.0) {
			return 1.0;
		}
		if (input < 0.0) {
			return 0.0;
		}
		return input;
	}

	public void teleopPeriodic() {
		
		servoAngleY = servoY.get();
		servoAngleX = servoX.get();
		double joyStickY = Math.floor(joystickRight.getY());
		double joyStickX = Math.floor(joystickRight.getX());

		if (joyStickY > 0.5) {
			servoAngleY += 0.005;
			System.out.println("Adding to the servoAngle\n");
		} else if (joyStickY < -0.5) {
			servoAngleY -= 0.005;
			System.out.println("Subtracting from the servoAngle\n");
		} 
		else
		{
//			servoAngleY = 0.0;
		}

		if (joyStickX > 0.5) {
			servoAngleX += 0.005;
			System.out.println("Adding to the servoAngle\n");
		} else if (joyStickX < -0.5) {
			servoAngleX -= 0.005;
			System.out.println("Subtracting from the servoAngle\n");
		}
		else
		{
//			servoAngleX = 0.0;
		}
		// TODO: TIMER

		System.out.println("servoAngle " + servoAngleX);


//		servo9.set(servoAngle);
//		System.out.println("Raw value:     " + servoAngle);
//		System.out.println("Rounded value: " + getThreshold(servoAngle));
		servoY.set(getThreshold(servoAngleY));
		servoX.set(getThreshold(servoAngleX));

//		System.out.println(getThreshold(servoAngle));

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		while (button1.get()) {

			System.out.println(compressor1.enabled());
		}
		compressor1.stop();
	}
}