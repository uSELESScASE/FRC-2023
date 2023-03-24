package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP
;
import frc.robot.Constants;

public class Arm {
    private static Arm m_Instance = new Arm();
    private static MotorController VictorSP;

    public static Arm getInstance(){
        return m_Instance;
    }

    Arm(){
        
        VictorSP = new VictorSP(Constants.Arm_Port);
    }

    public void move(double deg){
        if (deg > 0.5){
            deg = 0.5;
            
            //VictorSP.set(deg);
        }
        else if (deg < -0.5){
            deg = -0.5;
            
            //VictorSP.set(deg);
        }

        System.out.println(deg);
    }
}
