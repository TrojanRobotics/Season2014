package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;

public class Chasis 
{
        Bundle leftSide, rightSide;      
        Ultrasonic ultrasonic;
        PIDController leftSidePID, rightSidePID, leftSide2PID, rightSide2PID;
        Encoder leftSideEncoder, rightSideEncoder;
        Compressor compressor;
        Winch winch;
		DriverStationLCD driverStationLCD;
		Talon talonOne, talonTwo, talonThree, talonFour;
        
        public Chasis(int[] rightChannels, int[] leftChannels ,int[] ultraSonicPingAndEcho, int[] leftEncoderChannels, int[] rightEncoderChannels)
        {
				talonOne = new Talon(1);
				talonTwo = new Talon(2);
				talonThree = new Talon(3);
				talonFour = new Talon(4);
                //this.leftSide = new Bundle(rightChannels[0], rightChannels[1]);
                //this.rightSide = new Bundle(leftChannels[0], leftChannels[1]);
                leftSideEncoder = new Encoder(leftEncoderChannels[0], leftEncoderChannels[1]);
                rightSideEncoder = new Encoder(rightEncoderChannels[0], rightEncoderChannels[1]);
				leftSideEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
				rightSideEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
                //ultrasonic = new Ultrasonic(ultraSonicPingAndEcho[0], ultraSonicPingAndEcho[1]);
                //leftSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], leftSideEncoder, talonThree);
                //rightSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], rightSideEncoder, talonFour);
				//leftSide2PID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], leftSideEncoder, talonOne);
                //rightSide2PID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], rightSideEncoder, talonTwo);
				rightSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], rightSideEncoder, talonFour);
				rightSide2PID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], rightSideEncoder, talonTwo);
				leftSidePID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], leftSideEncoder, talonThree);
				leftSide2PID = new PIDController(Config.PID[0], Config.PID[1], Config.PID[2], leftSideEncoder, talonOne);

				//compressor = new Compressor(Config.COMPRESSOR[0], Config.COMPRESSOR[1]);
                //winch = new Winch(Config.WINCH_MOTOR, Config.WINCH_SWITCH);
                leftSideEncoder.setDistancePerPulse(Config.LEFT_DRIVE_DPP);
                rightSideEncoder.setDistancePerPulse(Config.RIGHT_DRIVE_DPP); 
				leftSideEncoder.setReverseDirection(true);
				rightSideEncoder.setReverseDirection(true);
                //compressor.start();
                leftSideEncoder.start();
                rightSideEncoder.start();
				driverStationLCD = DriverStationLCD.getInstance();
              
        }                                                                                                      
        
        public void set(double speed)
        {
                //leftSide.set(speed);
                //rightSide.set(-speed);
			talonFour.set(speed);
			talonTwo.set(speed);
			talonThree.set(-speed);
			talonOne.set(-speed);
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
			if (!leftSide2PID.isEnable()) leftSide2PID.enable();
            if (!rightSidePID.isEnable()) rightSidePID.enable();
			if (!rightSide2PID.isEnable()) rightSide2PID.enable();
			
            leftSidePID.setSetpoint(setPoint);
			leftSide2PID.setSetpoint(setPoint);
            rightSidePID.setSetpoint(-setPoint);
			rightSide2PID.setSetpoint(-setPoint);
        }
                
        public void stop()
        {
            //leftSide.stop();
            //rightSide.stop();
			talonOne.disable();
			talonTwo.disable();
			talonThree.disable();
			talonFour.disable();
			
            leftSidePID.disable();
			rightSidePID.disable();
			leftSide2PID.disable();
			rightSide2PID.disable();
        }
}