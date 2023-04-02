package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import frc.robot.Constants;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();
    private double acc_Y;
    private static BuiltInAccelerometer accelerometer;
    private Drivetrain drive;
    LinearFilter xAccFilter = LinearFilter.movingAverage(14);

    public static Gyroscope getInstance(){
        return mInstance;
    }

    Gyroscope(){
        accelerometer = new BuiltInAccelerometer();
        drive = new Drivetrain();
    }


    public void accStabilize(){
        acc_Y = accelerometer.getY();
        double filteredxAccel = xAccFilter.calculate(acc_Y) * Constants.ACCELEROMETER_THROTTLE_MULTIPLY;

        drive.simpleTankDrv(filteredxAccel);
    }

    public double accStabilizeValue(){
        acc_Y = accelerometer.getY();
        double filteredxAccel = xAccFilter.calculate(acc_Y) * Constants.ACCELEROMETER_THROTTLE_MULTIPLY;

        return filteredxAccel;
    }
}
