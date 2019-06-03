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

  public static WPI_TalonSRX frontLeftMotor;
  public static WPI_TalonSRX frontRightMotor;
  public static WPI_TalonSRX rearLeftMotor;
  public static WPI_TalonSRX rearRightMotor;

  public static SpeedControllerGroup leftSide;
  public static SpeedControllerGroup rightSide;
  
  public void initDefaultCommand() {
  }

  public void setValues() {
    frontLeftMotor = new WPI_TalonSRX(DriveMotors.frontLeft.ordinal());
    frontRightMotor = new WPI_TalonSRX(DriveMotors.frontRight.ordinal());
    rearLeftMotor = new WPI_TalonSRX(DriveMotors.rearLeft.ordinal());
    rearRightMotor = new WPI_TalonSRX(DriveMotors.rearRight.ordinal());

    leftSide = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
    rightSide = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
    System.out.println("System.out.println(frontLeftMotor.getBaseID()); " + frontLeftMotor.getBaseID());
  }

  public static void Stop() {
    rightSide.set(Finals.zero);
    leftSide.set(Finals.zero);
  }
}
