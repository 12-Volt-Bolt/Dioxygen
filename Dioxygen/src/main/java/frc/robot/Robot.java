/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.ballLauncher.LauncherSpinup;
import frc.robot.commands.driveCommands.BasicMecDrive;
import frc.robot.commands.driveCommands.BasicTankDrive;
import frc.robot.subsystems.DrivebaseContainer;
import frc.robot.subsystems.MecanumDriveSub;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.BallLauncher.LauncherMotors;
import frc.robot.statics_and_classes.Equations;
import frc.robot.statics_and_classes.RobotSwitches;
import frc.robot.statics_and_classes.UniversalController;
import frc.robot.statics_and_classes.RobotSwitches.Switches;
import frc.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Generic scripts
  public static Equations equations = new Equations();
  public static RobotMap robotMap = new RobotMap();
  public static RobotMap.DriveMotors driveMotors;
  public static RobotSwitches switchSystem = new RobotSwitches();

  // Subsystems
  public static final DrivebaseContainer drivebaseContainer = new DrivebaseContainer();
  public static final TankDrive tankDrive = new TankDrive();
  public static final MecanumDriveSub mecDrive = new MecanumDriveSub();
  public static final LauncherMotors ballLaunchMotors = new LauncherMotors();

  // UI elements
  public static OI m_oi;

  // Controllers
  public static final XboxController driveController = new XboxController(0);
  public static final UniversalController testController = new UniversalController(0);

  // Switches
  public static final Switches ballLauncherSafety = Switches.newSwitch();
  public static final Switches doMecanumDrive = Switches.newSwitch();

  // private OneMotorTest motorTester = new OneMotorTest(15);

  // Commands
  static Command basicTankDrive = new BasicTankDrive();
  static Command basicMecDrive = new BasicMecDrive();
  static Command ballLauncherSpinup = new LauncherSpinup();
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Robot Sensors
  public static AHRS navXGyro;
  public static DriverStation driverStation = DriverStation.getInstance();

  // press and hold prevention
  public static boolean startPressed = false;
  public static boolean launcherOn = false;
  public static boolean doMechanum = false;

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
    switchSystem.allRobotSwitchesOn();

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    switchSystem.alternateSwitch(ballLauncherSafety, driveController.getStartButton(), true);
    switchSystem.alternateSwitch(doMecanumDrive, driveController.getBackButton(), true);
    ballLaucher();
    driving();
  }

  private void driving()
  {
    boolean tempDoMecanum = switchSystem.checkSwitch(doMecanumDrive);
    if (tempDoMecanum == false && doMechanum == false)
    {
      doMechanum = true;
      basicMecDrive.cancel();
      basicTankDrive.start();
      System.out.println("Switching to tank mode.");
    } else if (tempDoMecanum == true && doMechanum == true){
      doMechanum = false;
      basicTankDrive.cancel();
      basicMecDrive.start();
      System.out.println("Switching to Mecanum mode.");
    }
  }

  private void ballLaucher()
  {
    boolean tempLauncherSafety = switchSystem.checkSwitch(ballLauncherSafety);
    if (tempLauncherSafety == false && launcherOn == false)
    {
      launcherOn = true;
      ballLauncherSpinup.start();
      System.out.println("Starting ball launcher command");
    } else if (tempLauncherSafety == true && launcherOn == true){
      launcherOn = false;
      ballLauncherSpinup.cancel();
      System.out.println("Stopping ball launcher command");
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
