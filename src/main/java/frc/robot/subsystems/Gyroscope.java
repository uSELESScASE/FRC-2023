package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import frc.robot.Constants;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();
    private static double acc_Y;
    private static double force_Y;
    private static BuiltInAccelerometer accelerometer;
    private static Drivetrain drive;

    public static Gyroscope getInstance(){
        return mInstance;
    }

    Gyroscope(){
        accelerometer = new BuiltInAccelerometer();
        drive = new Drivetrain();
    }


    public void accStabilize(){
        acc_Y = accelerometer.getY();
        force_Y = acc_Y * Constants.accelerometerThrottleMult;
        double maxValue;

        if (force_Y < 0.6) {
         maxValue = force_Y;
        } else {
            maxValue = 0.6;
        }
        System.out.println(maxValue);
        drive.simpleTankDrv(maxValue);
    }
}
