/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.statics_and_classes;

import java.util.function.Consumer;

import frc.robot.statics_and_classes.classes.Dial;

/**
 * Add your docs here.
 */
public class RobotDials {

    private static Dial[] dialValues = new Dial[0];
    
    private static boolean[] dialTurned = new boolean[0];
    
    /**
     * Creates a new dial.
     * 
     * @param dialPositions The amount of positions the dial contains. (2 positions minimum)
     * 
     * @return A newly created dial.
     */ 
    public static int newDial(int dialPositions)
    {
        int oldDialAmount = dialValues.length;
        Dial[] tempDials = new Dial[oldDialAmount];
        boolean[] tempTurned = new boolean[oldDialAmount];
        for (int i = 0; i < oldDialAmount; i++)
        {
            tempDials[i] = dialValues[i];
            tempTurned[i] = dialTurned[i];
        }
        tempDials[oldDialAmount] = new Dial(dialPositions - 1);
        tempTurned[oldDialAmount] = false;
        dialValues = tempDials;
        dialTurned = tempTurned;
        return oldDialAmount;
    }

    /**
     * Sets the value of a dial to zero.
     */
    public static void resetDial(int yourDial)
    {
        dialValues[yourDial].currentvalue = 0;
        checkNewValue(yourDial);
    }

    /**
     * Sets all dial values to zero.
     */
    public static void resetAllDials()
    {
        for (int i = 0; i < dialValues.length; i++)
        {
            dialValues[i].currentvalue = 0;
        }

        System.out.println("All dials are now set to zero.");
    }

    /**
     * Adds 1 to a dial.
     * 
     * @param yourDial The dial you want to increment.
     */
    public static void incrementDial(int yourDial)
    {
        dialValues[yourDial].currentvalue++;
        checkNewValue(yourDial);
    }

    /**
     * Subtracts 1 from a dial.
     * 
     * @param yourDial The dial you want to decrement.
     */
    public static void decrementDial(int yourDial)
    {
        dialValues[yourDial].currentvalue--;
        checkNewValue(yourDial);
    }

    public static boolean moveDialAndWait(int yourDial, boolean input, Boolean compareValue, Consumer<Integer> moveDialType)
    {
        boolean turned = dialTurned[yourDial];
        boolean output = false;
        if (input != compareValue)
        {
            turned = false;
        } else if (turned == false) {
            moveDialType.accept(yourDial);
            turned = true;
            output = true;
        }
        dialTurned[yourDial] = turned;
        return output;
    }

    public static int getDial(int yourDial)
    {
        return dialValues[yourDial].currentvalue;
    }

    /**
     * Sets the value of a dial.
     * 
     * @param yourDial The dial you want to change.
     * @param dialValue The amount you want to set the dial to.
     */
    public static void setDial(int yourDial, int dialValue)
    {
        dialValues[yourDial].currentvalue = dialValue;
        checkNewValue(yourDial);
    }

    /**
     * Adds or subtracts an amount to a dial.
     * 
     * @param yourDial The dial you want to "turn".
     * @param changeAmount The amount you want to "turn" the dial.
     */
    public static void turnDial(int yourDial, int changeAmount)
    {
        dialValues[yourDial].currentvalue += changeAmount;
        checkNewValue(yourDial);
    }

    private static void checkNewValue(int yourDial)
    {
        dialValues[yourDial].currentvalue = Equations.wrap(dialValues[yourDial].currentvalue, 0, dialValues[yourDial].maxValue);
        //System.out.println("Dial '" + yourDial + "' is now set to '" + dialValues[yourDial.ordinal()].currentvalue + "'.");
    }
}
