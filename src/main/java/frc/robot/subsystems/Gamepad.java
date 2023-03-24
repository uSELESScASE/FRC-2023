package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Gamepad {
    private XboxController m_controller;
    private static Gamepad mInstance = new Gamepad();

    public static Gamepad getInstance(){
        return mInstance;
    }

    public Gamepad(){
        m_controller = new XboxController(Constants.XboxPort);
    }

    public double getFwd(){
        return m_controller.getRawAxis(0);
    }

    public double getRta(){
        return m_controller.getRightTriggerAxis();
    }

    public double getSteer(){
        return m_controller.getRawAxis(1);
    }

    public double getSensRotPressed(){
        return m_controller.getRawAxis(5);
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
}