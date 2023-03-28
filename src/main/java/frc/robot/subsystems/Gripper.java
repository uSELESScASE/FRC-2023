package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
            Constants.pneomaticSetForwardWidget.getEntry().setBoolean(true);
        } else {
            if (xbox.getAButtonPressed() != true){
                Constants.pneomaticSetForwardWidget.getEntry().setBoolean(false);
            }

        }
        if (xbox.getYButtonPressed() == true) { // take driver opinion to change these top two to shoulder button inputs
            mainSolenoid.set(Value.kReverse);
            Constants.pneomaticSetReverseWidget.getEntry().setBoolean(true);
        } else {
            if (xbox.getYButtonPressed() != true){
                Constants.pneomaticSetReverseWidget.getEntry().setBoolean(false);
            }
        }
        if (xbox.getXButtonPressed() == true){
            mainSolenoid.set(Value.kOff);
            Constants.pneomaticSetOffWidget.getEntry().setBoolean(true);
        } else {
            if (xbox.getXButtonPressed() != true){
                Constants.pneomaticSetOffWidget.getEntry().setBoolean(false);
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
