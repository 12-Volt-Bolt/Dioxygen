package frc.robot.statics_and_classes.classes;

import frc.robot.statics_and_classes.Equations;

public class Dial {

    public int value = 0;
    private int max;
    private boolean justTurned = false;

    public Dial(int positions)
    {
        max = positions - 1;
    }

    public void increment()
    {
        value++;
        wrap();
    }

    public void decrement()
    {
        value--;
        wrap();
    }

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

    public int getMax()
    {
        return max;
    }
}