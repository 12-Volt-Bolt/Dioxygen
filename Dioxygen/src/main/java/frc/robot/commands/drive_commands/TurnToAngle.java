/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive_commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.MecanumDrive;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.Drivebase.driveSubsystemKeys;

public class TurnToAngle extends Command {
  private double angleToTurnTo;
  
  private AHRS gyro = Robot.navXGyro;
  
  private Drivebase drivebase = Robot.drivebase;

  public TurnToAngle(double angle) {
    requires(drivebase);

    angleToTurnTo = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Drivebase.setKey(driveSubsystemKeys.tankSub);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    TankDrive.arcadeDrive(0, MecanumDrive.turnSpeed(gyro, false, angleToTurnTo));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    Boolean output = false;
    if ((int) (gyro.getAngle() / 3) == (int) (angleToTurnTo / 3))
    {
      output = true;
    }
    return output;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Drivebase.Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Drivebase.Stop();
  }
}
