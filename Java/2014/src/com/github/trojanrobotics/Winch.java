package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

public class Winch {
    Jaguar winchMotor;
    LimitSwitch limitSwitch;
    Solenoid releaseSolenoid, retractSolenoid;
    
    public Winch (int winchChannel, int limitChannel, int releaseChannel, int retractChannel) {
        winchMotor = new Jaguar(winchChannel);
        limitSwitch = new LimitSwitch(limitChannel, winchMotor);
        releaseSolenoid = new Solenoid(releaseChannel);
        retractSolenoid = new Solenoid(retractChannel);
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
