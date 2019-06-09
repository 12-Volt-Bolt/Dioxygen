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

public class Drive extends Command {

  private static Command basicMecDrive = Robot.basicMecDrive;
  private static Command basicTankDrive = Robot.basicTankDrive;
  private static Switches doMecanumDrive = Robot.doMecanumDrive;

  boolean doMechanum = false;

  public Drive() {
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
    boolean tempDoMecanum = RobotSwitches.checkSwitch(doMecanumDrive);
    if (tempDoMecanum == false && doMechanum == false)
    {
      doMechanum = true;
      basicMecDrive.cancel();
      basicTankDrive.start();
      System.out.println("Switching to tank mode.");
    } else if (tempDoMecanum == true && doMechanum == true){
      doMechanum = false;
      basicTankDrive.cancel();
      basicMecDrive.start();
      System.out.println("Switching to Mecanum mode.");
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
