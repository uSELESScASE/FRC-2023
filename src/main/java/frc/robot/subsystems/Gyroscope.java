package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.SerialPort;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();
    private SerialPort arduino;
    

    public static Gyroscope getInstance(){
        return mInstance;
    }

    public void setUpGyro(){
        try {
            arduino = new SerialPort(9600, SerialPort.Port.kUSB);
            arduino.setTimeout(0.001);
         }
         catch (UncleanStatusException e){
         System.out.println("ERROR: Unable to connect to Arduino");
       }

    }

    public void readGyro(){
        // create a connection code form roboRIO to L3GD20H gyro
        try{

            arduino.readString(3);
  
          } catch(Exception e){
              System.out.println("ERROR: Blank Arduino output");
          }
    }
  

 

    



}
