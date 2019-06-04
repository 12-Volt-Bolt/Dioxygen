package frc.robot.statics_and_classes.controller_profiles;

public class XboxOne {

    public final Boolean isGeneric = true;

    public enum Axis
    {
          leftX        ("xAxis", 0)
        , leftY        ("yAxis", 1)
        , leftTrigger  ("uAxis", 2)
        , rightTrigger ("vAxis", 3)
        , rightX       ("zAxis", 4)
        , rightY       ("wAxis", 5);

        private final String trueName;
        private final int ID;

        private Axis(String trueName, int ID)
        {
            this.trueName = trueName;
            this.ID = ID;
        }

        public String getTrueName()
        {
            return this.trueName;
        }

        public int getID()
        {
            return this.ID;
        }
    }

    public enum Button
    {
        a             ("aButton",           0)
        , b           ("bButton",           1)
        , x           ("xButton",           2)
        , y           ("yButton",           3)
        , leftBumper  ("leftBumperButton",  4)
        , rightBumper ("rightBumperButton", 5)
        , view        ("viewButton",        6)
        , menu        ("menuButton",        7)
        , leftStick   ("leftStickButton",   8)
        , rightStick  ("rightStickButton",  9);

        
        private final String trueName;
        private final int ID;

        private Button(String trueName, int ID)
        {
            this.trueName = trueName;
            this.ID = ID;
        }

        public String getTrueName()
        {
            return this.trueName;
        }

        public int getID()
        {
            return this.ID;
        }
    }

    public enum POV
    {
        dPad ("dPad", 0);

        private final String trueName;
        private final int ID;

        private POV(String trueName, int ID)
        {
            this.trueName = trueName;
            this.ID = ID;
        }

        public String getTrueName()
        {
            return this.trueName;
        }

        public int getID()
        {
            return this.ID;
        }
    }
}