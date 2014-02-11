package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.PIDSource;

public class Chasis 
{
        Bundle leftSide, rightSide;      
        Ultrasonic ultrasonic;
        PIDController leftSidePID, rightSidePID;
        Encoder leftSideEncoder, rightSideEncoder;
        Compressor compressor;
        Winch winch;
        DriverStationLCD driverStationLCD;
        
        public Chasis(int[] rightChannels, int[] leftChannels ,int[] ultraSonicPingAndEcho, int[] leftEncoderChannels, int[] rightEncoderChannels)
        {
			this.leftSide = new Bundle(leftChannels[0], leftChannels[1]);
			this.rightSide = new Bundle(rightChannels[0], rightChannels[1]);   
			leftSideEncoder = new Encoder(leftEncoderChannels[0], leftEncoderChannels[1]);
			rightSideEncoder = new Encoder(rightEncoderChannels[0], rightEncoderChannels[1]);
			leftSideEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
			rightSideEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
			leftSideEncoder.setDistancePerPulse(Config.LEFT_DRIVE_DPP);
			rightSideEncoder.setDistancePerPulse(Config.RIGHT_DRIVE_DPP); 
			leftSideEncoder.setReverseDirection(true);
			rightSideEncoder.setReverseDirection(true);
			leftSideEncoder.start();
			rightSideEncoder.start();
			ultrasonic = new Ultrasonic(ultraSonicPingAndEcho[0], ultraSonicPingAndEcho[1]);
			rightSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], rightSideEncoder, rightSide);
			leftSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], leftSideEncoder, leftSide);
			compressor = new Compressor(Config.COMPRESSOR[0], Config.COMPRESSOR[1]);
//			winch = new Winch(Config.WINCH_MOTOR, Config.WINCH_SWITCH);
			compressor.start();
			driverStationLCD = DriverStationLCD.getInstance();       
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

    class retrieval {

        public retrieval() {
        }
    }
}