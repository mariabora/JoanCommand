package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private VictorSPX m_aimMotor;
    private static int aimerDeviceID = 1;
    private VictorSPX m_flyWheel;
    private static int flywheelDeviceID = 3;
    private VictorSPX m_feederMotor;
    private static int feederDeviceID = 4;
 //encoder???
    public ShooterSubsystem() {

        m_aimMotor = new VictorSPX(aimerDeviceID);
        m_feederMotor = new VictorSPX(feederDeviceID);
        m_flyWheel = new VictorSPX(flywheelDeviceID);

        m_flyWheel.configFactoryDefault();
        m_feederMotor.configFactoryDefault();
        m_aimMotor.configFactoryDefault();

        stop();


    }

    public void setAim(double speed) {
        m_aimMotor.set(VictorSPXControlMode.PercentOutput, speed);

    }    
    
    public void setFlyWheel(double speed) {
        m_flyWheel.set(VictorSPXControlMode.PercentOutput, speed);
    }

    public void setFeeder(double speed){
        m_feederMotor.set(VictorSPXControlMode.PercentOutput, speed);

    }

    public void stop(){
        setFeeder(0);
        setFlyWheel(0);
        setAim(0);
    }

    public void startFlywheel() {
        setFlyWheel(0.85);
    }

    public void feedBall() {
        setFeeder(-1);
    }

    public void aimBack() {
        setAim(-.3);
    }

    public void aimForward() {
        setAim(.3);
    }

}
