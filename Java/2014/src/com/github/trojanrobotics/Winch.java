package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Jaguar;

public class Winch {
    Jaguar winchMotor;
    LimitSwitch limitSwitch;
    
    public Winch (Jaguar wM, LimitSwitch ls) {
        winchMotor = wM;
        limitSwitch = ls;
    }
}
