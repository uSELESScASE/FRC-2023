package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

import frc.robot.Constants;

public class Arm {
    private static Arm m_Instance = new Arm();
    private static MotorController VictorSP;
    LinearFilter filter = LinearFilter.movingAverage(20);

    public static Arm getInstance(){
        return m_Instance;
    }

    Arm(){ 
        VictorSP = new VictorSP(Constants.ARM_PORT);
    }


    public void move(double deg, double thr){
        double limiter;

        if (thr >= 0.5) {
            limiter = 0.5;
        } else if (thr <= -0.5) {
            limiter = -0.5;
        } else {
            limiter = thr;
        }

        deg *= limiter;
        // Kod Çalışmassa deg yerine limiteri kullan
        double filteredDeg = filter.calculate(deg);

        VictorSP.set(-filteredDeg);

    }
}