/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pnumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.statics_and_classes.classes.Dial;
import frc.robot.subsystems.ball_launcher.CompressorControl;

public class CompressorOnTillDone extends Command {

  private static CompressorControl compressorControl = Robot.compressorControl;
  private static Dial compressorMode = Robot.compressorMode;

  private static Compressor compressor;
  private static boolean end = false;

  public CompressorOnTillDone(Compressor newCompressor) {
    requires(compressorControl);
    compressor = newCompressor;
    System.out.println(compressor);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
	  CompressorControl.compressorOn(CompressorControl.compressor1);
    System.out.println("Compressor '" + CompressorControl.compressor1.getName() + "' is now on until the tank finishes filling.");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (CompressorControl.compressor1.enabled() == false)
    {
      compressorMode.set(0);
      CompressorControl.compressorOff(CompressorControl.compressor1);
      end = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return end;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("'CompressorOnTillDone' canceled, the compressor will remain on until turned off.");
  }
}
