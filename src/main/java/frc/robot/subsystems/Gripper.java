package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.Constants;

public class Gripper {

    private static Gripper m_Instance = new Gripper();

    public static Gripper getInstance(){
        return m_Instance;
    }



    private DoubleSolenoid mainSolenoid;
    private Compressor mainCompressor;
    Gripper() {
        mainSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.forwardChannel, Constants.reverseChannel);
        mainCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
    }

    public void engageGripper(XboxGamepad xbox){

        if (xbox.getAButtonPressed() == true){
            mainSolenoid.set(Value.kForward);
            SmartDashboard.putBoolean("Set Forward", true);
        } else {
            if (xbox.getAButtonPressed() != true){
                SmartDashboard.putBoolean("Set Forward", false);
            }

        }
        if (xbox.getYButtonPressed() == true) { // take driver opinion to change these top two to shoulder button inputs
            mainSolenoid.set(Value.kReverse);
            SmartDashboard.putBoolean("Set Reverse", true);
        } else {
            if (xbox.getYButtonPressed() != true){
                SmartDashboard.putBoolean("Set Reverse", false);
            }
        }
        if (xbox.getXButtonPressed() == true){
            mainSolenoid.set(Value.kOff);
            SmartDashboard.putBoolean("Set Off", true);
        } else {
            if (xbox.getXButtonPressed() != true){
                SmartDashboard.putBoolean("Set Off", false);
            }
            
        }
    }

    public void manageCompressor() {
        if (mainCompressor.isEnabled() == false){
            mainCompressor.enableDigital();
        }
        mainCompressor.getPressureSwitchValue();
        mainCompressor.getCurrent();
    }
}
