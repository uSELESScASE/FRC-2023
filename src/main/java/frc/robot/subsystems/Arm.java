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


    public void move(double deg, double thr){

        if (deg < 0.75){
            deg *= thr;
            VictorSP.set(deg);
        }
        else{
            deg = 0.75;
            deg *= thr;
            VictorSP.set(deg);
        }

    }
}
