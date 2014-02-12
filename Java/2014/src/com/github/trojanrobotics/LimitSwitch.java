package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.TimerTask;


public class LimitSwitch extends SafetyObject {
    DigitalInput digital_Input;
    boolean isInverted;
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
    
    public LimitSwitch(int channel, SpeedController m) {
        digital_Input = new DigitalInput(channel);
        isInverted = false;
        motor = m;
        
		controlLoop = new java.util.Timer();
        controlLoop.schedule(new LimitTask(this), 0L, (long) (period *1000));
    }
    
    public boolean get() {
        if (this.isInverted) {
            return !digital_Input.get();
        } else {
            return digital_Input.get();
        }
    }
    
    public void setInverted(boolean inverted) {
        this.isInverted = inverted;
    }
    
    public void stop() {
        motor.disable();
    }
        
    public String getDescription() {
       return "LimitSwitch";
    }
}
