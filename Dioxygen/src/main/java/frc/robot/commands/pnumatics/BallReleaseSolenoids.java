/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pnumatics;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap.Pnumatics;
import frc.robot.subsystems.ball_launcher.SolenoidControl;

public class BallReleaseSolenoids extends Command {

  private static SolenoidControl solenoids = Robot.solenoids;

  public BallReleaseSolenoids() {
    requires(solenoids);
  }

  private static double endTime = 0;
  private static boolean end = false;
  private static boolean newRelease = true;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (System.currentTimeMillis() > endTime && endTime != 0)
    {
      SolenoidControl.solenoidOn(Pnumatics.topSolenoid);
      SolenoidControl.solenoidOn(Pnumatics.bottomSolenoid);

      end = true;
      newRelease = true;
      endTime = 0;
    } else if (newRelease == true) {
      newRelease = false;
      endTime = System.currentTimeMillis() + 1000;
      SolenoidControl.solenoidOff(Pnumatics.bottomSolenoid);
      SolenoidControl.solenoidOff(Pnumatics.topSolenoid);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean output = false;
    if (end == true)
    {
      end = false;
      output = true;
    }
    return output;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
