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

    public double secondStickYAxis(){
        return flightStick.getY();
    }

    public double secondStickThrottleAxis(){
        return flightStick.getRawAxis(6);
    }

    public boolean getAButtonPressed(){
        return flightStick.getRawButton(0);
    }

    public boolean getXButtonPressed(){
        return flightStick.getRawButton(0);
    }

    public boolean getYButtonPressed(){
        return flightStick.getRawButton(0);
    }

    public double getRightThumbY(){
        return flightStick.getRawAxis(5);
    
    }

    
}
