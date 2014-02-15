package com.github.trojanrobotics;

public class Config {

	static final boolean TEST_BOT = false;

	static final int MAIN_JOYSTICK = 1;
	static final int SECONDARY_JOYSTICK = 2;

	static final int[] COMPRESSOR = {13, 8};

//	Motors: Main Bot
	static final int[] LDRIVE = {3, 4};
	static final int[] RDRIVE = {1, 2};

	static final int LIGHTS = 2;

	static final int GEAR_SHIFT_SOLENOID_ONE = 1;
	static final int GEAR_SHIFT_SOLENOID_TWO = 6;

//	Motors: Test Bot
	static final int[] TEST_LDRIVE = {1, 2};
	static final int[] TEST_RDRIVE = {3, 4};

	//TODO: FIND THE REAL NUMBERS HERE 
	static final int RETRIEVAL_ANGLE_MOTOR = 6; ////5;
	static final int BELT_MOTOR = 5; ////6;
	static final int POTENTIOMETER = 1;
	
	static final int[] ULTRASONIC = {7, 9};
	static final int BEAM_SENSOR = 9;

	static final int[] LEFT_ENCODER = {3, 4};
	static final int[] RIGHT_ENCODER = {5, 6};

	static final int WINCH_MOTOR = 7; ////10;
	static final int WINCH_SWITCH = 10; ////5; ////11;
	static final int WINCH_RELEASE_SOLENOID = 4;
	static final int WINCH_RETRACT_SOLENOID = 3;
	static final int WINCH_WAIT = 5000;

	//pid stuffz
	static final double[] PID = {0.15, 0.0, 1.0};
	static final double LEFT_DRIVE_DPP = (Math.PI * 4.0) / 360;
	static final double RIGHT_DRIVE_DPP = (Math.PI * 4.0) / 360;

	static final int SHOOT_POSITION = 207; ////90;
	static final int RETRIEVE_POSITION = 255; ////30;
	static final int HOME_POSITION = 168; //180;
	static final int MAX_POSITION = 255; ////178; ////190;
	static final int MIN_POSITION = 168; ////255; ////20;
	static final int BUFFER = 3;

	static final int SHOOT_POSITION_BUTTON = 7;
	static final int RETRIEVE_POSITION_BUTTON = 6;
	static final int HOME_POSITION_BUTTON = 3;
	static final int RETRIEVAL_MANUAL_BUTTON = 2;
}
