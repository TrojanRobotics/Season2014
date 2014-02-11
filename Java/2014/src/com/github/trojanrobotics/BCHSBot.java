package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.camera.*;

public class BCHSBot extends IterativeRobot {

    Joystick mainJoystick, secondaryJoystick;
    double servoAngleY;
    double servoAngleX;
    double x, y;
    Chasis chasis;
    Winch winch;
    boolean autoRunOnce;
    Retrieval retrieval;
    ParticleAnalysisReport[] particles;
    Camera camera;
    int[] RGBThreshold = {202, 255, 86, 207, 0, 255};

    public void robotInit() {
        camera = new Camera();
        mainJoystick = new Joystick(Config.MAIN_JOYSTICK);
        secondaryJoystick = new Joystick(Config.SECONDARY_JOYSTICK);
        winch = new Winch(Config.WINCH_MOTOR, Config.WINCH_LIMITSWITCH, Config.RELEASE_SOLENOID, Config.RETRACT_SOLENOID);
        retrieval = new Retrieval(Config.RETRIEVAL_MOTOR, Config.BELT_MOTOR, Config.RETRIEVAL_ENCODER, winch);
        if (Config.TEST_BOT) {
            chasis = new Chasis(Config.TEST_LDRIVE, Config.TEST_RDRIVE, Config.ULTRASONIC, Config.LEFT_ENCODER, Config.RIGHT_ENCODER);
        } else {
            chasis = new Chasis(Config.LDRIVE, Config.RDRIVE, Config.ULTRASONIC, Config.LEFT_ENCODER, Config.RIGHT_ENCODER);
        }
        LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talOne);
        LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talTwo);
        LiveWindow.addActuator("Right Side", "Value", chasis.leftSide.talOne);
        LiveWindow.addActuator("Left Side", "Value", chasis.leftSide.talTwo);
        LiveWindow.addActuator("PID", "leftPID", chasis.leftSidePID);
        LiveWindow.addActuator("PID", "rightPID", chasis.rightSidePID);
    }

    public void disabledPeriodic() {
        chasis.stop();
        chasis.reset();
        autoRunOnce = true;
    }

    public void autonomousPeriodic() {
        System.out.println("Autonomous Periodic");
        if (autoRunOnce == true) {
            System.out.println("Autonomous");
            //chasis.set(0.5);
            autoRunOnce = false;
        }
        String clear = "                         ";
        chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, clear);
        chasis.driverStationLCD.updateLCD();

        String s = Double.toString(chasis.rightSideEncoder.getDistance());
        chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, s);

        String s1 = Double.toString(chasis.leftSideEncoder.getDistance());
        chasis.driverStationLCD.println(DriverStationLCD.Line.kUser2, 1, s1);
        chasis.driverStationLCD.updateLCD();

                //once moved to firing position, release winch to fire the ball
        //bonus: fire when hot goal
        if (chasis.getPID() == 15) {
            chasis.winch.release();
        }
    }

    public void teleopPeriodic() {
        x = mainJoystick.getX();
        y = mainJoystick.getY();

        x = Lib.signSquare(x);
        y = Lib.signSquare(y);

        //System.out.println(y);
        //System.out.println(x);
        chasis.rightSide.set(Lib.limitOutput(y + x));
        chasis.leftSide.set(-Lib.limitOutput(y - x));

//		String clear = "                         ";
//		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, clear);
//		chasis.driverStationLCD.updateLCD();
//		
//		String s = Double.toString(chasis.rightSideEncoder.getDistance());
//		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser1, 1, s);
//		
//		String s1 = Double.toString(chasis.leftSideEncoder.getDistance());
//		chasis.driverStationLCD.println(DriverStationLCD.Line.kUser2, 1, s1);
//		chasis.driverStationLCD.updateLCD();
//		
//		System.out.println("Distance and rate" + chasis.rightSideEncoder.getDistance() + "    " + chasis.rightSideEncoder.getRate());
        if (mainJoystick.getRawButton(2)) {
            
            particles = camera.getLargestParticle(RGBThreshold);
            double area = particles[0].particleArea;
            System.out.println(area);
        }
        
        if (mainJoystick.getRawButton(1)) //Shoot
        {
            retrieval.setArmPosition(Retrieval.Direction.up);
            retrieval.winchMotor.release();
            retrieval.startTimer();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }
}
