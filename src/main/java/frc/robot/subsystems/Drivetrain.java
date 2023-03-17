package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Constants;

public class Drivetrain {
  private static Drivetrain mDrivetrain_Instance = new Drivetrain();
  
  public DifferentialDrive m_robotDrive;
  public MotorControllerGroup m_leftDrive;
  public MotorControllerGroup m_rightDrive;
  
  public static Drivetrain getInstance(){
    return mDrivetrain_Instance;
  }
  
  public Drivetrain(){
    MotorController m_R_Top = new PWMSparkMax(Constants.R_T_Port);
    MotorController m_R_Bot= new PWMSparkMax(Constants.R_B_Port);
    MotorController m_L_Top = new PWMSparkMax(Constants.L_T_Port);
    MotorController m_L_Bot = new PWMSparkMax(Constants.L_B_Port);

    MotorControllerGroup m_leftDrive = new MotorControllerGroup(m_L_Top, m_L_Bot);
    MotorControllerGroup m_rightDrive = new MotorControllerGroup(m_R_Top, m_R_Bot);
    
    m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  }

  public void arcadeDrv(double spd, double rot){
    spd *= Constants.throttleMult;
    rot *= Constants.throttleMult;

    m_robotDrive.arcadeDrive(spd, rot, false);
  }

  public void engineStop(){
    m_robotDrive.stopMotor();
  }

  public void driveHalt(){
    m_robotDrive.tankDrive(0, 0);
  }
}