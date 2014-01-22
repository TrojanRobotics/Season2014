package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class Bundle implements PIDOutput, SpeedController
{
        protected Jaguar jagOne, jagTwo;
        
        public Bundle(int channelOne, int channelTwo)
        {
                jagOne = new Jaguar(channelOne);
                jagTwo = new Jaguar(channelTwo);
        }
        
        public double get()
        {
                return jagOne.getSpeed();
        }
        
        public void set(double speed)
        {
                jagOne.set(speed);
                jagTwo.set(speed);
        }
        
        public void set(double speed, byte syncGroup)
        {
                jagOne.set(speed);
                jagTwo.set(speed);
        }
        
        public void stop()
        {
                jagOne.set(0.0);
                jagTwo.set(0.0);
        }
        
        public void pidWrite(double output)
        {
                jagOne.pidWrite(output);
                jagTwo.pidWrite(output);
        }
        
        public void disable()
        {
                jagOne.disable();
                jagTwo.disable();
        }
}
