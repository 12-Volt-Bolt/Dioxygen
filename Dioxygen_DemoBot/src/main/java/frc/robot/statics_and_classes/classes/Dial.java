package frc.robot.statics_and_classes.classes;

import frc.robot.statics_and_classes.Equations;

public class Dial {

    public int value = 0;
    private int max;
    private boolean justTurned = false;

    /**
     * How many positions the dial has.
     */
    public Dial(int positions)
    {
        max = positions - 1;
    }

    /**
     * Increases the value of the dial by 1.
     */
    public void increment()
    {
        value++;
        wrap();
    }

    /**
     * Decreases the value of the dial by 1.
     */
    public void decrement()
    {
        value--;
        wrap();
    }

    /**
     * Sets the value of the dial.
     */
    public void set(int newValue)
    {
        value = newValue;
        wrap();
    }

    /**
     * Increments the dial only when 'input' changes to true, 'input' must change to false before the dial will increment again.
     * 
     * @return Whether or not the value changed.
     */
    public boolean incrementWhenTrue(boolean input)
    {
        boolean output = false;
        if (input == true && justTurned == false)
        {
            justTurned = true;
            increment();
            output = true;
        } else if (input == false)
        {
            justTurned = false;
        }
        return output;
    }

    /**
     * Resets the dial back to 0.
     */
    public void resetDial()
    {
        set(0);
    }

    private void wrap()
    {
        value = Equations.wrap(value, 0, max);
    }

    /**
     * @return The max value the switch will turn to.
     */
    public int getMax()
    {
        return max;
    }
}