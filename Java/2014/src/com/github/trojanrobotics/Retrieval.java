package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import java.util.TimerTask;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Retrieval {
	Talon beltMotor, retrievalAngleMotor;
	Potentiometer potentiometer;
	Winch winch;
	double targetAngle;
	boolean movementEnabled;
	double retrievalAngle;
	BeamSensor beamSensor;

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
		beamSensor = new BeamSensor(Config.BEAM_SENSOR);
		
		//TODO: Constant name
		upSolenoid = new Solenoid(2);
		downPiston = new Solenoid(5);
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
		
		SmartDashboard.putNumber("kRetrievalSpeed", speed);
		double potAngle = potentiometer.getAngle();
		
		if (potAngle >= Config.MIN_POSITION + Config.BUFFER || potAngle <= Config.MAX_POSITION - Config.BUFFER){
			System.out.println("LLLLLLLLLLLLLLLLLLL " + potAngle );
			retrievalAngleMotor.set(speed);
		} else {
			System.out.println("AAAAAAAAAAAAAAAAAA " + potAngle );
			retrievalAngleMotor.disable();
		}
    }
  
    public void setAngleRetrieval(double angle){
		targetAngle = angle; 
	}
	
    private void calculate() {
		SmartDashboard.putNumber("kTargetAngle", targetAngle);
		
		if (movementEnabled) {
			SmartDashboard.putBoolean("kMovementEnabled", movementEnabled);
			
			double potAngle = potentiometer.getAngle();
			double distanceRequired = (Math.abs(targetAngle - potAngle) / targetAngle);
			double retrievalSpeed;
			int someName = 0;
			
			if(distanceRequired >= 0.75){
				retrievalSpeed = 0.65;
				someName = 1;
			} else if (distanceRequired <= 0.10) {
				retrievalSpeed = 0.35;
				someName = 2;
			} else {
				someName = 3;
				retrievalSpeed = 0.55;
			}
			
			SmartDashboard.putBoolean("kOpenOrClosed", upSolenoid.get());
			SmartDashboard.putNumber("kSomeNumber", someName);
			SmartDashboard.putNumber("kPotAngle", potAngle);
			SmartDashboard.putNumber("kDistanceRequired", distanceRequired);
			SmartDashboard.putNumber("kRetrievalSpeed", retrievalSpeed);
			
			//tolerance
				if (potAngle < targetAngle - Config.BUFFER) {
					setRetrieval(-retrievalSpeed);
					//System.out.println("go forwards" + potAngle);
				} else if (potAngle > targetAngle + Config.BUFFER) {
					setRetrieval(retrievalSpeed);
					//System.out.println("go backwards" + potAngle);
				} else {
					if((potAngle < 200 && potAngle > 160)){
						setRetrieval(0.0);
					} else {
						setRetrieval(0.2);
					}
					//System.out.println("perfect" + potAngle);//stop motor
				}
			}////else{
			 ////System.out.println("Movement NOT Enabled...");
			////}
		
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
