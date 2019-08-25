package frc.robot.statics_and_classes.controller.controller_profiles;

public class MadCatzV1 {

    public final Boolean isGeneric = true;
    
    public enum Axis{
          XAxis ("xAxis", "xAxis", 1)
        , YAxis ("yAxis", "yAxis", 2)
        , ZAxis ("zAxis", "zAxis", 3)
        , WAxis ("wAxis", ""     , 4);

        private final int ID;
        private final String genericName;
        private final String trueName;
    
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

    /*
     * Represents a digital button on an MadCatzV1 controller.
     */
    public enum Button
    {
          trigger     ("button1", "Main1" , 0)
        , button2     ("button2", "Main2" , 1)
        , button3     ("button3", "Left"  , 2)
        , button4     ("button4", "Right" , 3)
        , button5     ("button5", "Menu"  , 4)
        , button6     ("button6", "Start" , 5)
        , pinkyButton ("button7", ""      , 6);

        
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
        thumbStick ("dPad", 0);

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