/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballLauncher;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.statics_and_classes.Equations;
import frc.robot.statics_and_classes.Safeties;
import frc.robot.statics_and_classes.Safeties.SafetySwitches;
import frc.robot.subsystems.BallLauncher.LauncherMotors;

public class LauncherSpinup extends Command {

  private static LauncherMotors launchMotors = Robot.ballLaunchMotors;
  private static XboxController drivController = Robot.driveController;
  private static Equations equations = Robot.equations;
  private static Safeties safetySystem = Robot.safetySystem;

  private static double maxMotorPower = 50;
  private static double minMotorPower = 0.2;
  private static int dPad = -1;
  private static boolean dPadPressed = false;
  private static int motorPower = 0;
  private static SafetySwitches launcherSafety = Robot.ballLauncherSafety;

  public LauncherSpinup() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(launchMotors);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    motorPower = 0;
    launchMotors.stopMotors();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    dPad = drivController.getPOV();

    if (dPad == -1)
    {
      dPadPressed = false;
    } else if (dPadPressed == false) 
    {
      dPadPressed = true;
      System.out.print("DPad being pressed: ");

      switch (dPad) {
        case 0:
          motorPower++;
          System.out.println("Increment ball launcher power.");
          break;
        case 180:
          motorPower--;
          System.out.println("decrement ball launcher power.");
          break;
      
        default:
          System.out.println("Not valid dpad input.");
          break;
      }

      motorPower = equations.clamp(motorPower, 0, 10);
    }

    if (motorPower == 0)
    {
      launchMotors.stopMotors();
    } else {
      launchMotors.runMotors(minMotorPower + (motorPower / maxMotorPower));
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean stopCommand = false;
    if (safetySystem.checkSwitch(launcherSafety) == true)
    {
      stopCommand = true;
    }
    return stopCommand;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    motorPower = 0;
    launchMotors.stopMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    motorPower = 0;
    launchMotors.stopMotors();
  }
}
