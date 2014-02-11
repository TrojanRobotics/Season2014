package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import java.util.TimerTask;

public class Retrieval {

    Jaguar retrievalMotor;
    Jaguar beltMotor;
    Winch winchMotor;
    Encoder encoder1;
    Solenoid upSolenoid, downPiston;
    LimitSwitch limitSwitch;
    java.util.Timer timer;

    protected class WinchTask extends TimerTask
    {
        Retrieval retrieval;
        public WinchTask (Retrieval r){
            retrieval = r;
        }
        
        public void run() {
            retrieval.winchMotor.retract();
            retrieval.timer.cancel();
            retrieval.timer = null;
        }
    }
    
    public static class Direction {

        public final int value;
        static final int up_val = 3;
        static final int down_val = 4;
        static final int error_val = -1;

        public static final Direction error = new Direction(error_val);
        public static final Direction up = new Direction(up_val);
        public static final Direction down = new Direction(down_val);

        private Direction(int value) {
            this.value = value;
        }
    }

    public Retrieval(int rM, int bM, int[] e1, Winch wM) {
        retrievalMotor = new Jaguar(rM);
        beltMotor = new Jaguar(bM);
        encoder1 = new Encoder(e1[0], e1[1]);
        winchMotor = wM;
        limitSwitch = new LimitSwitch(Config.WINCH_LIMITSWITCH, wM.winchMotor);
        timer = new java.util.Timer();
    }

    public void setRetrieval(double speed) {
        retrievalMotor.set(speed);
    }

    public void setArmPosition(Direction direction) {
        if (direction == Direction.up) {
            upSolenoid.set(true);
            downPiston.set(false);
        } else if (direction == Direction.down) {
            upSolenoid.set(false);
            downPiston.set(true);
        }
    }
    
    public void startTimer()
    {
        if (this.timer == null) {
            timer = new java.util.Timer();
            timer.schedule(new WinchTask(this), Config.WINCH_WAIT, 0);
        }
    }
}
