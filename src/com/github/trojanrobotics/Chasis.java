

package com.github.trojanrobotics;


public class Chasis 
{
        Bundle leftSide, rightSide;      

                                                                                             
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