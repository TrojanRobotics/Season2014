package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.TimerTask;


public class LimitSwitch {
    DigitalInput digital_Input;
    boolean isInverted;
    Thread limitSwitchThread;
    SpeedController motor;
    java.util.Timer controlLoop;
    long period = 1;
    
    private class LimitTask extends TimerTask {
        
        private LimitSwitch l_switch;
        
        public LimitTask(LimitSwitch lS) {
            if (lS == null) {
                throw new NullPointerException("Given Limit Switch  was null");
            }
            l_switch = lS;
        }
        
        public void run() {
            if (l_switch.get())
            {
                motor.disable();
            }
        }
        
    }
    
    public LimitSwitch(int port, SpeedController m) {
        digital_Input = new DigitalInput(port);
        isInverted = false;
        limitSwitchThread = new Thread();
        motor = m;
        
        controlLoop.schedule(new LimitTask(this), 0L, (long) (period *1000));
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
        
    public String getDescription() {
       return "Matt is dope";
    }
}
