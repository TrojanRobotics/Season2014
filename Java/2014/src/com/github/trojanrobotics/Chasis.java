package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;

public class Chasis 
{
        Bundle leftSide, rightSide;      
        Ultrasonic ultrasonic;
        PIDController leftSidePID, rightSidePID;
        Encoder leftSideEncoder, rightSideEncoder;
        Compressor compressor;
		DriverStationLCD driverStationLCD;
		Retrieval retrieval;
        Solenoid gearShiftSolenoid;
		
		public static class Gear {
		public final int value;
		static final int gear1_val = 1;
		static final int gear2_val = 2;

        public static final Gear gearOne = new Gear(gear1_val);
        public static final Gear gearTwo = new Gear(gear2_val);

        private Gear(int value) {
            this.value = value;
        }
    }
	
        public Chasis(int[] rightChannels, int[] leftChannels ,int[] ultraSonicPingAndEcho, int[] leftEncoderChannels, int[] rightEncoderChannels)
        {
			int[] winchChannels = {Config.WINCH_MOTOR, Config.WINCH_SWITCH, Config.WINCH_RELEASE_SOLENOID, Config.WINCH_RETRACT_SOLENOID};
			retrieval = new Retrieval(Config.RETRIEVAL_ANGLE_MOTOR, Config.BELT_MOTOR, Config.POTENTIOMETER, winchChannels);
			this.leftSide = new Bundle(leftChannels[0], leftChannels[1]);
			this.rightSide = new Bundle(rightChannels[0], rightChannels[1]);   
			leftSideEncoder = new Encoder(leftEncoderChannels[0], leftEncoderChannels[1]);
			rightSideEncoder = new Encoder(rightEncoderChannels[0], rightEncoderChannels[1]);
			leftSideEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
			rightSideEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
			leftSideEncoder.setDistancePerPulse(Config.LEFT_DRIVE_DPP);
			rightSideEncoder.setDistancePerPulse(Config.RIGHT_DRIVE_DPP); 
			gearShiftSolenoid = new Solenoid(Config.GEAR_SHIFT_SOLENOID);
			leftSideEncoder.setReverseDirection(true);
			rightSideEncoder.setReverseDirection(true);
			leftSideEncoder.start();
			rightSideEncoder.start();
			ultrasonic = new Ultrasonic(ultraSonicPingAndEcho[0], ultraSonicPingAndEcho[1]);
			rightSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], rightSideEncoder, rightSide);
			leftSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], leftSideEncoder, leftSide);
			compressor = new Compressor(Config.COMPRESSOR[0], Config.COMPRESSOR[1]);
			compressor.start();
			driverStationLCD = DriverStationLCD.getInstance();       
        }                                                                                                      
 
		public void gearShift(Gear gear){
			if (gear == Gear.gearOne){
				this.gearShiftSolenoid.set(false);
			} else if (gear == Gear.gearTwo){
				this.gearShiftSolenoid.set(true);
			}
		} 
		
        public void set(double speed)
        {
            leftSide.set(speed);
            rightSide.set(-speed);
        }
        
        public double getPID()
        {
            return leftSidePID.get();
        }
        public void reset()
        {
            leftSideEncoder.reset();
            rightSideEncoder.reset();
        }
        
        public void setSetpoint(double setPoint)
        {
			if (!leftSidePID.isEnable()) leftSidePID.enable();
            if (!rightSidePID.isEnable()) rightSidePID.enable();
            leftSidePID.setSetpoint(setPoint);
            rightSidePID.setSetpoint(-setPoint);
        }
               
        public void stop()
        {
            leftSide.stop();
            rightSide.stop();
            leftSidePID.disable();
			rightSidePID.disable();
        }
}