package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.DigitalInput;


public class LimitSwitch {
    DigitalInput digital_Input;
    boolean isInverted;
    
    public LimitSwitch(int port) {
        digital_Input = new DigitalInput(port);
        isInverted = false;
    }
    public boolean get() {
        return digital_Input.get();
    }
    
    public void setInverted(boolean value, boolean invert) {
        if (value) {
            if (invert) {
                isInverted = false;
            } else {
                isInverted = true;
            }
        } else {
            if (invert) {
                isInverted = true;
            } else {
                isInverted = false;
            }
        }
    }
}
