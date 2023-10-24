package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

import frc.robot.Constants;

public class Arm {
    private static Arm m_Instance = new Arm();
    private static MotorController VictorSP;
    LinearFilter filter = LinearFilter.movingAverage(10);

    public static Arm getInstance(){
        return m_Instance;
    }

    Arm(){ 
        VictorSP = new VictorSP(Constants.ARM_PORT);
    }


    public void move(double deg, double thr){
        double limiter;
        double limiterValue = 0.8;
        
        if (thr >= limiterValue) {
            limiter = limiterValue;
        } else if (thr <= -limiterValue) {
            limiter = -limiterValue;
        } else {
            limiter = thr;
        }

        deg *= limiter;

        double filteredDeg = filter.calculate(deg);
        VictorSP.set(filteredDeg);
    }

    public void moveValue(double deg, double thr){
        double limiter;
        double limiterValue = 0.8;

        if (thr >= limiterValue) {
            limiter = limiterValue;
        } else if (thr <= -limiterValue) {
            limiter = -limiterValue;
        } else {
            limiter = thr;
        }
        
        deg *= limiter;

        double filteredDeg = filter.calculate(deg);
        System.out.println(filteredDeg);
    }
}