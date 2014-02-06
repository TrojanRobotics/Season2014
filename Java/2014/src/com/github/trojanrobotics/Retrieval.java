
package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Retrieval {
    Talon retrievalMotor;
    Talon beltMotor;
    Encoder encoder1;

    
    public Retrieval (Talon rM, Talon bM, Encoder e1) {
        retrievalMotor = rM;
        beltMotor = bM;
        encoder1 = e1;
    }
    
    public void setRetrieval(double speed)
    {
        retrievalMotor.set(speed);
    }
    
}
