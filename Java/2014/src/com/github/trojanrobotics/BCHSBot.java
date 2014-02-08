package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DriverStationLCD;

public class BCHSBot extends IterativeRobot {
	Joystick mainJoystick, secondaryJoystick;
	double servoAngleY;
	double servoAngleX;
	double x, y;
	Chasis chasis;
	boolean autoRunOnce;

	public void robotInit() {
		mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
		secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
		if(Config.TEST_BOT){
			chasis = new Chasis(Config.TEST_LDRIVE, Config.TEST_RDRIVE, Config.ULTRASONIC, Config.LEFT_ENCODER, Config.RIGHT_ENCODER);
		} else {
			chasis = new Chasis(Config.LDRIVE, Config.RDRIVE, Config.ULTRASONIC, Config.LEFT_ENCODER, Config.RIGHT_ENCODER);
		}
		LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talOne);
		LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talTwo);
		LiveWindow.addActuator("Right Side", "Value", chasis.leftSide.talOne);
		LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talTwo);
		LiveWindow.addActuator("PID", "leftPID", chasis.leftSidePID);
		LiveWindow.addActuator("PID", "rightPID", chasis.rightSidePID);
	}
	
	public void disabledPeriodic(){
		chasis.stop();
		chasis.reset();
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
		
	}
}