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

        drive.simpleTankDrv(force_Y);
    }
}
