// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoDriveBackwards;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.commands.ShootBall;
import frc.robot.commands.StartIntake;
import frc.robot.commands.StopIntake;
import frc.robot.commands.StopShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

  private final Joystick m_driveJoystick = new Joystick(0);

  private final XboxController m_Controller = new XboxController(1);

  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();

  private final Command m_autoCommand = new AutoDriveBackwards(m_driveSubsystem , m_shooterSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveSubsystem.setDefaultCommand( new JoystickDriveCommand( m_driveSubsystem, m_driveJoystick ));
    m_IntakeSubsystem.setDefaultCommand( new StopIntake(m_IntakeSubsystem));
    m_shooterSubsystem.setDefaultCommand( new StopShooter(m_shooterSubsystem));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton buttonA = new JoystickButton(m_Controller, XboxController.Button.kA.value);
    buttonA.whenHeld( new StartIntake(m_IntakeSubsystem)  );

    JoystickButton buttonB = new JoystickButton(m_Controller, XboxController.Button.kB.value);
    buttonB.whenPressed( new ShootBall(m_shooterSubsystem));

    new JoystickButton(m_Controller, XboxController.Button.kLeftBumper.value).whenPressed(
      
      new RunCommand( m_IntakeSubsystem::raise, m_IntakeSubsystem)
    );
 
    new JoystickButton(m_Controller, XboxController.Button.kRightBumper.value).whenPressed(
      
      new RunCommand( m_IntakeSubsystem::drop, m_IntakeSubsystem)
    );
 
    //.new JoystickButton(m_Controller, XboxController.Button.kX.value).whenHeld(
      
      //new RunCommand( m_shooterSubsystem::aimBack, m_shooterSubsystem)
    //);

   // new JoystickButton(m_Controller, XboxController.Button.kY.value).whenHeld(
      
     // new RunCommand( m_shooterSubsystem::aimForward, m_shooterSubsystem)
   // );

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
