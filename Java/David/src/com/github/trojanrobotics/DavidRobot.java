package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

public class DavidRobot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	Joystick joystick;
    Jaguar   jaguar001, jaguar002, jaguar003, jaguar004;
	int xSpecialX = 1;
	int xSpecialY = 1;
	
	
	public void robotInit() {
		joystick = new Joystick(1);
		jaguar001   = new Jaguar(1);
		jaguar002   = new Jaguar(2);
		jaguar003   = new Jaguar(3);
		jaguar004   = new Jaguar(4);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
		
		
		if(joystick.getRawButton(1)){
			//System.out.println("x=="+joystick.getX());
			//System.out.println("y=="+joystick.getY());
			
			double xx = ((joystick.getX()*0.50) * xSpecialX);
			double yy = ((joystick.getY()*0.75) * xSpecialY);
			jaguar001.set(yy+xx);
			jaguar002.set(yy+xx);
			jaguar003.set(-yy+xx);
			jaguar004.set(-yy+xx);
			/*
			jaguar001.set(yy);
			jaguar002.set(yy);
			jaguar003.set(-yy);
			jaguar004.set(-yy);
			*/
		}else{

			jaguar001.set(0.0);
			jaguar002.set(0.0);
			jaguar003.set(0.0);
			jaguar004.set(0.0);
		}
      
		/*if(joystick.getRawButton(3)){
			jaguar001.set(0.0);
			jaguar002.set(0.0);
			jaguar003.set(0.0);
			jaguar004.set(0.0);
		}
		
		
		if(joystick.getRawButton(6)){
			jaguar001.set(0.25);
			jaguar002.set(0.25);
			jaguar003.set(-0.25);
			jaguar004.set(-0.25);
		}
		
		if(joystick.getRawButton(7)) {
			jaguar001.set(-0.25);
		    jaguar002.set(-0.25);
			jaguar003.set(0.25);
			jaguar004.set(0.25);
		
	    }    
	     
		if (joystick.getRawButton(11)) {
			jaguar001.set(0.25);
			jaguar002.set(0.25);
			jaguar003.set(-0.10);
			jaguar004.set(-0.10);
		}
		
		
		if (joystick.getRawButton(10))	{
			jaguar001.set(-0.25);
			jaguar002.set(-0.25);
			jaguar003.set(0.10);
			jaguar004.set(0.10);
			
        }
	}
        
		
    /**
     * This function is called periodically during test mode
     */
    
    
    }
    
}
