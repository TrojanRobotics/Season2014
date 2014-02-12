package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.DigitalInput;

public class BeamSensor {
	
	DigitalInput digitalInput;
	
	public BeamSensor(int beamChannel){
		digitalInput = new DigitalInput(beamChannel);
	}
	
	public boolean get(){
		return digitalInput.get();
	}
}
