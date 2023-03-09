package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.Constants;

public class Gamepad {
    private XboxController m_controller;
    private static Gamepad mInstance = new Gamepad();

    public double RobotFwd;
    public double RobotRev;

    public static Gamepad getInstance(){
        return mInstance;
    }

    public Gamepad(){
        m_controller = new XboxController(Constants.XboxPort);
    }
    
    public double getSteer(){
        return m_controller.getRightTriggerAxis();
    }

    public double getDPadFwd(){
        return m_controller.getPOV();
    }

    public double getDPadRev(){
        return m_controller;
    }

    public boolean getArmCalib(){
        return m_controller.getAButton();
    }

    public double getSensRotPressed(){
        Constants.throttleMult = m_controller.getRawAxis(3);

        return m_controller.getRawAxis(3);
    }

    public double getArmSpdPressed(){
        Constants.armThrottleMult = m_controller.getRawAxis(5);

        return m_controller.getRawAxis(5);
    }
}