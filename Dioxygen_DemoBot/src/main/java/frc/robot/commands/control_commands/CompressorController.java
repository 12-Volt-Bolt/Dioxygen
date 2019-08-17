/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control_commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.pneumatics.CompressorOnForTime;
import frc.robot.statics_and_classes.classes.Dial;
import frc.robot.subsystems.ball_launcher.CompressorControl;

public class CompressorController extends Command {

  private static XboxController driveController = Robot.driveController;

  private static Compressor compressor;

  private static final Dial compressorMode = Robot.compressorMode;

  public CompressorController(Compressor newCompressor) {
    compressor = newCompressor;
  }
  
  private static Command compressorOnTillDone = new CompressorOnForTime(compressor, 30);

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    CompressorControl.compressorOff(compressor);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (compressorMode.incrementWhenTrue(driveController.getXButton()))
    {
      switch (compressorMode.value) {
        case 0:
          compressorOnTillDone.cancel();
          CompressorControl.compressorOff(compressor);
          break;
        case 1:
          CompressorControl.compressorOn(compressor);
          System.out.println("Compressor '" + compressor.getName() + "' is now on.");
          break;
        case 2:
          compressorOnTillDone.start();
          break;
        default:
          break;
      }
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
