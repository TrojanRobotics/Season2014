package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;

public class Ultrasonic {
	AnalogChannel analogUltraSonic;
	
	public Ultrasonic (int channel) {
		analogUltraSonic = new AnalogChannel(channel);
	}
	
	public double getRange() {
		double voltage = analogUltraSonic.getVoltage();
		double range = ((voltage / 5.0) * 254.0);
		return range;
	}
}
