/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.trojanrobotics;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author mattbettinson
 */
public class Winch {
    Jaguar winchMotor = new Jaguar(Config.WINCH_MOTOR);
    LimitSwitch ls = new LimitSwitch(Config.DIGITAL_INPUT);
}
