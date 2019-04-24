/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.statics_and_classes.Finals;
import frc.robot.statics_and_classes.Equations;

/**
 * Add your docs here.
 */
public class MecanumDriveSub extends Subsystem {
  
  private static DrivebaseContainer drivebaseContainer = Robot.drivebaseContainer;
  private static Equations equations;

  public static MecanumDrive mecDrive = new MecanumDrive(drivebaseContainer.frontLeftMotor, drivebaseContainer.rearLeftMotor, drivebaseContainer.frontRightMotor, drivebaseContainer.rearRightMotor);

  private static double newZero = Robot.navXGyro.getAngle();
  private static double lastInput;
  private static double kToleranceDegrees = 5;

  public static void Drive(double xInput, double yInput, double zInput) {
    mecDrive.driveCartesian(yInput, xInput, zInput, 0.0);
  }

  public static void Drive(double xInput, double yInput, double zInput, double gyroAngle) {
    mecDrive.driveCartesian(yInput, xInput, zInput, gyroAngle);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  
  // Move robot forward/backwards without rotation drifting by asigning a local
  // north and turning towards that
  // Strafe robot
  // Robot can still turn without inturupting movement
  // Relies on newZero to work
  public static void noDriftDrive(double yLeft, double xLeft, double xRight, AHRS gyro) {
    mecDrive.setSafetyEnabled(false);
    // Resets local north if turning

    double rotationSpeed = locRotationLock(xLeft, -xRight, gyro);

    // newZero = Robot.navXGyro.getAngle();
    mecDrive.driveCartesian(Equations.deadzone(xLeft), Equations.deadzone(-yLeft), rotationSpeed);
  }

  public static double locRotationLock(double xInput, double zInput, AHRS gyro) {
    // if Z axis joystick is moving or has moved within the past 0.4 seconds set doLocRot to "true", else leave as "false"
    boolean doLocRot = false;
    double rotationSpeed;
    if (equations.deadzone(zInput) != 0) {
      lastInput = System.currentTimeMillis();
    } else if (System.currentTimeMillis() - lastInput > 400) {
      doLocRot = true;
    }

    // if not locking rotation, roationSpeed equals Z axis input
    if (doLocRot == false) {
      newZero = gyro.getAngle();
      rotationSpeed = zInput;
    } else {
      rotationSpeed = turnSpeed(gyro, equations.isZero(xInput), newZero);
    }

    return -rotationSpeed;
  }

  public static double turnSpeed(AHRS gyro, Boolean isStrafing, double angleToTurnTo) {
    double angleOff = gyro.getAngle() - angleToTurnTo;
    // if "angleOff" is greater than kToleranceDegrees, rotationSpeed equals zero and robot does not turn
    if (Math.abs(angleOff) < kToleranceDegrees) {
      return 0;
    } else {
      // if strafing, set locRot power lower beacuse the wheels are already turning, they dont need to pass the minimum required torque
      double turnPower;
      if (isStrafing = true) {
        turnPower = equations.deadzone(equations.exponetialAbs((angleOff) / 180, 2), 0.25); // turnPower equals 
      } else {
        turnPower = equations.deadzone(equations.exponetialAbs((angleOff) / 180, 2), 0.35);
      }
      return equations.clamp(-1, 1, turnPower);
    }
  }
  


}
