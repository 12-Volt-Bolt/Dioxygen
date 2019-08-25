package frc.robot.statics_and_classes.controller.controller_profiles;

public class XboxOne {

    public final Boolean isGeneric = true;

    public enum Axis
    {
          leftX        ("L X Axis" , "xAxis", 0)
        , leftY        ("L Y Axis" , "yAxis", 1)
        , leftTrigger  ("L Trigger", ""     , 2)
        , rightTrigger ("R Trigger", ""     , 3)
        , rightX       ("R X Axis" , "zAxis", 4)
        , rightY       ("R Y Axis" , ""     , 5);

        private final String trueName;
        private final String genericName;
        private final int ID;

        private Axis(String trueName, String genericName, int ID)
        {
            this.trueName = trueName;
            this.genericName = genericName;
            this.ID = ID;
        }

        public String getTrueName()
        {
            return this.trueName;
        }

        public String getGenericName()
        {
            return this.genericName;
        }

        public int getID()
        {
            return this.ID;
        }
    }

    public enum Button
    {
          a           ("aButton"          , "Main1", 0)
        , b           ("bButton"          , "Main2", 1)
        , x           ("xButton"          , ""     , 2)
        , y           ("yButton"          , ""     , 3)
        , leftBumper  ("leftBumperButton" , "Left" , 4)
        , rightBumper ("rightBumperButton", "Right", 5)
        , view        ("viewButton"       , "Start", 6)
        , menu        ("menuButton"       , "Menu" , 7)
        , leftStick   ("leftStickButton"  , ""     , 8)
        , rightStick  ("rightStickButton" , ""     , 9);

        
        private final String trueName;
        private final String genericName;
        private final int ID;

        private Button(String trueName, String genericName, int ID)
        {
            this.trueName = trueName;
            this.genericName = genericName;
            this.ID = ID;
        }

        public String getTrueName()
        {
            return this.trueName;
        }

        public String getGenericName()
        {
            return this.genericName;
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

    public enum Rumble
    {
          leftRumble
        , rightRumble;

        public int getID()
        {
            return ordinal();
        }
    }
}