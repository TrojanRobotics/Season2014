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
		double a = (double) ((330*voltRound)/5);
		int angle = (int) a;
		return angle;
	}
}
/*
		if (startButton == true) //sets retrieval to start position
		{
			while (angle != 115) 
			{
				if (angle < 115)
				{
					System.out.println("go forwards" + angle);//motor needs to reverse until 115
				}
				else if (angle > 115)
				{
					System.out.println("go backwards" + angle);//motor needs to go forward until 115
				}
				else
				{
					System.out.println("perfect" + angle);//stop motor
				}
			angle = roundVolts(pot.getVoltage());
			}
			
		}
		if (shootButton == true) //sets retrieval to start position
		{
			while (angle != 57) 
			{
				if (angle < 57)
				{
					System.out.println("go forwards" + angle);//motor needs to reverse until 115
				}
				else if (angle > 57)
				{
					System.out.println("go backwards" + angle);//motor needs to go forward until 115
				}
				else
				{
					System.out.println("perfect" + angle);//stop motor
				}
			angle = roundVolts(pot.getVoltage());
			}
			
		}
		if (retrieveButton == true) //sets retrieval to start position
		{
			while (angle != 0) 
			{
				if (angle < 0)
				{
					System.out.println("go forwards" + angle);//motor needs to reverse until 115
				}
				else if (angle > 0)
				{
					System.out.println("go backwards" + angle);//motor needs to go forward until 115
				}
				else
				{
					System.out.println("perfect" + angle);//stop motor
				}
			angle = roundVolts(pot.getVoltage());
			}
			
		}
		*/