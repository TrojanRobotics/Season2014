package com.github.trojanrobotics;

public class Config {
	static final boolean TEST_BOT = false;
	
	static final int MAIN_JOYSTICK = 1;
	static final int SECONDARY_JOYSTICK = 2;
	
	static final int[] COMPRESSOR = {4, 8};
	
//	Motors: Main Bot
	static final int[] LDRIVE = {3, 1};
	static final int[] RDRIVE = {4, 2};
	
//	Motors: Test Bot
	static final int[] TEST_LDRIVE = {1, 2};
	static final int[] TEST_RDRIVE = {3, 4};
        
	//TODO: FIND THE REAL NUMBERS HERE 
    static final int RETRIEVAL_MOTOR = 4; 
    static final int BELT_MOTOR = 5; 
	static final int[] ULTRASONIC = {7, 9};
      
    static final int[] LEFT_ENCODER = {3, 4};
	static final int[] RIGHT_ENCODER = {5, 6};
            
	static final int WINCH_MOTOR = 10;
	static final int WINCH_SWITCH = 11;
        
	//pid stuffz
	static final double[] PID = { 0.15, 0.0, 1.0 };
	static final double LEFT_DRIVE_DPP = (Math.PI * 4.0) / 360;
	static final double RIGHT_DRIVE_DPP = (Math.PI * 4.0) / 360;
}
