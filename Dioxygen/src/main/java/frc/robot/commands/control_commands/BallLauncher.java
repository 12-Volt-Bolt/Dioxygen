/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.statics_and_classes.RobotSwitches;
import frc.robot.statics_and_classes.RobotSwitches.Switches;

public class BallLauncher extends Command {

  private static Command ballLauncherSpinup = Robot.ballLauncherSpinup;
  private static Switches ballLauncherSafety = Robot.ballLauncherSafety;

  private static boolean launcherOn = false;

  public BallLauncher() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    boolean tempLauncherSafety = RobotSwitches.checkSwitch(ballLauncherSafety);
    if (tempLauncherSafety == false && launcherOn == false)
    {
      launcherOn = true;
      ballLauncherSpinup.start();
      System.out.println("Starting ball launcher command");
    } else if (tempLauncherSafety == true && launcherOn == true){
      launcherOn = false;
      ballLauncherSpinup.cancel();
      System.out.println("Stopping ball launcher command");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
