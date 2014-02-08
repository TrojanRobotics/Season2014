package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import java.util.TimerTask;

public class Retrieval {
	Talon beltMotor, retrievalAngleMotor;
	Potentiometer potentiometer;
	Winch winch;
	double potAngle;
	
    Solenoid upSolenoid, downPiston;
    java.util.Timer timer;

    protected class WinchTask extends TimerTask
    {
        Retrieval retrieval;
        public WinchTask (Retrieval r){
            retrieval = r;
        }
        
        public void run() {
            retrieval.winch.retract();
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
	
	public Retrieval (int retrievalAngleChannel, int beltChannel, int potChannel, int[] winchChannels) {
        beltMotor = new Talon(beltChannel);
		retrievalAngleMotor = new Talon(retrievalAngleChannel);
		potentiometer = new Potentiometer(potChannel);
		winch = new Winch(winchChannels[0], winchChannels[1], winchChannels[2], winchChannels[3]);
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
	
	public void setRetrieval(double speed) {
        retrievalAngleMotor.set(speed);
    }
    
    public void setAngleRetrieval(double angle){
		potAngle = potentiometer.voltToAngle();
			while (potAngle != angle) 
			{
				if (potAngle < angle)
				{
					
					System.out.println("go forwards" + potAngle);
				}
				else if (potAngle > angle)
				{
					System.out.println("go backwards" + potAngle);
				}
				else
				{
					System.out.println("perfect" + potAngle);//stop motor
				}
			potAngle = potentiometer.voltToAngle();
			} 
	}
    
}
