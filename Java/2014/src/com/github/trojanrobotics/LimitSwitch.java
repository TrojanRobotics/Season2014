/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author mattbettinson
 */
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
    
    public boolean setInverted(boolean value, boolean isInverted) {
        if (value) {
            if (isInverted) {
                return false;
            } else {
                return true;
            }
        } else {
            if (isInverted) {
                return true;
            } else {
                return false;
            }
        }
    }
}
