/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.BallLauncher;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.BallLauncherMap;
import frc.robot.statics_and_classes.Finals;

/**
 * Add your docs here.
 */
public class LauncherMotors extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final VictorSPX leftMotor  = new VictorSPX(BallLauncherMap.left.getMotorID());
  public static final VictorSPX rightMotor = new VictorSPX(BallLauncherMap.left.getMotorID());

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void stopMotors()
  {
    leftMotor.set(ControlMode.PercentOutput, Finals.zero);
    rightMotor.set(ControlMode.PercentOutput, Finals.zero);
  }

  public void runMotors(double speed)
  {
    leftMotor.set(ControlMode.PercentOutput, speed);
    rightMotor.set(ControlMode.PercentOutput, speed);
  }
}
