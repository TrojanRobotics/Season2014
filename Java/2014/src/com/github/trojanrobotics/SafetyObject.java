package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.SpeedController;

public class SafetyObject {
    SpeedController controller;
   
    public SafetyObject (SpeedController c)
    {
        controller = c;
    }
    
    public void stop()
    {
        
    }
    
    public String getDescription()
    {
        return "This is safety object";
    }
}
