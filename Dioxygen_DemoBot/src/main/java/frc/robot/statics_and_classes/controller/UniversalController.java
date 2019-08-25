package frc.robot.statics_and_classes.controller;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.statics_and_classes.Equations;
import frc.robot.statics_and_classes.controller.DPad;
import frc.robot.statics_and_classes.controller.DPad.Direction;
import edu.wpi.first.hal.HAL;

/**
 * UniversalController
 */
public class UniversalController {

    private Joystick stick;
    private int m_port;
    private short m_rightRumble = 0;
    private short m_leftRumble = 0;
    private int m_outputs;

    /**
     * Creates a new universal controller instance.
     *
     * @param port The driver station port that the controller is connected to.
     * @return The new controller.
     */
    public UniversalController(int port)
    {
        m_port = port;
        stick = new Joystick(port);
    }
    
    /**
     * Gets the double value of the axis.
     *
     * @param ID ID of the axis whose value should be returned. (Profile.Enum)
     * @return The double value of the axis.
     */
    public double getAxis(int ID)
    {
        return stick.getRawAxis(ID);
    }

    /**
     * Gets the deadzoned double value of the axis. If the raw value is within 0.1 of 0, the output value is rounded to 0.
     *
     * @param ID ID of the axis whose value should be returned. (Profile.Enum)
     * @return The deadzoned double value of the axis.
     */
    public double getAxisDeadzone(int ID)
    {
        return Equations.deadzone(stick.getRawAxis(ID));
    }

    /**
     * Gets the boolean value of the button.
     *
     * @param ID ID of the button whose value should be returned. (Profile.Enum)
     * @return The boolean value of the button.
     */
    public boolean getButton(int ID)
    {
        return stick.getRawButton(ID);
    }

    /**
     * Get the port number of the controller.
     *
     * @return The port number of the controller.
     */
    public int getPort()
    {
      return m_port;
    }
    
    /**
     * Get the angle in degrees of a POV on the HID.
     *
     * <p>The POV angles start at 0 in the up direction, and increase clockwise (eg right is 90,
     * upper-left is 315).
     *
     * @param ID The ID of the POV to read (starting at 0)
     * @return the angle of the POV in degrees, or -1 if the POV is not pressed.
     */
    public int getPOV(int ID)
    {
        return stick.getPOV(ID);
    }

    public int getPOV()
    {
        return getPOV(0);
    }

    /**
     * Returns the Direction Enum of a POV.
     * 
     * @param ID
     * @return
     */
    public Direction getDPad(int ID)
    {
        return DPad.getDirection(getPOV(ID));
    }

    public Direction getDPad()
    {
        return getDPad(0);
    }
    
    /**
     * Set the rumble output for the controller. The DS currently supports 2 rumble values, left rumble and right rumble.
     *
     * @param ID  The ID of the side the rumble value should be set too (Profile.enum)
     * @param intesity The normalized value (0 to 1) to set the rumble to
     */
    public void setRumble(int ID, double intesity)
    {
        Equations.clamp(intesity, 0, 1);
        if (ID == 0)
        {
            m_leftRumble = (short) (intesity * 65535);
        } else {
            m_rightRumble = (short) (intesity * 65535);
        }
        HAL.setJoystickOutputs((byte) m_port, m_outputs, m_leftRumble, m_rightRumble);
    }
}
