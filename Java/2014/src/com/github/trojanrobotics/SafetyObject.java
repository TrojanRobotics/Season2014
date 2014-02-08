/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author mattbettinson
 */
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
