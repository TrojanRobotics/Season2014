package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class Bundle implements PIDOutput, SpeedController
{
        protected Talon talOne, talTwo;
        
        public Bundle(int channelOne, int channelTwo)
        {
                talOne = new Talon(channelOne);
                talTwo = new Talon(channelTwo);
        }
        
        public double get()
        {
                return talOne.getSpeed();
        }
        
        public void set(double speed)
        {
                talOne.set(speed);
                talTwo.set(speed);
        }
        
        public void set(double speed, byte syncGroup)
        {
                talOne.set(speed);
                talTwo.set(speed);
        }
        
        public void stop()
        {
                talOne.set(0.0);
                talTwo.set(0.0);
        }
        
        public void pidWrite(double output)
        {
                talOne.pidWrite(output);
                talTwo.pidWrite(output);
        }
        
        public void disable()
        {
                talOne.disable();
                talTwo.disable();
        }
}
