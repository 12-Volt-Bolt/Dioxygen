package frc.robot.statics_and_classes;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Equations {

    /**
     * If the input is within a given amount of 0, the output is set to 0.
     * 
     * @param number
     * @param deadzone If the input is within this amount of 0 the output will be 0.
     * 
     * @return The input with a deadzone applied.
     */
    public static double deadzone(double number, double deadzone) {
        if (Math.abs(number) > Math.abs(deadzone)) {
            return number;
        } else {
            return 0;
        }
    }


    /**
     * If the input is within 0.1 of 0, the output is set to 0.
     * 
     * @param number
     * 
     * @return The input with a 0.1 deadzone applied.
     */
    public static double deadzone(double number) {
        return deadzone(number, 0.1);
    }

    /**
     * If the double input is outside the given range then it is reduced/increased to the max or min,
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return The clamped input.
     */
    public static double clamp(double input, double min, double max) {
        double output = 0;
        if (input > max) {
            output = max;
        } else if (input < min) {
            output = min;
        } else {
            output = input;
        }
        return output;
    }

    /**
     * If the integer input is outside the given range then it is reduced/increased to the max or min,
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return The clamped input.
     */
    public static int clamp(int input, int min, int max) {
        int output = 0;
        if (input > max) {
            output = max;
        } else if (input < min) {
            output = min;
        } else {
            output = input;
        }
        return output;
    }

    /**
     * Checks to see if the input is within the min/max range.
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return Whether the input is within the given range. (True/False)
     */
    public static boolean insideRange(int input, int min, int max)
    {
        boolean output = false;
        if (input <= max && input >= min)
        {
            output = true;
        }
        return output;
    }

    /**
     * If the input is outside the given range it is "wrapped" around by adding/subtracting the max value until it is within the given range.
     * 
     * @param input
     * @param min
     * @param max
     * 
     * @return The input wrapped to the given range.
     */
    public static int wrap(int input, int min, int max)
    {
        min--;
        int output = input;
        int range = max - min;
        while (insideRange(output, min, max) == false)
        {
            if (input >= max){
                output -= range;
            } else {
                output += range;
            }
        }
        return output;
    }

    /**
     * Normalize all values if the magnitude of any value is greater than 1.0.
     */
    public static void normalize(double[] wheelSpeeds) {
      double maxMagnitude = Math.abs(wheelSpeeds[0]);
      for (int i = 1; i < wheelSpeeds.length; i++) {
        double temp = Math.abs(wheelSpeeds[i]);
        if (maxMagnitude < temp) {
          maxMagnitude = temp;
        }
      }
      if (maxMagnitude > 1.0) {
        for (int i = 0; i < wheelSpeeds.length; i++) {
          wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
        }
      }
    }

    /**
     * Always otputs a positive number, even if the given power is an odd number.
     * 
     * @param input
     * @param power The exponential applied to input.
     * 
     * @return The absolute value of input^power.
     */
    public static double exponetialAbs(double input, double power) {
        return Math.abs(Math.pow(input, power));
    }

    /**
     * If input is less than 0 the output will be negative, even if the given power is an even number.
     * 
     * @param input
     * @param power The exponential applied to input.
     * 
     * @return input^power or -(input^power) if input < 0.
     */
    public static double exponentialNegativeBelowZero(double input, double power)
    {
        double output = Math.pow(input, power);
        if (input < 0)
        {
            output = -output;
        }
        return output;
    }

    /**
     * Returns a -1 to 1 range based of the position of the left and right triggers. 
     * 
     * @param controller
     * 
     * @return If the left trigger is down: left trigger value, if the right trigger is down: right trigger value, else 0.
     */
    public static double triggersAsJoy(XboxController controller) {
        double leftTrig = deadzone(controller.getTriggerAxis(Hand.kLeft));
        double rightTrig = deadzone(controller.getTriggerAxis(Hand.kRight));
        double output = 0;
        if (leftTrig == 0 && rightTrig == 0) 
        {
        } else if (leftTrig > 0) {
            output = -leftTrig;
        } else if (rightTrig > 0) {
            output = rightTrig;
        }
        return output;
    }

    /**
     * Checks if the input is 0.
     * 
     * @param input
     * 
     * @return Whether the input is 0. (True/False)
     */
    public static boolean isZero(double input) {
        if (input == Finals.zero) {
            return true;
        } else {
            return false;
        }
    }




}