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
            System.out.println("check!");
            arduino.setTimeout(0.001);
         }
         catch (UncleanStatusException e){
         System.out.println("ERROR: Unable to connect to Arduino");
       }

    }

    public void readGyro(){
        ArrayList<byte[]> arduino_out = new ArrayList<byte[]>(6);
  
        arduino_out.add(arduino.read(6));
        System.out.println("raw arduino data" + arduino_out);

        String x_val = arduino_out.get(0).toString().concat(arduino_out.get(1).toString());
        String y_val = arduino_out.get(2).toString().concat(arduino_out.get(3).toString());
        String z_val = arduino_out.get(4).toString().concat(arduino_out.get(5).toString());

        System.out.println("X"+ x_val + "Y" + y_val + "Z" + z_val);
    }
  

 

    



}
