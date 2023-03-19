package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import frc.robot.Constants;

public class Broomstick {
    private MotorController NEOSpark = new PWMSparkMax(Constants.Broom_Port);
    private int MotorPos = Constants.MotorPost;
    private DigitalInput armCalibSw = new DigitalInput(Constants.Switch_Chan);

    public static Broomstick m_Instance = new Broomstick();

    public void moveAngle(int spd){
        if (MotorPos < 180){
          spd *= Constants.armThrottleMult;
          MotorPos+= spd;

          NEOSpark.set(spd);
        }
    }

    public void zero_out(){
        while(!armCalibSw.get()){
            NEOSpark.set(0.05);
        }

        NEOSpark.set(0.0);
        MotorPos = 0;
    }
}
