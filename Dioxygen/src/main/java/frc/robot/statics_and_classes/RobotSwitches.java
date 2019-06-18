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

    private static boolean[] switchValues = new boolean[0];
    
    private static boolean[] switchAlternated = new boolean[0];
    
    /**
    * Creates a new switch.
    * 
    * @return A newly created switch.
    */
    public static int newSwitch()
    {
        int oldSwitchAmount = switchValues.length;
        boolean[] tempSwitches = new boolean[oldSwitchAmount + 1];
        boolean[] tempAlternated = new boolean[oldSwitchAmount + 1];
        for (int i = 0; i < oldSwitchAmount; i++)
        {
            tempSwitches[i] = switchValues[i];
            tempAlternated[i] = switchAlternated[i];
        }
        tempSwitches[oldSwitchAmount] = true;
        tempSwitches[oldSwitchAmount] = false;
        switchValues = tempSwitches;
        switchAlternated = tempAlternated;
        return oldSwitchAmount;
    }

    /**
     * Turns all switches off.
     */
    public static void allSwitchesOn()
    {
        for (int i = 0; i < switchValues.length; i++)
        {
            switchValues[i] = true;
            switchAlternated[i] = false;
        }

        System.out.println("All switches are now on.");
    }

    /**
     * Turns a switch on.
     * 
     * @param yourSwitch The switch you want to turn on.
     */
    public static void switchOn(int yourSwitch)
    {
        switchValues[yourSwitch] = true;
        //System.out.println(yourSwitch + " is now on.");
    }

    /**
     * Turns a switch off.
     * 
     * @param yourSwitch The switch you want to turn off.
     */
    public static void switchOff(int yourSwitch)
    {
        switchValues[yourSwitch] = false;
        //System.out.println(yourSwitch + " is now off.");
    }
    /**
     * Alternates the value of a switch.
     * 
     * @param yourSwitch The switch you want to alternate.
     */
    public static void alternateSwitch(int yourSwitch)
    {
        boolean switchValue = switchValues[yourSwitch];
        if (switchValue == false)
        {
            //System.out.println(yourSwitch + " is now on.");
            switchValue = true;
        } else {
            //System.out.println(yourSwitch + " is now off.");
            switchValue = false;
        }

        switchValues[yourSwitch] = switchValue;
    }

    /**
     * Alternates the value of a switch once while inputs are equal.
     * 
     * @param yourSwitch The switch you want to alternate.
     * @param input The changing value that will be compared.
     * @param compareValue The final value that the 'input' is compared to.
     */
    public static boolean alternateSwitch(int yourSwitch, Boolean input, Boolean compareValue)
    {
        boolean output = false;
        boolean alternated = switchAlternated[yourSwitch];
        if (input != compareValue)
        {
            alternated = false;
        } else if (alternated == false) {
            alternateSwitch(yourSwitch);
            alternated = true;
            output = true;
        }
        switchAlternated[yourSwitch] = alternated;
        return output;
    }

    /**
     * Returns the value of a switch.
     * 
     * @param yourSwitch The switch you want to check.
     */
    public static boolean checkSwitch(int yourSwitch)
    {
        return switchValues[yourSwitch];
    }
}
