/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.statics_and_classes;

/**
 * Add your docs here.
 */
public class RobotSwitches {

    private static boolean[] switchValues = new boolean[10];
    
    private static boolean[] switchAlternated = new boolean[10];

    /**
     * Static switches that can be turned on or off 
     */
    public enum Switches
    {
        safety0
        , safety1
        , safety2
        , safety3
        , safety4
        , safety5
        , safety6
        , safety7
        , safety8
        , safety9;

        private static int usedSwitches = 0;

        /**
         * Creates a new switch.
         * 
         * @return A newly created switch.
         */
        public static Switches newSwitch()
        {
            usedSwitches++;
            return Switches.values()[usedSwitches - 1];
        }
    }

    /**
     * Turns all switches off.
     */
    public static void allRobotSwitchesOn()
    {
        for (int i = 0; i < switchValues.length; i++)
        {
            switchValues[i] = true;
        }
    }

    /**
     * Turns a switch on.
     * 
     * @param yourSwitch The switch you want to turn on.
     */
    public static void switchOn(Switches yourSwitch)
    {
        switchValues[yourSwitch.ordinal()] = true;
    }

    /**
     * Turns a switch off.
     * 
     * @param yourSwitch The switch you want to turn off.
     */
    public static void switchOff(Switches yourSwitch)
    {
        switchValues[yourSwitch.ordinal()] = false;
    }
    /**
     * Alternates the value of a switch.
     * 
     * @param yourSwitch The switch you want to alternate.
     */
    public static void alternateSwitch(Switches yourSwitch)
    {
        boolean switchValue = switchValues[yourSwitch.ordinal()];
        if (switchValue == false)
        {
            System.out.println(yourSwitch + " is now on.");
            switchValue = true;
        } else {
            System.out.println(yourSwitch + " is now off.");
            switchValue = false;
        }

        switchValues[yourSwitch.ordinal()] = switchValue;
    }

    /**
     * Alternates the value of a switch once while inputs are equal.
     * 
     * @param yourSwitch The switch you want to alternate.
     * @param input The changing value that will be compared.
     * @param compareValue The final value that the 'input' is compared to.
     */
    public static void alternateSwitch(Switches yourSwitch, Boolean input, Boolean compareValue)
    {
        boolean alternated = switchAlternated[yourSwitch.ordinal()];
        if (input != compareValue)
        {
            alternated = false;
        } else if (alternated == false) {
          alternateSwitch(yourSwitch);
          alternated = true;
        }
        switchAlternated[yourSwitch.ordinal()] = alternated;
    }

    /**
     * Returns the value of a switch.
     * 
     * @param yourSwitch The switch you want to check.
     */
    public static boolean checkSwitch(Switches yourSwitch)
    {
        return switchValues[yourSwitch.ordinal()];
    }
}
