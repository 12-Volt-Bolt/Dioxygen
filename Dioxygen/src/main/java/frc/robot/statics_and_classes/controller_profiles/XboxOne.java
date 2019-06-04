package frc.robot.statics_and_classes.controller_profiles;

public class XboxOne {

    public enum Axis
    {
        leftX ("xAxis")
        , leftY ("yAxis")
        , leftTrigger ("uAxis")
        , rightTrigger ("vAxis")
        , rightX ("zAxis")
        , rightY ("wAxis");

        String trueAxis;

        Axis(String trueAxis)
        {
            this.trueAxis = trueAxis;
        }

        public String getTrueAxis()
        {
            return this.trueAxis;
        }
    }

    public enum Button
    {
        a
        , b
        , x
        , y
        , leftBumper
        , rightBumper
        , view
        , menu
        , leftStick
        , rightStick;
    }
}