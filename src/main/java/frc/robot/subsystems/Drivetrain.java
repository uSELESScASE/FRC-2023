package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import frc.robot.Constants;

public class Drivetrain {
  private static Drivetrain mDrivetrain_Instance = new Drivetrain();
  static LinearFilter smoother = LinearFilter.movingAverage(8);
  
  public static DifferentialDrive m_robotDrive;
  public MotorControllerGroup m_leftDrive;
  public MotorControllerGroup m_rightDrive;
  
  public static Drivetrain getInstance(){
    return mDrivetrain_Instance;
  }
  
  public Drivetrain(){    
    m_robotDrive = Constants.ROBOT_DRIVE;
  }

  public void arcadeDrv(double spd, double rot, double drivethr){
    spd *= drivethr;
    rot *= drivethr;

    m_robotDrive.arcadeDrive(spd, rot, false);
  }

  public void engineStop(){
    m_robotDrive.stopMotor();
  }

  public void driveHalt(){
    m_robotDrive.tankDrive(0, 0);
  }

  public static void simpleTankDrv(double spd){
    double smoothSpeed = smoother.calculate(spd);

    m_robotDrive.tankDrive(smoothSpeed,-smoothSpeed);
  }
}