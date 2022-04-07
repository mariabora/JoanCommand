// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private CANSparkMax m_left;
  private CANSparkMax m_leftSlave;
  private CANSparkMax m_right;
  private CANSparkMax m_rightSlave;
  private static final int leftDeviceID = 1; 
  private static final int rightDeviceID = 4;
  private static final int leftDeviceID2 = 2; 
  private static final int rightDeviceID2 = 3;
  private final DifferentialDrive m_drive;
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  public Joystick m_Joystick;

  

  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {

    m_left = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
    m_leftSlave = new CANSparkMax(leftDeviceID2, MotorType.kBrushless);
    m_right = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
    m_rightSlave = new CANSparkMax(rightDeviceID2, MotorType.kBrushless);

    m_left.restoreFactoryDefaults();
    m_right.restoreFactoryDefaults();
    m_leftSlave.restoreFactoryDefaults();
    m_rightSlave.restoreFactoryDefaults();

   m_right.setIdleMode(CANSparkMax.IdleMode.kCoast);
   m_rightSlave.setIdleMode(CANSparkMax.IdleMode.kCoast);
   m_left.setIdleMode(CANSparkMax.IdleMode.kCoast);
   m_leftSlave.setIdleMode(CANSparkMax.IdleMode.kCoast);

   m_right.getIdleMode();
   m_rightSlave.getIdleMode();
   m_left.getIdleMode();
   m_leftSlave.getIdleMode();

    m_leftSlave.follow(m_left);
    m_rightSlave.follow(m_right);
     

    m_left.stopMotor();
    m_right.stopMotor();
    m_right.setInverted(true);

    
    m_drive = new DifferentialDrive(m_left, m_right);
    // PID coefficients
    kP = 6e-5; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000015; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);


  }
  public void position(double x, double y) {
    m_drive.arcadeDrive(y / 1.5, x / 1.5);
  }

  @Override
  public void periodic() {

    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { m_pidController.setP(p); kP = p; }
    if((i != kI)) { m_pidController.setI(i); kI = i; }
    if((d != kD)) { m_pidController.setD(d); kD = d; }
    if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      m_pidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }

    double setPoint = m_Joystick.getY()*maxRPM;
    m_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());
   
  }

  public void setMotors(double leftSpeed, double rightSpeed) {
    m_left.set(leftSpeed);
    m_right.set(rightSpeed);

      }

  public void stop() {
        m_drive.arcadeDrive(0, 0);
      }

  public void forwardSlowly() {
    m_drive.arcadeDrive( .5, 0);
  }

  @Override
  public void simulationPeriodic() {
  }
}
