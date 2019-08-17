/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.statics_and_classes.Equations;
import frc.robot.RobotMap.DriveMotors;
import frc.robot.statics_and_classes.Finals;

public class Drivebase extends Subsystem {

  /**
   * The availible subsystem keys. Add more for each new subsystem added.
   */
  public enum driveSubsystemKeys
  {
    mecanumSub
    , tankSub;
  }

  private static driveSubsystemKeys currentKey;

  public static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(DriveMotors.frontLeft.getID());
  public static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(DriveMotors.frontRight.getID());
  public static WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(DriveMotors.rearLeft.getID());
  public static WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(DriveMotors.rearRight.getID());

  private static int rightSideInverter = -1;
  
  public void initDefaultCommand() {
  }
  
  /**
   * Sets the current value of the key.
   * 
   * @param newKey
   */
  public static void setKey(driveSubsystemKeys newKey)
  {
    currentKey = newKey;
  }

  /**
   * Sets all motors to speed 0.
   */
  public static void Stop() {
    frontLeftMotor.set(Finals.zero);
    frontRightMotor.set(Finals.zero);
    rearLeftMotor.set(Finals.zero);
    rearRightMotor.set(Finals.zero);
  }

  /**
   * Reverses the direction of the right side drive motors.
   */
  public static void flipRightSide()
  {
    rightSideInverter = -rightSideInverter;
  }

  /**
   * Sets the speeds of the drive motors.
   * 
   * @param motorSpeeds Double array of motor speeds. Motor order is determined by robotmap ordinals.
   * @param subsystemKey The key of the subsystem Drivebase is being called from. If this key and the key set by the Command don't match the motors won't be set.
   */
  public static void setAllMotors(double[] motorSpeeds, driveSubsystemKeys subsystemKey)
  {
    if(currentKey == subsystemKey)
    {
      double[] speeds = motorSpeeds;
      Equations.normalize(speeds);
      
      frontLeftMotor.set(speeds[DriveMotors.frontLeft.ordinal()]);
      frontRightMotor.set(speeds[DriveMotors.frontRight.ordinal()] * rightSideInverter);
      rearLeftMotor.set(speeds[DriveMotors.rearLeft.ordinal()]);
      rearRightMotor.set(speeds[DriveMotors.rearRight.ordinal()] * rightSideInverter);
    } else
    {
      System.out.println("Subsystem key '" + subsystemKey + "' does not match the current key '" + currentKey + "'.");
    }
  }
}
