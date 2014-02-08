package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class BCHSBot extends IterativeRobot {
	Joystick mainJoystick, secondaryJoystick;
	Servo servoY = new Servo(Config.SERVO_Y);
	Servo servoX = new Servo(Config.SERVO_X);
	double servoAngleY;
	double servoAngleX;
	double x, y;
	Chasis chasis = new Chasis(Config.LDRIVE, Config.RDRIVE, Config.ULTRASONIC, Config.LEFT_ENCODER, Config.RIGHT_ENCODER);
	boolean autoRunOnce;
	
	public void robotInit() {
		mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
		secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
		servoAngleY = servoY.getAngle();
		servoAngleX = servoX.getAngle();	
		
		LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talOne);
		LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talTwo);
		LiveWindow.addActuator("Right Side", "Value", chasis.leftSide.talOne);
		LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talTwo);
		LiveWindow.addActuator("PID", "leftPID", chasis.leftSidePID);
		LiveWindow.addActuator("PID", "rightPID", chasis.rightSidePID);
	}
	
	public void disabledPeriodic(){
		chasis.stop();
		//chasis.reset();
		autoRunOnce = true;
    }
	
	public void autonomousPeriodic() {
		System.out.println("Autonomous Periodic");
		if (autoRunOnce == true) {
			System.out.println("Autonomous");
			//chasis.set(0.5);
			autoRunOnce = false;
		}
		String clear = "                         ";
		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, clear);
		chasis.driverStationLCD.updateLCD();
		
		String s = Double.toString(chasis.rightSideEncoder.getDistance());
		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, s);
		
		String s1 = Double.toString(chasis.leftSideEncoder.getDistance());
		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser2, 1, s1);
		chasis.driverStationLCD.updateLCD();
		
                //once moved to firing position, release winch to fire the ball
                //bonus: fire when hot goal
                if (chasis.getPID() == 15)
                {
                    chasis.winch.release();
                }
	}
	
	public void teleopPeriodic() {
		servoAngleY = servoY.get();
		servoAngleX = servoX.get();
		double joyStickY = Math.floor(mainJoystick.getY());
		double joyStickX = Math.floor(mainJoystick.getX());
		
//		if (joyStickY > 0.5) {
//			servoAngleY += 0.005;
//			System.out.println("Adding to the servoAngle\n");
//		} else if (joyStickY < -0.5) {
//			servoAngleY -= 0.005;
//			System.out.println("Subtracting from the servoAngle\n");
//		}
//
//		if (joyStickX > 0.5) {
//			servoAngleX += 0.005;
//			System.out.println("Adding to the servoAngle\n");
//		} else if (joyStickX < -0.5) {
//			servoAngleX -= 0.005;
//			System.out.println("Subtracting from the servoAngle\n");
//		}
//		
//		servoY.set(Lib.limitOutput(servoAngleY));
//		servoX.set(Lib.limitOutput(servoAngleX));
		
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
		
//		String clear = "                         ";
//		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, clear);
//		chasis.driverStationLCD.updateLCD();
//		
//		String s = Double.toString(chasis.rightSideEncoder.getDistance());
//		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, s);
//		
//		String s1 = Double.toString(chasis.leftSideEncoder.getDistance());
//		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser2, 1, s1);
//		chasis.driverStationLCD.updateLCD();
//		
//		System.out.println("Distance and rate" + chasis.rightSideEncoder.getDistance() + "    " + chasis.rightSideEncoder.getRate());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		SmartDashboard.putNumber("Adam", 5);
	}
}