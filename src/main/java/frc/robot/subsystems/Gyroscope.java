package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import frc.robot.Constants;

/* uSELESScASE Gyro Wannabe Accelerometer Code
 * 
 * We used BuiltInAccelerometer since gyros were really expensive.
 * This code has been licensed under MIT (https://choosealicense.com/licenses/mit/).
 * Feel free to use it, just let us know if you wanna use it.
*/
public class Gyroscope {
    private static Gyroscope paINSTANCE = new Gyroscope();
    private double aCCELEROMETER_gETy;
    private static BuiltInAccelerometer aCEELEROMETER;
    private Drivetrain dRIVE;
    LinearFilter xaCCfILTER = LinearFilter.movingAverage(16);

    public static Gyroscope getInstance(){
        return paINSTANCE;
    }

    Gyroscope(){
        aCEELEROMETER = new BuiltInAccelerometer();
        dRIVE = new Drivetrain();
    }


    public void accStabilize(){
        aCCELEROMETER_gETy = aCEELEROMETER.getY();
        double filteredxAccel = xaCCfILTER.calculate(aCCELEROMETER_gETy);

        dRIVE.simpleTankDrv(filteredxAccel * Constants.ACCELEROMETER_THROTTLE_MULTIPLY);
    }

    public double accStabilizeValue(){
        aCCELEROMETER_gETy = aCEELEROMETER.getY();
        double filteredxAccel = xaCCfILTER.calculate(aCCELEROMETER_gETy);

        return (filteredxAccel * Constants.ACCELEROMETER_THROTTLE_MULTIPLY);
    }
}
