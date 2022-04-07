// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.OuttakeSubsystem;

public class StartEject extends SequentialCommandGroup {
  private OuttakeSubsystem m_OuttakeSubsystem;

  
  public void initialize() {}

  
  public void executeEject() {
    m_OuttakeSubsystem.outward(); 
  }

  public void end(boolean interrupted) {}

  // Returns true when the command should end.

  public boolean isFinishedEject() {
    return false;
  }

    
      // Use addRequirements() here to declare subsystem dependencies.
    

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  

  // Called just before this Command runs the first time
  //@Override
  protected void initializeEject() {}

  // Called repeatedly when this Command is scheduled to run
  //@Override
  public void execute() {}

  // Make this return true when this Command no longer needs to run execute()
  //@Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
 
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run

  protected void interrupted() {}
}
