package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();

    public static Gyroscope getInstance(){
        return mInstance;
    }

    private static byte L3GD20H_ADDRESS;
    private static int REGISTER_PWR_MGMT;
    private static int REGISTER_GYRO;


    Gyroscope(){

        L3GD20H_ADDRESS = 0x6B;
        REGISTER_PWR_MGMT = 0x20;
        REGISTER_GYRO = 0x29;
    }

    private I2C accelerometer = new I2C(I2C.Port.kOnboard ,L3GD20H_ADDRESS);
    private byte[] buffer = new byte[6];


    public void gyroRead(){

        accelerometer.read(REGISTER_GYRO, 6, buffer);
        
        int x = (buffer[0] << 8) | buffer[1];
        int y = (buffer[2] << 8) | buffer[3];
        int z = (buffer[4] << 8) | buffer[5];
    
        //System.out.println(x + "," + y + "," + z);
        
    }

    public void wake_gyro(){
        accelerometer.write(REGISTER_PWR_MGMT, 0x0F);
    }
}
