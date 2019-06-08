/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.Robot;
import frc.robot.RobotMap.DriveMotors;
import frc.robot.statics_and_classes.Equations;
import frc.robot.subsystems.Drivebase.driveSubsystemKeys;

/**
 * Add your docs here.
 */
public class MecanumDrive extends Subsystem {

  private static double newZero;
  private static double lastInput;
  private static double kToleranceDegrees = 5;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void setNewZero(double newNewZero)
  {
    newZero = newNewZero;
  }

  /**
   * The robot will move forward/backwards/strafing without its rotation drifting by asigning a local north and rotating towards that angle. 
   * The robot can still turn through user inputs without inturupting movement.
   * 
   * @param yLeft
   * @param xLeft
   * @param xRight
   * @param gyro
   */
  // Move robot forward/backwards without rotation drifting by asigning a local north and rotating towards that angle
  // Strafe robot
  // Robot can still turn without inturupting movement
  // Relies on newZero to work
  public static void noDriftDrive(double yLeft, double xLeft, double xRight, AHRS gyro)
  {
    // Resets local north if turning
    double rotationSpeed = locRotationLock(xLeft, -xRight, gyro);

    // newZero = Robot.navXGyro.getAngle();
    driveCartesian(Equations.deadzone(xLeft), Equations.deadzone(-yLeft), rotationSpeed);
  }

  public static double locRotationLock(double xInput, double zInput, AHRS gyro) {
    // if Z axis joystick is moving or has moved within the past 0.4 seconds set doLocRot to "true", else leave as "false"
    boolean doLocRot = false;
    double rotationSpeed;
    if (Equations.deadzone(zInput) != 0) {
      lastInput = System.currentTimeMillis();
    } else if (System.currentTimeMillis() - lastInput > 400) {
      doLocRot = true;
    }

    // if not locking rotation, roationSpeed equals Z axis input
    if (doLocRot == false) {
      newZero = gyro.getAngle();
      rotationSpeed = zInput;
    } else {
      rotationSpeed = turnSpeed(gyro, Equations.isZero(xInput), newZero);
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
        turnPower = Equations.deadzone(Equations.exponetialAbs((angleOff) / 180, 2), 0.25); // turnPower equals 
      } else {
        turnPower = Equations.deadzone(Equations.exponetialAbs((angleOff) / 180, 2), 0.35);
      }
      return Equations.clamp(-1, 1, turnPower);
    }
  }
  

  /**
   * Drive method for Mecanum platform.
   *
   * <p>Angles are measured clockwise from the positive X axis. The robot's speed is independent
   * from its angle or rotation rate.
   *
   * @param ySpeed    The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
   * @param xSpeed    The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
   *                  positive.
   */
  public static void driveCartesian(double ySpeed, double xSpeed, double zRotation)
  {
    driveCartesian(ySpeed, xSpeed, zRotation, 0.0);
  }

  /**
   * Drive method for Mecanum platform.
   *
   * <p>Angles are measured clockwise from the positive X axis. The robot's speed is independent
   * from its angle or rotation rate.
   *
   * @param ySpeed    The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
   * @param xSpeed    The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
   *                  positive.
   * @param gyroAngle The current angle reading from the gyro in degrees around the Z axis. Use
   *                  this to implement field-oriented controls.
   */
  public static void driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle)
  {
    ySpeed = Equations.clamp(ySpeed, -1, 1);
    ySpeed = Equations.deadzone(ySpeed);

    xSpeed = Equations.clamp(xSpeed, -1, 1);
    xSpeed = Equations.deadzone(xSpeed);

    // Compensate for gyro angle.
    Vector2d input = new Vector2d(xSpeed, ySpeed);
    input.rotate(-gyroAngle);

    double[] wheelSpeeds = new double[4];
    wheelSpeeds[DriveMotors.frontLeft.ordinal()] = input.x + input.y + zRotation;
    wheelSpeeds[DriveMotors.frontRight.ordinal()] = -input.x + input.y - zRotation;
    wheelSpeeds[DriveMotors.rearLeft.ordinal()] = -input.x + input.y + zRotation;
    wheelSpeeds[DriveMotors.rearRight.ordinal()] = input.x + input.y - zRotation;

    Equations.normalize(wheelSpeeds);

    Drivebase.setAllMotors(wheelSpeeds, driveSubsystemKeys.mecanumSub);
  }

}
