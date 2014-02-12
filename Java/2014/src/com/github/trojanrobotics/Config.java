package com.github.trojanrobotics;

public class Config {
	static final boolean TEST_BOT = false;
	
	static final int MAIN_JOYSTICK = 1;
	static final int SECONDARY_JOYSTICK = 2;
	
	static final int[] COMPRESSOR = {5, 8};
	
//	Motors: Main Bot
	static final int[] LDRIVE = {1, 2};
	static final int[] RDRIVE = {3, 4};
	
	static final int LIGHTS = 2;
	
	static final int GEAR_SHIFT_SOLENOID = 5;
	
//	Motors: Test Bot
	static final int[] TEST_LDRIVE = {1, 2};
	static final int[] TEST_RDRIVE = {3, 4};
        
	//TODO: FIND THE REAL NUMBERS HERE 
    static final int RETRIEVAL_ANGLE_MOTOR = 4; 
    static final int BELT_MOTOR = 5; 
	static final int POTENTIOMETER = 10;
	static final int[] ULTRASONIC = {7, 9};
      
	static final int[] LEFT_ENCODER = {3, 4};
	static final int[] RIGHT_ENCODER = {5, 6};
            
	static final int WINCH_MOTOR = 10;
	static final int WINCH_SWITCH = 11;
	static final int WINCH_RELEASE_SOLENOID = 12;
	static final int WINCH_RETRACT_SOLENOID = 13;
	static final int WINCH_WAIT = 5000;


	//pid stuffz
	static final double[] PID = { 0.15, 0.0, 1.0 };
	static final double LEFT_DRIVE_DPP = (Math.PI * 4.0) / 360;
	static final double RIGHT_DRIVE_DPP = (Math.PI * 4.0) / 360;
	
	static final int SHOOT_POSITION = 90;
	static final int RETRIEVE_POSITION = 30;
	static final int HOME_POSITION = 180;
	static final int MAX_POSITION = 190;
	static final int MIN_POSITION = 20;
	
	static final int SHOOT_POSITION_BUTTON = 6;
	static final int RETRIEVE_POSITION_BUTTON = 7;
	static final int HOME_POSITION_BUTTON = 8;
}
