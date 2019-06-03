package frc.robot.statics_and_classes;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.robot.statics_and_classes.Equations;
import edu.wpi.first.hal.HAL;

/**
 * UniversalController
 */
public class UniversalController {

    private Equations equations;
    private DriverStation m_ds;
    private int m_port;

    /**
     * Creates a new generic controller instance.
     *
     * @param port The driver station port that the controller is connected to.
     * @return The new controller.
     */
    public UniversalController(int port)
    {
        equations = Robot.equations;
        m_ds = DriverStation.getInstance();
        m_port = port;
    }

    /**
     * Creates a new generic controller instance, using a specific controller profile.
     *
     * @param port The driver station port that the controller is connected to.
     * @param controllerProfile The profile that this controller will use.
     * @return The new controller.
     */
    public UniversalController(int port, String controllerProfile)
    {
        equations = Robot.equations;
        m_ds = DriverStation.getInstance();
        m_port = port;
    }

    /**
     * Gets the double value of the axis.
     *
     * @param axis Axis of the controller whose value should be returned. (Profile.Enum)
     * @return The double value of the axis.
     */
    public double getAxis(int axis)
    {
        return m_ds.getStickAxis(m_port, axis);
    }

    /**
     * Gets the deadzoned double value of the axis. If the raw value is within 0.1 of 0, the output value is rounded to 0.
     *
     * @param axis Axis of the controller whose value should be returned. (Profile.Enum)
     * @return The deadzoned double value of the axis.
     */
    public double getAxisDeadzone(int axis)
    {
        return equations.deadzone(m_ds.getStickAxis(m_port, axis));
    }

    /**
     * Gets the boolean value of the button.
     *
     * @param button Button of the controller whose value should be returned. (Profile.Enum)
     * @return The boolean value of the button.
     */
    public boolean getButton(int button)
    {
        return m_ds.getStickButton(m_port, button);
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
     * @param pov The index of the POV to read (starting at 0)
     * @return the angle of the POV in degrees, or -1 if the POV is not pressed.
     */
    public int getPOV(int pov)
    {
        return m_ds.getStickPOV(m_port, pov);
    }

    public int getPOV()
    {
        return getPOV(0);
    }

    private short m_rightRumble = 0;
    private short m_leftRumble = 0;
    private int m_outputs;
    
    /**
     * Set the rumble output for the controller. The DS currently supports 2 rumble values, left rumble and right rumble.
     *
     * @param type  Which rumble value to set (Profile.enum)
     * @param value The normalized value (0 to 1) to set the rumble to
     */
    public void setRumble(int rumbleSide, double value)
    {
        equations.clamp(value, 0, 1);
        if (rumbleSide == 0)
        {
            m_leftRumble = (short) (value * 65535);
        } else {
            m_rightRumble = (short) (value * 65535);
        }
        HAL.setJoystickOutputs((byte) m_port, m_outputs, m_leftRumble, m_rightRumble);
    }
}