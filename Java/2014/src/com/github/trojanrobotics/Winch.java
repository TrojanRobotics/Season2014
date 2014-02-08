package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

public class Winch {
    Jaguar winchMotor;
    LimitSwitch limitSwitch;
    Solenoid engageSolenoid, releaseSolenoid;
    
    public Winch (int jagChannel, int limitChannel) {
        winchMotor = new Jaguar(jagChannel);
        limitSwitch = new LimitSwitch(limitChannel);
        engageSolenoid = new Solenoid(1);
        releaseSolenoid = new Solenoid(2);
        engageSolenoid.set(true);
        releaseSolenoid.set(false);
    }
    
    public void retract(double speed)
    {
        //set motor speed to pull down winch
        releaseSolenoid.set(false);
        engageSolenoid.set(true);
        winchMotor.set(speed);
    }
    
    public void stop()
    {
        //stop the motor
       winchMotor.set(0.0);
    }
    
    public void release()
    {
        //release the winch to fire the ball
        engageSolenoid.set(false);
        releaseSolenoid.set(true);
    }
}
