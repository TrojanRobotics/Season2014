package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import java.util.TimerTask;

public class Retrieval {
	Talon beltMotor, retrievalAngleMotor;
	Potentiometer potentiometer;
	Winch winch;
	double targetAngle;
	boolean movementEnabled;
	double retrievalAngle;

	Solenoid upSolenoid, downPiston;
	java.util.Timer timer, retrievalTimer;

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
    
	private class RetrievalTask extends TimerTask{
		private Retrieval retrieval;
		
		public RetrievalTask(Retrieval r){
			if (r == null){
				throw new NullPointerException("requires retrieval for the task to run");
			}
			retrieval = r;
		}
		public void run(){
			retrieval.calculate();
		}
	}
	
	public Retrieval (int retrievalAngleChannel, int beltChannel, int potChannel, int[] winchChannels) {
        beltMotor = new Talon(beltChannel);
		retrievalAngleMotor = new Talon(retrievalAngleChannel);
		potentiometer = new Potentiometer(potChannel);
		winch = new Winch(winchChannels[0], winchChannels[1], winchChannels[2], winchChannels[3]);
		movementEnabled = false;
		targetAngle = Config.HOME_POSITION;
		retrievalTimer = new java.util.Timer();
		retrievalTimer.schedule(new RetrievalTask(this), 0, (long)(0.05 * 1000));
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
	
	public boolean canFire(){
		return this.timer == null;
	}
	public void setRetrieval(double speed) {
		double potAngle = potentiometer.getAngle();
		if (potAngle >= Config.MIN_POSITION + Config.BUFFER || potAngle <= Config.MAX_POSITION - Config.BUFFER){
			retrievalAngleMotor.set(speed);
		} else {
			retrievalAngleMotor.disable();
		}
    }
  
    public void setAngleRetrieval(double angle){
		targetAngle = angle; 
	}
    private void calculate() {
		if (movementEnabled) {
			double potAngle = potentiometer.getAngle();
			double distanceRequired = (Math.abs(targetAngle - potAngle) / targetAngle);
			double retrievalSpeed = 0.5 * distanceRequired;
			
				if (potAngle < targetAngle) {
					setRetrieval(retrievalSpeed);
					System.out.println("go forwards" + potAngle);
				} else if (potAngle > targetAngle) {
					setRetrieval(-retrievalSpeed);
					System.out.println("go backwards" + potAngle);
				} else {
					setRetrieval(0.0);
					System.out.println("perfect" + potAngle);//stop motor
				}
			}
		}
	
	public void setEnabled(boolean isEnabled){
		movementEnabled = isEnabled;
	}
	
	
	public void free(){
		timer.cancel();
		timer = null;
		retrievalTimer.cancel();
		retrievalTimer = null;
	}
}
