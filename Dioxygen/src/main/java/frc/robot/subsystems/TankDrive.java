/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.Robot;
import frc.robot.statics_and_classes.Finals;
import frc.robot.subsystems.DrivebaseContainer;

public class TankDrive extends Subsystem {

  private static DrivebaseContainer drivebaseContainer = Robot.drivebaseContainer;

  public static DifferentialDrive drive = new DifferentialDrive(drivebaseContainer.leftSide, drivebaseContainer.rightSide);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void Drive(double yInput, double zInput) {
    drive.arcadeDrive(yInput, zInput);
  }

  public static void Stop() {
    drivebaseContainer.rightSide.set(Finals.zero);
    drivebaseContainer.leftSide.set(Finals.zero);
  }
  
}