// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootBall extends SequentialCommandGroup {

  private ShooterSubsystem m_shooterSubsystem;

  /** Creates a new ShootBall. */
  public ShootBall( ShooterSubsystem shooterSubsystem) {
    m_shooterSubsystem = shooterSubsystem;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands( 
      new RunCommand( m_shooterSubsystem::startFlywheelShoot, m_shooterSubsystem ).withTimeout(2),
      new RunCommand( m_shooterSubsystem::feedBall, m_shooterSubsystem ).withTimeout(2)

    );
    }

  public void HoldBall( ShooterSubsystem shooterSubsystem) {
    
    addCommands(
       new RunCommand( m_shooterSubsystem::startFlywheelHold, m_shooterSubsystem)
   );

     }
    }
    
    
  

      
  



  //public ShootBall( ShooterSubsystem shooterSubsystem) {
   // m_shooterSubsystem = shooterSubsystem;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    //addCommands( 
      //new RunCommand( m_shooterSubsystem::startFlywheel, m_shooterSubsystem ).withTimeout(2),
      //new RunCommand( m_shooterSubsystem::feedBall, m_shooterSubsystem ).withTimeout(2)

    //);

