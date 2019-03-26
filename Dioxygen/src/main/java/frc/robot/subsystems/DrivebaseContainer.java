/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.DriveMotors;

/**
 * Add your docs here.
 */
public class DrivebaseContainer extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public final WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(DriveMotors.frontLeft.ordinal());
  public final WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(DriveMotors.frontRight.ordinal());
  public final WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(DriveMotors.rearLeft.ordinal());
  public final WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(DriveMotors.rearRight.ordinal());

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
