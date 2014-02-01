package com.github.trojanrobotics;

public class Config {
	static final int SERVO_Y = 9;
	static final int SERVO_X = 10;
	
	static final int MAIN_JOYSTICK = 1;
	static final int SECONDARY_JOYSTICK = 2;
	
	static final int[] COMPRESSOR = {4, 8};
	static final int[] LDRIVE = {1, 2};
	static final int[] RDRIVE = {3, 4};
        
        //TODO: FIND THE REAL NUMBERS HERE 
        static final int RETRIEVAL_MOTOR = 4; 
        static final int BELT_MOTOR = 5; 
        static final int[] ULTRASONIC = {6, 7};
      
        static final int[] LEFT_ENCODER = {1,2};
        static final int[] RIGHT_ENCODER = {3,4};
            
        static final int WINCH_MOTOR = 10;
        static final int WINCH_SWITCH = 11;
        
        //pid stuffz
        static final double[] PID = { 0.0, 0.0, 0.0 };
        static final double leftDriveDPP = 5.0;
        static final double rightDriveDPP = 5.0;
	//static final double[] SHOOTER_PID = { 0.5, 0.0, 0.0 };
}
