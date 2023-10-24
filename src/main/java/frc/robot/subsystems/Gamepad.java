package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

public class Gamepad {
    private static int m_port;
    private XboxController m_controller;
    private static Gamepad mInstance = new Gamepad();

    public static Gamepad getInstance(int port){
        m_port = port;
        return mInstance;
    }

    public Gamepad(){
        m_controller = new XboxController(m_port);
    }

    public double getForwardDrive() {
        return m_controller.getRawAxis(1);
    }

    public double getTurnDrive() {
        return m_controller.getRawAxis(0);
    }

    public double getArmDrive(){
        return m_controller.getRawAxis(5);
    }

    public double getRightTriggerAxis(){
        return m_controller.getRightTriggerAxis();
    }
    
    public double getLeftTriggerAxis(){
        return m_controller.getLeftTriggerAxis();
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
