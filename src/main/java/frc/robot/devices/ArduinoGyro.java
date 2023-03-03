package frc.robot.devices;

import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Constants;

public class ArduinoGyro {

    SerialPort arduino;
    int[] xyzArray;

     // Function for conversion
     static int[] strToIntArr(String str)
     
     {
        String[] splitArray = str.split(" ");
        int[] array = new int[splitArray.length];
  
        /* 
        parsing the String argument as a signed decimal
        integer object and storing that integer into the
        array
        */

        for (int i = 0; i < splitArray.length; i++) {
            array[i] = Integer.parseInt(splitArray[i]);
        }
        return array;
     }
    
    private static ArduinoGyro m_arduinoInstance = new ArduinoGyro();

    public static ArduinoGyro getInstance(){
        return m_arduinoInstance;
    }

    public ArduinoGyro(){

        arduino = new SerialPort(9600 ,SerialPort.Port.kUSB);

    }

    public void ardnSendCmds(int resetCd, int calibCd){
        byte mode1 = 0;  //////// 0b<reset><calibrate>
				if (resetCd == 1)  mode1 |= 0x80; //0b10000000
                if (calibCd == 1)  mode1 |= 0x40; //0b01000000

                Constants.gyroReset = 0;
                Constants.gyroCalib = 0;
	
				
				byte[] mode2 = {mode1};
				arduino.write(mode2, 1);
    }


    private float getRaws(int axis){

        if (arduino.getBytesReceived() > 0){
            String rcvRaw = arduino.readString();
            xyzArray = strToIntArr(rcvRaw);
            System.out.println(xyzArray);

        }

        if (axis == 1){
            System.out.println((float)xyzArray[0]);
            return (float)xyzArray[0];
        }

        if (axis == 2){
            System.out.println((float)xyzArray[1]);
            return (float)xyzArray[1];
        }

        if (axis == 3){
            System.out.println((float)xyzArray[2]);
            return (float)xyzArray[2];
        }

        else{
            System.out.println();
            return 0;
        }
    }

    public float getAngle(){

        return getRaws(0);

    }

    public void getRate(){

    }

    public void reset(){
        Constants.gyroReset = 1;

    }

    public void calibrate(){
        Constants.gyroCalib = 1;

    }
}
