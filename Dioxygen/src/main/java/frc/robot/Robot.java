/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import frc.robot.commands.ball_launcher.LauncherSpinup;
import frc.robot.commands.control_commands.BallLauncher;
import frc.robot.commands.control_commands.BallRelease;
import frc.robot.commands.control_commands.CompressorController;
import frc.robot.commands.control_commands.Drive;
import frc.robot.commands.driveCommands.BasicMecDrive;
import frc.robot.commands.driveCommands.BasicTankDrive;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.ball_launcher.CompressorControl;
import frc.robot.subsystems.ball_launcher.LauncherMotors;
import frc.robot.subsystems.ball_launcher.SolenoidControl;
import frc.robot.statics_and_classes.RobotDials;
import frc.robot.statics_and_classes.RobotSwitches;
import frc.robot.statics_and_classes.UniversalController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Required subsystems
  public static Drivebase drivebase = new Drivebase();
  public static LauncherMotors launcher = new LauncherMotors();
  public static CompressorControl compressorControl = new CompressorControl();
  public static SolenoidControl solenoids = new SolenoidControl();

  // UI elements
  public static OI m_oi;

  // Controllers
  public static final XboxController driveController = new XboxController(0);
  public static final UniversalController testController = new UniversalController(0);

  // Switches and Dials
  public static final int ballLauncherSafety = RobotSwitches.newSwitch();
  public static final int releaseBall = RobotSwitches.newSwitch();
  public static final int driveMode = RobotDials.newDial(2);
  public static final int compressorMode = RobotDials.newDial(3);

  // Commands
  public static Command basicTankDrive = new BasicTankDrive();
  public static Command basicMecDrive = new BasicMecDrive();
  public static Command ballLauncherSpinup = new LauncherSpinup();
  public static Command ballRelease = new BallRelease();
  //public static Command compressorOnTillDone = new CompressorOnTillDone(CompressorControl.compressor1);
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Control Commands
  private static Command drive = new Drive();
  private static Command ballLauncher = new BallLauncher();
  private static Command compressorController = new CompressorController(CompressorControl.compressor1);

  // Robot Sensors
  public static AHRS navXGyro;
  public static DriverStation driverStation = DriverStation.getInstance();

  // press and hold prevention
  public static boolean startPressed = false;
  public static boolean launcherOn = false;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    try {
      // navXGyro = new AHRS(SerialPort.Port.kMXP);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating NAV-X Gyro (MXP)", true);
    }
    // drivebaseContainer.setValues();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();

    // navXGyro.reset();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    RobotSwitches.allSwitchesOn();
    RobotDials.resetAllDials();

    drive.start();
    ballLauncher.start();
    compressorController.start();
    ballRelease.start();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    RobotSwitches.alternateSwitch(ballLauncherSafety, driveController.getStartButton(), true);
    RobotSwitches.alternateSwitch(driveMode, driveController.getBackButton(), true);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
