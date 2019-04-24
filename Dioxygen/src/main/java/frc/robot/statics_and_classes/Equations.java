package frc.robot.statics_and_classes;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Equations {

    public static double deadzone(double number, double deadzone) {
        if (Math.abs(number) > Math.abs(deadzone)) {
            return number;
        } else {
            return 0;
        }
    }

    public static double deadzone(double number) {
        return deadzone(number, 0.1);
    }

    public static double clamp(double input, double min, double max) {
        if (input > max) {
            return max;
        } else if (input < min) {
            return min;
        } else {
            return 0;
        }
    }

    public static int clamp(int input, int min, int max) {
        if (input > max) {
            return max;
        } else if (input < min) {
            return min;
        } else {
            return 0;
        }
    }

    public static double exponetialAbs(double input, double power) {
        return Math.abs(Math.pow(input, power));
    }

    public static double triggersAsJoy(XboxController controller) {
        double leftTrig = deadzone(controller.getTriggerAxis(Hand.kLeft));
        double rightTrig = deadzone(controller.getTriggerAxis(Hand.kRight));
        if (leftTrig == 0 && rightTrig == 0) {
            return 0;
        } else if (leftTrig > 0) {
            return -leftTrig;
        } else if (rightTrig > 0) {
            return rightTrig;
        } else {
            return 0;
        }
    }

    public static boolean isZero(double input) {
        if (input == Finals.zero) {
            return true;
        } else {
            return false;
        }
    }




}