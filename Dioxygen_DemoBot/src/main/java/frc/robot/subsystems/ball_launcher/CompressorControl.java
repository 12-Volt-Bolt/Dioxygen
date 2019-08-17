/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.ball_launcher;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.pneumatics;

/**
 * Add your docs here.
 */
public class CompressorControl extends Subsystem {

  public static Compressor compressor1 = new Compressor(pneumatics.compressor.getID());

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void compressorOn(Compressor compressor)
  {
    compressor.setClosedLoopControl(true);
  }

  public static void compressorOff(Compressor compressor)
  {
    compressor.setClosedLoopControl(false);
    System.out.println("Compressor '" + compressor.getName() + "' is now off.");
  }

  
}
