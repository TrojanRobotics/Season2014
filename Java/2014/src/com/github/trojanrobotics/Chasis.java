

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Ultrasonic;


public class Chasis 
{
        Bundle leftSide, rightSide;      
        Ultrasonic ultrasonic;
        
        
        public Chasis(int[] rightChannels, int[] leftChannels ,int[] ultraSonicPingAndEcho)
        {
                this.leftSide = new Bundle(rightChannels[0], rightChannels[1]);
                this.rightSide = new Bundle(leftChannels[0], leftChannels[1]);
                ultrasonic = new Ultrasonic(ultraSonicPingAndEcho[0], ultraSonicPingAndEcho[1]);
        }                                                                                                      
        
        public void set(double speed)
        {
                leftSide.set(speed);
                rightSide.set(-speed);
        }
        
        public void stop()
        {
                leftSide.stop();
                rightSide.stop();
        }
}