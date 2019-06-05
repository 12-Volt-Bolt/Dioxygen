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
public class Safeties {

    private boolean[] safetyValues = new boolean[10];

    public enum SafetySwitches
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

        public static int usedSwitches = 0;

        public static SafetySwitches newSwitch()
        {
            usedSwitches++;
            return SafetySwitches.values()[usedSwitches - 1];
        }
    }

    public void allSwitchesOn()
    {
        for (int i = 0; i < safetyValues.length; i++)
        {
            safetyValues[i] = true;
        }
    }

    public void switchOn(SafetySwitches yourSwitch)
    {
        safetyValues[yourSwitch.ordinal()] = true;
    }

    public void switchOff(SafetySwitches yourSwitch)
    {
        safetyValues[yourSwitch.ordinal()] = false;
    }

    public void alternateSwitch(SafetySwitches yourSwitch)
    {
        if (safetyValues[yourSwitch.ordinal()] == false)
        {
            System.out.println(yourSwitch + " is now on.");
            safetyValues[yourSwitch.ordinal()] = true;
        } else {
            System.out.println(yourSwitch + " is now off.");
            safetyValues[yourSwitch.ordinal()] = false;
        }
    }

    public boolean checkSwitch(SafetySwitches yourSwitch)
    {
        return safetyValues[yourSwitch.ordinal()];
    }
}
