package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

public class Gyroscope {

    private static Gyroscope mInstance = new Gyroscope();

    public static Gyroscope getInstance(){
        return mInstance;
    }

    private static byte L3GD20H_ADDRESS;
    private static int REGISTER_PWR_MGMT;
    private static int LOW_ODR;
    private static int CTRL_REG_1;
    private static int CTRL_REG_4;


    Gyroscope(){

        L3GD20H_ADDRESS = 0x6B;
        REGISTER_PWR_MGMT = 0x20;
    }

    private I2C accelerometer = new I2C(I2C.Port.kOnboard ,L3GD20H_ADDRESS);
    private byte[] buffer = new byte[6];


    public void gyroRead(){
        
    }

    public void wake_gyro(){
        accelerometer.write(REGISTER_PWR_MGMT, 0x0F);
        accelerometer.write(CTRL_REG_1, 0x6F);
        accelerometer.write(CTRL_REG_4, 0x00);
        accelerometer.write(LOW_ODR, 0x00);
    }



}
