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

    public double secondStickThrottleAxis(){
        return flightStick.getRawAxis(2);
    }

    public boolean getAButtonPressed(){
        return flightStick.getRawButtonPressed(1);
    }

    public boolean getXButtonPressed(){
        return flightStick.getRawButtonPressed(2);
    }

    public boolean getYButtonPressed(){
        return flightStick.getRawButtonPressed(3);
    }

    public double getRightThumbY(){
        return flightStick.getRawAxis(5);
    }
}
