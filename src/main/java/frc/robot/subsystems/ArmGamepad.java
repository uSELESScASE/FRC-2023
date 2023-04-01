package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

public class ArmGamepad {
    private static int nport;
    private XboxController m_controller;
    private static ArmGamepad mInstance = new ArmGamepad();

    public static ArmGamepad getInstance(int port){
        nport = port;
        return mInstance;
    }

    public ArmGamepad(){
        m_controller = new XboxController(nport);
    }

    public double getRightTriggerAxis(){
        return m_controller.getRightTriggerAxis();
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
}   
