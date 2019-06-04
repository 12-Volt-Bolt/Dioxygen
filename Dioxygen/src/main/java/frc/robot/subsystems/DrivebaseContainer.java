/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap.DriveMotors;
import frc.robot.statics_and_classes.Finals;

public class DrivebaseContainer extends Subsystem {

  public static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(DriveMotors.frontLeft.getMotorID());
  public static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(DriveMotors.frontRight.getMotorID());
  public static WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(DriveMotors.rearLeft.getMotorID());
  public static WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(DriveMotors.rearRight.getMotorID());

  public static SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
  public static SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
  
  public void initDefaultCommand() {
  }
  
  public static void Stop() {
    rightSide.set(Finals.zero);
    leftSide.set(Finals.zero);
  }
}
