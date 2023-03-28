package frc.robot.subsystems;

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
            System.out.println("check!");
            arduino.setTimeout(0.001);
         }
         catch (UncleanStatusException e){
         System.out.println("ERROR: Unable to connect to Arduino");
       }

    }

    public void readGyro(){
        arduino.readString();
        System.out.println(arduino.readString());
    }
  

 

    



}
