package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import frc.robot.Constants;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();
    private double acc_Y;
    private double force_Y;
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
        double filteredxAccel = xAccFilter.calculate(acc_Y);

        System.out.println(filteredxAccel);
        drive.simpleTankDrv(filteredxAccel * Constants.ACCELEROMETER_THROTTLE_MULTIPLY);
    }

    public void accStabilizeValue(){
        acc_Y = accelerometer.getY();
        double filteredxAccel = xAccFilter.calculate(acc_Y);

        System.out.println(filteredxAccel * Constants.ACCELEROMETER_THROTTLE_MULTIPLY);
    }
}
