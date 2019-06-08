/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap.DriveMotors;
import frc.robot.statics_and_classes.Equations;
import frc.robot.statics_and_classes.Finals;

public class DrivebaseContainer extends Subsystem {

  public enum driveSubsystemKeys
  {
    mecanumSub
    , tankSub;
  }

  public static void setKey(driveSubsystemKeys newKey)
  {
    currentKey = newKey;
  }

  private static Equations equations = Robot.equations;

  public static final WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(DriveMotors.frontLeft.getMotorID());
  public static final WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(DriveMotors.frontRight.getMotorID());
  public static final WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(DriveMotors.rearLeft.getMotorID());
  public static final WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(DriveMotors.rearRight.getMotorID());

  public static final SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
  public static final SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
  
  public static final double maxOutput = 1;

  private static double rightSideInvertMultiplier = -1;
  private static driveSubsystemKeys currentKey;
  
  public void initDefaultCommand() {
  }
  
  public static void Stop() {
    rightSide.set(Finals.zero);
    leftSide.set(Finals.zero);
  }

  public static void invertRightSide()
  {
    rightSideInvertMultiplier = -rightSideInvertMultiplier;
  }

  public static void setAllMotors(double[] speeds, driveSubsystemKeys key)
  {
    if (key == currentKey)
    {
      double[] finalSpeeds = new double[4];
      for (int i = 0; i < finalSpeeds.length; i++)
      {
        finalSpeeds[i] = equations.clamp(speeds[i], -1, 1);
      }
      frontLeftMotor.set(finalSpeeds[DriveMotors.frontLeft.ordinal()]);
      frontRightMotor.set(finalSpeeds[DriveMotors.frontRight.ordinal()] * rightSideInvertMultiplier);
      rearLeftMotor.set(finalSpeeds[DriveMotors.rearLeft.ordinal()]);
      rearRightMotor.set(finalSpeeds[DriveMotors.rearRight.ordinal()] * rightSideInvertMultiplier);
    } else {
      System.out.println("The given key '" + key + "' does not match the currently checked out key '" + currentKey + "'.");
    }
  }
}
