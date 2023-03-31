package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class XboxGamepad2 {
    private static int nport;
    private XboxController m_controller;
    private static XboxGamepad2 mInstance = new XboxGamepad2();

    public static XboxGamepad2 getInstance(int port){
        nport = port;
        System.out.println(port);
        return mInstance;
    }

    public XboxGamepad2(){
        m_controller = new XboxController(nport);
    }

    public double getRta(){
        double drvthr = m_controller.getRightTriggerAxis();

        Constants.throttleMult = drvthr;

        return drvthr;
    }

    public boolean getYButtonPressed(){
        return m_controller.getYButtonPressed();
    }
    public boolean getYButtonReleased(){
        return m_controller.getYButtonReleased();
    }

    public boolean getAButtonPressed(){
        return m_controller.getAButtonPressed();
    }
    public boolean getAButtonReleased(){
        return m_controller.getAButtonReleased();
    }

    public boolean getXButtonPressed(){
        return m_controller.getXButtonPressed();
    }

    public double getRightThumbY(){
        return m_controller.getRawAxis(3);
    }

    public double getLta(){
        double armthr = m_controller.getLeftTriggerAxis();

        Constants.armThrottleMult = armthr;

        return armthr;
    }
}   
