/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap.DriveMotors;
import frc.robot.statics_and_classes.Equations;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Drivebase.driveSubsystemKeys;

public class TankDrive extends Subsystem {

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Arcade drive method for differential drive platform.
   * The calculated values will be squared to decrease sensitivity at low speeds.
   *
   * @param xSpeed    The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
   *                  positive.
   */
  public static void arcadeDrive(double xSpeed, double zRotation) {
    arcadeDrive(xSpeed, zRotation, true);
  }

  /**
   * Arcade drive method for differential drive platform.
   *
   * @param xSpeed        The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation     The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
   *                      positive.
   * @param squareInputs If set, decreases the input sensitivity at low speeds.
   */
  public static void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {

    xSpeed = Equations.clamp(xSpeed, -1, 1);
    xSpeed = Equations.deadzone(xSpeed);

    zRotation = Equations.clamp(zRotation, -1, 1);
    zRotation = Equations.deadzone(zRotation);

    // Square the inputs (while preserving the sign) to increase fine control
    // while permitting full power.
    if (squareInputs) {
      xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
      zRotation = Math.copySign(zRotation * zRotation, zRotation);
    }

    double leftMotorOutput;
    double rightMotorOutput;

    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

    if (xSpeed >= 0.0) {
      // First quadrant, else second quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      } else {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      }
    } else {
      // Third quadrant, else fourth quadrant
      if (zRotation >= 0.0) {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      } else {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      }
    }

    double[] wheelSpeeds = new double[4];
    wheelSpeeds[DriveMotors.frontLeft.ordinal()] = leftMotorOutput;
    wheelSpeeds[DriveMotors.frontRight.ordinal()] = rightMotorOutput;
    wheelSpeeds[DriveMotors.rearLeft.ordinal()] = leftMotorOutput;
    wheelSpeeds[DriveMotors.rearRight.ordinal()] = rightMotorOutput;

    Drivebase.setAllMotors(wheelSpeeds, driveSubsystemKeys.tankSub);
  }

  
}
