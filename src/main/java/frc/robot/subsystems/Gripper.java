package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.Constants;

public class Gripper {

    private static Gripper m_Instance = new Gripper();
    private DoubleSolenoid mainSolenoid;
    private Compressor mainCompressor;

    public static Gripper getInstance(){
        return m_Instance;
    }

    Gripper() {
        mainSolenoid = Constants.MAIN_SOLENOID;
        mainCompressor = Constants.MAIN_COMPRESSOR;
    }

    public void engageGripper(FlightStick xbox){
        if (xbox.getYButtonPressed() == true){
            mainSolenoid.set(Value.kForward);
        }
        if (xbox.getAButtonPressed() == true) {
            mainSolenoid.set(Value.kReverse);
        }
        if (xbox.getXButtonPressed() == true){
            mainSolenoid.set(Value.kOff);
        }
    }

    public void autonomousPnoForward(){
        mainSolenoid.set(Value.kReverse);
    }

    public void manageCompressor() {
        if (mainCompressor.isEnabled() == false){
            mainCompressor.enableDigital();
        }
        mainCompressor.getPressureSwitchValue();
        mainCompressor.getCurrent();
    }
}
