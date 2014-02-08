package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.SpeedController;

public abstract class SafetyObject {
    SpeedController controller;
   
    public abstract void stop();
    
    public abstract String getDescription();
}
