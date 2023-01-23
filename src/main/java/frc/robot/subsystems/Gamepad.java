package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;

import frc.robot.Constants;

public class Gamepad {

    private static Gamepad mInstance = new Gamepad();

    public static Gamepad getInstance(){
        return mInstance;
    }

    XboxController m_controller;


    public Gamepad(){
        
        m_controller = new XboxController(Constants.XboxPort);
    }

    

    public double getFwd(){

        return m_controller.getRawAxis(0);
    }


    public double getRev(){

        return m_controller.getRawAxis(4);
    }

    public double getSteer(){

        return m_controller.getRawAxis(1);

    }

    public double getSensRotPressed(){
        Constants.throttleMult = m_controller.getRawAxis(3);
        return m_controller.getRawAxis(3);
    }
    
}
