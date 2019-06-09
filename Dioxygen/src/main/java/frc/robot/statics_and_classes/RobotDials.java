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

    private static Dial[] dialValues = new Dial[10];
    
    private static boolean[] dialTurned = new boolean[10];

    public enum Dials
    {
        dial0
        , diall
        , dial2
        , dial3
        , dial4
        , dial5
        , dial6
        , dial7
        , dial8
        , dial9;

        public static int usedDials = 0;

        /**
         * Creates a new dial.
         * 
         * @return A newly created dial.
         */
        public static Dials newDial(int maxValue)
        {
            dialValues[usedDials] = new Dial(maxValue);
            usedDials++;
            return Dials.values()[usedDials - 1];
        }
    }

    /**
     * Sets the value of a dial to zero.
     */
    public static void resetDial(Dials yourDial)
    {
        dialValues[yourDial.ordinal()].currentvalue = 0;
        checkNewValue(yourDial);
    }

    /**
     * Sets all dial values to zero.
     */
    public static void resetAllDials()
    {
        for (int i = 0; i < Dials.usedDials; i++)
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
    public static void incrementDial(Dials yourDial)
    {
        dialValues[yourDial.ordinal()].currentvalue++;
        checkNewValue(yourDial);
    }

    /**
     * Subtracts 1 from a dial.
     * 
     * @param yourDial The dial you want to decrement.
     */
    public static void decrementDial(Dials yourDial)
    {
        dialValues[yourDial.ordinal()].currentvalue--;
        checkNewValue(yourDial);
    }

    public static boolean moveDialAndWait(Dials yourDial, boolean input, Boolean compareValue, Consumer<Dials> moveDialType)
    {
        boolean turned = dialTurned[yourDial.ordinal()];
        boolean output = false;
        if (input != compareValue)
        {
            turned = false;
        } else if (turned == false) {
            moveDialType.accept(yourDial);
            turned = true;
            output = true;
        }
        dialTurned[yourDial.ordinal()] = turned;
        return output;
    }

    public static int getDial(Dials yourDial)
    {
        return dialValues[yourDial.ordinal()].currentvalue;
    }

    /**
     * Sets the value of a dial.
     * 
     * @param yourDial The dial you want to change.
     * @param dialValue The amount you want to set the dial to.
     */
    public static void setDial(Dials yourDial, int dialValue)
    {
        dialValues[yourDial.ordinal()].currentvalue = dialValue;
        checkNewValue(yourDial);
    }

    /**
     * Adds or subtracts an amount to a dial.
     * 
     * @param yourDial The dial you want to "turn".
     * @param changeAmount The amount you want to "turn" the dial.
     */
    public static void turnDial(Dials yourDial, int changeAmount)
    {
        dialValues[yourDial.ordinal()].currentvalue += changeAmount;
        checkNewValue(yourDial);
    }

    private static void checkNewValue(Dials yourDial)
    {
        dialValues[yourDial.ordinal()].currentvalue = Equations.wrap(dialValues[yourDial.ordinal()].currentvalue, 0, dialValues[yourDial.ordinal()].maxValue);
        //System.out.println("Dial '" + yourDial + "' is now set to '" + dialValues[yourDial.ordinal()].currentvalue + "'.");
    }
}
