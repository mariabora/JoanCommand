// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private CANSparkMax m_left;
  private CANSparkMax m_leftSlave;
  private CANSparkMax m_right;
  private CANSparkMax m_rightSlave;
  private static final int leftDeviceID = 1; 
  private static final int rightDeviceID = 2;
  private static final int leftDeviceID2 = 3; 
  private static final int rightDeviceID2 = 4;
  private final DifferentialDrive m_drive;

  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {

    m_left = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
    m_leftSlave = new CANSparkMax(leftDeviceID2, MotorType.kBrushless);
    m_leftSlave.follow(m_left);



    m_right = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
    m_rightSlave = new CANSparkMax(rightDeviceID2, MotorType.kBrushless);
    m_rightSlave.follow(m_right);
     

    m_left.stopMotor();
    m_right.stopMotor();
    m_right.setInverted(true);

    m_drive = new DifferentialDrive(m_left, m_right);

  }
  public void position(double x, double y) {
    m_drive.arcadeDrive(y, x);
  }

  @Override
  public void periodic() {
   
  }

  public void setMotors(double leftSpeed, double rightSpeed) {
    m_left.set(leftSpeed);
    m_right.set(rightSpeed);

      }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
