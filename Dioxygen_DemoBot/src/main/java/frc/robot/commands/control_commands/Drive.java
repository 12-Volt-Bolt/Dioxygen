/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.statics_and_classes.classes.Dial;

public class Drive extends Command {

  private static XboxController driveController = Robot.driveController;
  private static Command basicMecDrive = Robot.basicMecDrive;
  private static Command basicTankDrive = Robot.basicTankDrive;
  private static final Dial driveMode = Robot.driveMode;

  int pastMode = 0;

  public Drive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    basicTankDrive.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (driveMode.incrementWhenTrue(driveController.getBackButton()))
    {
      switch (driveMode.value) {
        case 0:
          basicMecDrive.cancel();
          basicTankDrive.start();
          System.out.println("Switching to tank mode.");
          break;
      
        case 1:
          basicTankDrive.cancel();
          basicMecDrive.start();
          System.out.println("Switching to Mecanum mode.");
          break;
    
        default:
          System.out.println("Switch 'driveMode' out of bounds!");
          break;
      }
    }
    pastMode = driveMode.value;
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
