package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    private VictorSPX m_intake;
    private final static int intakeDeviceID = 5;

    public IntakeSubsystem() {

        m_intake = new VictorSPX(intakeDeviceID);
        m_intake.set(VictorSPXControlMode.PercentOutput, 0);
        
    }

    public void setMotor(double speed) {
        m_intake.set(VictorSPXControlMode.PercentOutput, speed);
    }    

}
