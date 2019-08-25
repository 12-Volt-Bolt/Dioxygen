/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ball_launcher;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.statics_and_classes.Equations;
import frc.robot.statics_and_classes.classes.Switch;
import frc.robot.statics_and_classes.controller.UniversalController;
import frc.robot.statics_and_classes.controller.controller_profiles.XboxOne;
import frc.robot.subsystems.ball_launcher.LauncherMotors;

public class LauncherSpinup extends Command {

  private static UniversalController driveController = Robot.driveController;
  private static LauncherMotors launcher = Robot.launcher;

  private static double inputMaxMotorPower = 0.5;
  private static double minMotorPower = 0.2;
  private static double maxMotorPower = inputMaxMotorPower - minMotorPower;
  private static boolean dPadPressed = false;
  private static int motorPower = 0;
  private static final Switch launcherSafety = Robot.ballLauncherSafety;

  public LauncherSpinup() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(launcher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    motorPower = 0;
    LauncherMotors.stopMotors();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    dPadSpeed(driveController.getPOV(XboxOne.POV.dPad.getID()));

    if (motorPower == 0)
    {
      LauncherMotors.stopMotors();
    } else {
      LauncherMotors.runMotors(motorPower / 10.0 * maxMotorPower + 0.2);
    }

    stopOnB();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean stopCommand = false;
    if (launcherSafety.state == true)
    {
      stopCommand = true;
    }
    return stopCommand;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    motorPower = 0;
    LauncherMotors.stopMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    motorPower = 0;
    LauncherMotors.stopMotors();
  }

  private void dPadSpeed(int dPad)
  {
    if (dPad == -1)
    {
      dPadPressed = false;
    } else if (dPadPressed == false) 
    {
      dPadPressed = true;

      switch (dPad) {
        case 0:
          motorPower++;
          break;
        case 180:
          motorPower--;
          break;
      
        default:
          break;
      }
    }
    motorPower = Equations.clamp(motorPower, 0, 10);
  }

  private void stopOnB()
  {
    if (driveController.getButton(XboxOne.Button.b.getID()) == true)
    {
      LauncherMotors.stopMotors();
      motorPower = 0;
    }
  }
}
