package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    private VictorSPX m_intake;
    private final static int intakeDeviceID = 2;

    private Solenoid m_intakePiston = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

    public IntakeSubsystem() {

        m_intake = new VictorSPX(intakeDeviceID);
        m_intake.configFactoryDefault();

        stop();

    }

    public void setSpeed(double speed) {
        m_intake.set(VictorSPXControlMode.PercentOutput, speed);
    }    

    public void stop() {
        setSpeed(0);
    }

    public void inward(){
        setSpeed(-0.78);
    }

  //  public void outward(){
       // setSpeed(-0.78);
   // }

    public void raise() {
        m_intakePiston.set(false);
    }

    public void drop() {
        m_intakePiston.set(true);
    }

}