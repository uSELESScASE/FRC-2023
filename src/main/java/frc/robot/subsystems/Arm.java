package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

import frc.robot.Constants;

public class Arm {
    private static Arm m_Instance = new Arm();
    private static MotorController VictorSP;

    public static Arm getInstance(){
        return m_Instance;
    }

    Arm(){ 
        VictorSP = new VictorSP(Constants.ARM_PORT);
    }


    public void move(double deg, double thr){

        deg *= thr;

        VictorSP.set(deg);

    }
}