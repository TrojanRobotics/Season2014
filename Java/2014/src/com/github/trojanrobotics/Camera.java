/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author mattbettinson
 */
public class Camera {
     public static class Direction
        {
        
                public final int value;
                static final int left_val = 0;
                static final int right_val = 1;
                static final int center_val = 2;
                static final int up_val = 3;
                static final int down_val = 4;
                static final int error_val = -1;

                public static final Direction left = new Direction(left_val);
                public static final Direction right = new Direction(right_val);
                public static final Direction center = new Direction(center_val);
                public static final Direction error = new Direction(error_val);
                public static final Direction up = new Direction(up_val);
                public static final Direction down = new Direction(down_val);


                private Direction (int value)
                {
                        this.value = value;
                }
        }
     
     
}
