/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.statics_and_classes.Finals;

/**
 * Add your docs here.
 */
public class MecanumDriveSub extends Subsystem {
  
  private static DrivebaseContainer drivebaseContainer = Robot.drivebaseContainer;

  public static MecanumDrive mecDrive = new MecanumDrive(drivebaseContainer.frontLeftMotor, drivebaseContainer.rearLeftMotor, drivebaseContainer.frontRightMotor, drivebaseContainer.rearRightMotor);

  public static void Drive(double xInput, double yInput, double zInput) {
    mecDrive.driveCartesian(yInput, xInput, zInput, 0.0);
  }

  public static void Drive(double xInput, double yInput, double zInput, double gyroAngle) {
    mecDrive.driveCartesian(yInput, xInput, zInput, gyroAngle);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void Stop() {
    drivebaseContainer.rightSide.set(Finals.zero);
    drivebaseContainer.leftSide.set(Finals.zero);
  }
}
