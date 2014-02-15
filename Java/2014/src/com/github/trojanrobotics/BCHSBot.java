package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BCHSBot extends IterativeRobot {
	Joystick mainJoystick, secondaryJoystick;
	double servoAngleY;
	double servoAngleX;
	double x, y;
	double inches;
	double retrievalAngle,joystickRetrievalAngle, retrievalMotor;
	Chasis chasis;
	boolean autoRunOnce, gear;
	Camera cam;

	public void robotInit() {
		mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
		secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
		//cam = new Camera();
		
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
		chasis.retrieval.setEnabled(false);
		autoRunOnce = true;
		chasis.retrieval.setAngleRetrieval(chasis.retrieval.potentiometer.getAngle());
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
		if(chasis.currentGear){
			SmartDashboard.putString("Current gear", "High Gear");
		} else {			
			SmartDashboard.putString("Current gear", "Low Gear");
		}
		
		SmartDashboard.putBoolean("kWinchLimitSwitch", chasis.retrieval.winch.limitSwitch.get());
//		inches = chasis.ultrasonic.getRangeInches();
		////System.out.println(inches);
//		if(inches >= 36 && inches <= 72) {
//			SmartDashboard.putString("kInRange", "We're in range");
//		} else {
//			SmartDashboard.putString("kInRange", "We're not in range");
//		}
		SmartDashboard.putBoolean("kBeamSensor", chasis.retrieval.beamSensor.get());
		SmartDashboard.putNumber("kUltrasonic", inches);
		SmartDashboard.putNumber("kPotentiometerAngle", chasis.retrieval.potentiometer.getAngle());
		
		if (mainJoystick.getRawButton(6)){
			chasis.gearShift(Chasis.Gear.gearOne);
		} else if (mainJoystick.getRawButton(7)){
			chasis.gearShift(Chasis.Gear.gearTwo);
		}
		
		if (mainJoystick.getRawButton(9)){
			//System.out.println("BELT 0.5");
			chasis.retrieval.beltMotor.set(0.5);
		} else if (mainJoystick.getRawButton(8)){
			//System.out.println("BELT -0.5");
			chasis.retrieval.beltMotor.set(-0.5);
		} else {
			chasis.retrieval.beltMotor.set(0.0);
		}
		
		if (secondaryJoystick.getRawButton(8)){
			chasis.retrieval.winch.retract();
			//System.out.println("Pressed button");
		}
		
		if (secondaryJoystick.getRawButton(9)){
			chasis.retrieval.winch.release();
		}
		
		if (secondaryJoystick.getRawButton(Config.RETRIEVAL_MANUAL_BUTTON)){
			//System.out.println("ZZZZZZ");
			chasis.retrieval.setEnabled(false);
			double secondaryYAxis = Lib.limitOutput(secondaryJoystick.getY() * 0.5);
			//System.out.println("Angle == " + secondaryYAxis);
			SmartDashboard.putNumber("kOverride", secondaryYAxis);
			chasis.retrieval.setRetrieval(secondaryYAxis);
			chasis.retrieval.setAngleRetrieval(chasis.retrieval.potentiometer.getAngle());
		} else {
			chasis.retrieval.setEnabled(true);
			//System.out.println("XXXXXXX");
			if(secondaryJoystick.getRawButton(Config.HOME_POSITION_BUTTON)){
				chasis.retrieval.setAngleRetrieval(Config.HOME_POSITION);
			} else if(secondaryJoystick.getRawButton(Config.SHOOT_POSITION_BUTTON)){
				chasis.retrieval.setAngleRetrieval(Config.SHOOT_POSITION);
			} else if(secondaryJoystick.getRawButton(Config.RETRIEVE_POSITION_BUTTON)){
				chasis.retrieval.setAngleRetrieval(Config.RETRIEVE_POSITION);
			}
		}
		
//		if(chasis.retrieval.winch.canMoveRetrieval){
//			if (chasis.retrieval.canFire() && secondaryJoystick.getTrigger()){ //shoot
//				chasis.retrieval.setAngleRetrieval(Config.SHOOT_POSITION);
//				chasis.retrieval.setArmPosition(Direction.up);
//				chasis.retrieval.winch.release();
//				chasis.retrieval.startTimer();
//		} else {
//				
//			}
//		}
		
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
		
		if (secondaryJoystick.getRawButton(4)){
			chasis.retrieval.setArmPosition(Direction.up);
		} else if (secondaryJoystick.getRawButton(5)){
			chasis.retrieval.setArmPosition(Direction.down);
		}
		
    }

    public void testPeriodic() {

    }
}
