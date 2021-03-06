package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DriverStationLCD;

public class BCHSBot extends IterativeRobot {
	Joystick mainJoystick, secondaryJoystick;
	double servoAngleY;
	double servoAngleX;
	double x, y;
	double inches;
	double retrievalAngle,joystickRetrievalAngle, retrievalMotor;
	Chasis chasis;
	boolean autoRunOnce;
	Camera cam;

	public void robotInit() {
		mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
		secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
		cam = new Camera();
		
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
		
//                //once moved to firing position, release winch to fire the ball
//                //bonus: fire when hot goal
//                if (chasis.getPID() == 15)
//                {
//                    chasis.winch.release();
//                }
	}
	
	public void teleopPeriodic() {
		inches = chasis.ultrasonic.getRangeInches();
		System.out.println(inches);
//		if(inches >= 36 && inches <= 72){
//			Config.LIGHTS;
//		}
		if (secondaryJoystick.getRawButton(10)){
			chasis.gearShift(Chasis.Gear.gearOne);
		} else if (secondaryJoystick.getRawButton(11)){
			chasis.gearShift(Chasis.Gear.gearTwo);
		}
		
		if (mainJoystick.getRawButton(6)){
			chasis.retrieval.beltMotor.set(0.5);
		} else if (mainJoystick.getRawButton(7)){
			chasis.retrieval.beltMotor.set(-0.5);
		}
	
		if(secondaryJoystick.getRawButton(Config.HOME_POSITION_BUTTON)){
			chasis.retrieval.setEnabled(true);
			chasis.retrieval.setAngleRetrieval(Config.HOME_POSITION);
		} else if(secondaryJoystick.getRawButton(Config.SHOOT_POSITION_BUTTON)){
			chasis.retrieval.setEnabled(true);
			chasis.retrieval.setAngleRetrieval(Config.SHOOT_POSITION);
		} else if(secondaryJoystick.getRawButton(Config.RETRIEVE_POSITION_BUTTON)){
			chasis.retrieval.setEnabled(true);
			chasis.retrieval.setAngleRetrieval(Config.RETRIEVE_POSITION);
		}
		
		x = mainJoystick.getX();
        y = mainJoystick.getY();
        x = Lib.signSquare(x);
        y = Lib.signSquare(y);

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
		
		if (secondaryJoystick.getRawButton(8)){
			chasis.retrieval.setArmPosition(Direction.up);
		} else if (secondaryJoystick.getRawButton(9)){
			chasis.retrieval.setArmPosition(Direction.down);
		}
		
        if (secondaryJoystick.getTrigger()){ //shoot
            chasis.retrieval.setArmPosition(Direction.up);
            chasis.retrieval.winch.release();
            chasis.retrieval.startTimer();
        }
    }

    public void testPeriodic() {

    }
}
