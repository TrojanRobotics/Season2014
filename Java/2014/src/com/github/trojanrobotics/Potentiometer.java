package com.github.trojanrobotics;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.AnalogChannel;

public class Potentiometer 
{
	AnalogChannel channel;
	
	
	public Potentiometer(int aChannel)
	{
		channel = new AnalogChannel(aChannel);
	}
	
	public double getAngle()
	{
		double voltDegreeRound = (double) MathUtils.round(channel.getVoltage()*1000);
		double voltRound = voltDegreeRound/1000.0;
		double angle = ((360.0*voltRound)/5.0);
		return angle;
	}
	
	public double getVoltage(double volts){
		volts = channel.getVoltage();
		return volts;
	}
}