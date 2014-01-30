

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Ultrasonic;


public class Chasis 
{
        Bundle leftSide, rightSide;      
        Ultrasonic ultrasonic = new Ultrasonic(Config.PING_CHANNEL, Config.ECHO_CHANNEL);
        
        
        public Chasis(int leftAChannel, int leftBChannel, int rightAChannel, int rightBChannel)
        {
                this.leftSide = new Bundle(leftAChannel, leftBChannel);
                this.rightSide = new Bundle(rightAChannel, rightBChannel);
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