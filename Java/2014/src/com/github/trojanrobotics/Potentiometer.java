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
	
	public double voltToAngle()
	{
		int voltDegreeRound = (int) MathUtils.round(channel.getVoltage()*1000);
		double voltRound = (double) voltDegreeRound/1000;
		double a = (double) ((360*voltRound)/5);
		int angle = (int) a;
		return angle;
	}
	
	public double getVoltage(double volts){
		volts = channel.getVoltage();
		return volts;
	}
}