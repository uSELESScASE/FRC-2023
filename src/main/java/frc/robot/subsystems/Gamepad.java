package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Gamepad {
    private static int nport;
    private XboxController m_controller;
    private static Gamepad mInstance = new Gamepad();

    public static Gamepad getInstance(int port){
        nport = port;
        return mInstance;
    }

    public Gamepad(){
        m_controller = new XboxController(nport);
    }

    public double getFwd(){
        return m_controller.getRawAxis(0);
    }

    public double getRta(){
        double drvthr = m_controller.getRightTriggerAxis();

        Constants.throttleMult = drvthr;

        return drvthr;
    }

    public double getSteer(){
        return m_controller.getRawAxis(1);
    }

    public boolean getYButtonPressed(){
        return m_controller.getYButtonPressed();
    }

    public boolean getAButtonPressed(){
        return m_controller.getAButtonPressed();
    }

    public boolean getXButtonPressed(){
        return m_controller.getXButtonPressed();
    }

    public double getRightThumbY(){
        return m_controller.getRawAxis(5);
    }

    public double getLta(){
        double armthr = m_controller.getLeftTriggerAxis();

        Constants.armThrottleMult = armthr;

        return armthr;
    }
}