/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author mattbettinson
 */
public class Retrieval {
    Jaguar retrievalMotor = new Jaguar(Config.RETRIEVAL_MOTOR);
    Jaguar beltMotor = new Jaguar(Config.BELT_MOTOR);
    Encoder encoder1 = new Encoder(Config.ENCODER1_CHANNEL, Config.ENCODER2_CHANNEL);

    
    public Retrieval () {
        
    }
    
    public void setRetrieval(double speed)
    {
        retrievalMotor.set(speed);
    }
    
}
