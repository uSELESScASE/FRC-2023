package frc.robot.subsystems;


import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.devices.ArduinoGyro;

public class mGyro implements Sendable {

    private static mGyro m_GyroInstance = new mGyro();

    public ArduinoGyro m_Gyro;

    public static mGyro getInstance(){
        return m_GyroInstance;
    }


    mGyro(){

        m_Gyro = new ArduinoGyro();

    }

    public void getReadout(){
        System.out.println(Math.round(m_Gyro.getAngle()));

    }

    public void platformStabilize(){

    }


    @Override
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        
    }

}
