package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Constants;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();
    private static double acc_Y;
    private static double force_Y;
    private static BuiltInAccelerometer accelerometer;
    private static Drivetrain drivetrain;

    public static Gyroscope getInstance(){
        return mInstance;
    }

    Gyroscope(){
        accelerometer = new BuiltInAccelerometer();
        drivetrain = new Drivetrain();
    }


    public void accStabilize(){

        acc_Y = accelerometer.getY();
        force_Y = -acc_Y * Constants.accelerometerThrottleMult;

        drivetrain.simpleTankDrv(force_Y);



    }

 

    



}
