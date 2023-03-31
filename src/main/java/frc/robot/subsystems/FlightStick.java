package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

public class FlightStick {

    private static FlightStick mInstance = new FlightStick();

    public static FlightStick getInstance(){
        return mInstance;
    }

    private Joystick flightStick;

    FlightStick(){

        flightStick = new Joystick(Constants.JOYSTICK_PORT);
    }

    public double flightStickYAxis(){
        return flightStick.getY();
    }

    public double flightStickThrottleAxis(){

        double thr = flightStick.getThrottle();

        if (thr > 0.75){
            thr = 0.75;
        }

       if (thr >= 0){
         Constants.ARM_THROTTLE_MULTIPLY = flightStick.getThrottle();
       }

       thr = Constants.ARM_THROTTLE_MULTIPLY;

       return thr;
    }

    
}
