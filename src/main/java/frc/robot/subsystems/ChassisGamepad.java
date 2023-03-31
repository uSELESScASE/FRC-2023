package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

public class ChassisGamepad {
    private static int nport;
    private XboxController m_controller;
    private static ChassisGamepad mInstance = new ChassisGamepad();

    public static ChassisGamepad getInstance(int port){
        nport = port;
        return mInstance;
    }

    public ChassisGamepad(){
        m_controller = new XboxController(nport);
    }

    public double getForwardDrive() {
        return m_controller.getRawAxis(0);
    }

    public double getTurnDrive() {
        return m_controller.getRawAxis(1);
    }

    public double getRightTriggerAxis(){
        return m_controller.getRightTriggerAxis();
    }
}   
