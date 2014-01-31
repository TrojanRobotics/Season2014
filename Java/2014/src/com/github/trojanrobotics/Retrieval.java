
package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

public class Retrieval {
    Jaguar retrievalMotor;
    Jaguar beltMotor;
    Encoder encoder1;

    
    public Retrieval (Jaguar rM, Jaguar bM, Encoder e1) {
        retrievalMotor = rM;
        beltMotor = bM;
        encoder1 = e1;
    }
    
    public void setRetrieval(double speed)
    {
        retrievalMotor.set(speed);
    }
    
}
