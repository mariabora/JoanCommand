package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private VictorSPX m_shooterAngle;
    private static int angleDeviceID = 6;
    private VictorSPX m_shooterWheels;
    private static int wheelsDeviceID = 7;
 //encoder???
    public ShooterSubsystem() {

        m_shooterAngle = new VictorSPX(angleDeviceID);
        m_shooterAngle.set(VictorSPXControlMode.PercentOutput, 0);
        m_shooterWheels = new VictorSPX(wheelsDeviceID);
        m_shooterWheels.set(VictorSPXControlMode.PercentOutput, 0);


    }

    public void setAngle(double speed) {
        m_shooterAngle.set(VictorSPXControlMode.PercentOutput, speed);

    }    
    
    public void setWheels(double speed) {
        m_shooterWheels.set(VictorSPXControlMode.PercentOutput, speed);
    }

}
