

package com.github.trojanrobotics;

import com.sun.squawk.util.MathUtils;

public class Lib
{
        public static double round(double num, int decimalPlaces)
        {
                double places = MathUtils.pow(10, decimalPlaces);
                double newDouble = num * places;
                int newInt = (int)newDouble;
                return newInt/places;
        }
        
        public static double limitOutput(double value)
        {
                
                if (value > 1.0)
                {
                        return 1.0;
                }
                else if (value < -1.0) 
                {
                        return -1.0;
                } 
                else 
                {
                        return round(value,1);
                }
        }

        public static double signSquare(double value)
        {
                if (value < 0.0) {
                        return round(-1.0 * value * value, 2);
                }
                else 
                {
                        return round(value * value, 2);
                }
        }
}