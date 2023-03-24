package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP
;
import frc.robot.Constants;

public class Arm {
    private static MotorController VictorSP = new VictorSP(Constants.Arm_Port);
    public static Arm m_Instance = new Arm();

    public static void move(double deg){
        VictorSP.set(deg);
    }
}
