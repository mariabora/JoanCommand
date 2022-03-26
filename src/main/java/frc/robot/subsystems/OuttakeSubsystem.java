package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OuttakeSubsystem extends SubsystemBase {
    private VictorSPX m_outtake;
    private final static int outtakeDeviceID = 2;


    public OuttakeSubsystem() {

        m_outtake = new VictorSPX(outtakeDeviceID);
        m_outtake.configFactoryDefault();

        stop();

    }

    public void setSpeed(double speed) {
        m_outtake.set(VictorSPXControlMode.PercentOutput, speed);
    }    

    public void stop() {
        setSpeed(0);
    }

    public void outward(){
        setSpeed(0.78);
    }



    }
    

