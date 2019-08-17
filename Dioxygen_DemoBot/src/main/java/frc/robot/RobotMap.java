/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public enum DriveMotors
  {
    frontLeft    (12)
    , frontRight (13)
    , rearLeft   (14)
    , rearRight  (15);

    int motorID;
    
    DriveMotors(int motorID)
    {
      this.motorID = motorID;
    }

    public int getID()
    {
      return this.motorID;
    }
  }

  public enum BallLauncherMap
  {
    right  (2)
    , left (1);

    int motorID;

    BallLauncherMap(int motorID)
    {
      this.motorID = motorID;
    }

    public int getID()
    {
      return this.motorID;
    }
  }

  public enum pneumatics
  {
    compressor       (0)
    , topSolenoid    (0)
    , bottomSolenoid (1);

    int pneumaticID;

    pneumatics(int pneumaticID)
    {
      this.pneumaticID = pneumaticID;
    }

    public int getID()
    {
      return this.pneumaticID;
    }
  }
}
