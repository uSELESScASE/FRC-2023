// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Gamepad;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.UCShuffleboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private Gamepad gamepad;
  private Drivetrain driveTrain;
  private Gripper mainGripper;
  private Arm mainArm;
  private Gyroscope acceleroMeter;
  double value;
  private final Timer m_generalTimer = new Timer();
  private final Timer m_autoTimer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = Drivetrain.getInstance();
    gamepad = Gamepad.getInstance(Constants.GAMEPAD_PORT);
    mainGripper = Gripper.getInstance();
    mainArm = Arm.getInstance();
    acceleroMeter = Gyroscope.getInstance();

    UCShuffleboard.robotInit();
  }

  @Override
  public void robotPeriodic() {
    UCShuffleboard.robotPeriodic();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {

    System.out.println("Starting Autonomous Mode...");

    m_generalTimer.reset();
    m_generalTimer.start();
    m_autoTimer.reset();
    mainGripper.autonomousPnoForward();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    while (acceleroMeter.accStabilizeValue() < 0.3 || m_generalTimer.get() < 1.5){
      Drivetrain.simpleTankDrv(0.8);
    } 
    m_autoTimer.start();
    while (m_autoTimer.get() < 1){
      Drivetrain.simpleTankDrv(0.8);
    }
    m_autoTimer.reset();
    acceleroMeter.accStabilize();
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    System.out.println("Starting Teleoperated Mode...");
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    
    double spd = gamepad.getForwardDrive();
    double rot = gamepad.getTurnDrive();
    double drvthr = gamepad.getRightTriggerAxis();
    double deg = gamepad.getArmDrive();
    double thr = gamepad.getLeftTriggerAxis();

    driveTrain.arcadeDrv(-rot, spd, drvthr);
    mainGripper.engageGripper(gamepad);
    mainArm.move(deg,thr);
  }

    /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}
      
    /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}