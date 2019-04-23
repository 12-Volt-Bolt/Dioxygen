package frc.robot.statics_and_classes;


public class Equations {

    public static double deadzone(double number, double limit) {
        if (Math.abs(number) > Math.abs(limit)) {
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

    public static double exponetialAbs(double input, double power) {
        return Math.abs(Math.pow(input, power));
    }
}