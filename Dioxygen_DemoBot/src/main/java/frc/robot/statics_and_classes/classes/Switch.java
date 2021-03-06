package frc.robot.statics_and_classes.classes;

public class Switch {

    public boolean state;
    private boolean justChanged;

    /**
     * Creates a new switch with an initial state of true.
     */
    public Switch()
    {
        state = true;
    }

    /**
     * Creates a new switch with an assigned initial state.
     */
    public Switch(boolean initialState)
    {
        state = initialState;
    }

    /**
     * Alternates the value of the switch.
     */
    public void flip()
    {
        state = !state;
    }

    /**
     * Flips the switch only when 'input' changes to true, 'input' must change to false before the switch will flip again.
     * 
     * @return Whether or not the value changed.
     */
    public boolean flipOnTrue(boolean input)
    {
        boolean output = false;
        if (input == true && justChanged == false)
        {
            justChanged = true;
            flip();
            output = true;
        } else if (input == false)
        {
            justChanged = false;
        }
        return output;
    }

    /**
     * Sets the value of the switch.
     */
    public void set(boolean newState)
    {
        state = newState;
    }
}