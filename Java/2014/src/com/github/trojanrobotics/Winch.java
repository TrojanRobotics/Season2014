package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;

public class Winch {
    Talon winchMotor;
    LimitSwitch limitSwitch;
    Solenoid releaseSolenoid, retractSolenoid;
    
    public Winch (int winchChannel, int limitChannel, int releaseChannel, int retractChannel) {
        winchMotor = new Talon(winchChannel);
        limitSwitch = new LimitSwitch(limitChannel, winchMotor);
        releaseSolenoid = new Solenoid(releaseChannel);
        retractSolenoid = new Solenoid(retractChannel);
		
        retractSolenoid.set(true);
        releaseSolenoid.set(false);
    }
    
    public void release() {
        winchMotor.stopMotor();
        retractSolenoid.set(false);
        releaseSolenoid.set(true);
    }
    public void retract() {
        releaseSolenoid.set(false);
        retractSolenoid.set(true);
        winchMotor.set(0.5);
    }
    
    public void stop() {
        winchMotor.stopMotor();
    }
}
