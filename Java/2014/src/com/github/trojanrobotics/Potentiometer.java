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
		double voltRound = (double) voltDegreeRound/1000;
		double a = (double) ((360*voltRound)/5);
		double angle = (double) a;
		return angle;
	}
	
	public double getVoltage(double volts){
		volts = channel.getVoltage();
		return volts;
	}
}