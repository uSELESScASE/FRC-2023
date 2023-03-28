// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.PAShuffle;
import frc.robot.subsystems.XboxGamepad;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private XboxGamepad xGamepad;
  private XboxGamepad xGamepad2;
  private Drivetrain Drive;
  private Gripper mainGripper;
  private Arm mainArm;
  private Gyroscope gyro;
  // private FlightStick flightStick;

  private final Timer m_timer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    Drive = Drivetrain.getInstance();
    xGamepad = XboxGamepad.getInstance(Constants.XboxPort);
    xGamepad2 = XboxGamepad.getInstance(Constants.Xbox_Port_2);
    mainGripper = Gripper.getInstance();
    mainArm = Arm.getInstance();
    gyro = Gyroscope.getInstance();
    // flightStick = FlightStick.getInstance();

    PAShuffle.onStart();
    gyro.setUpGyro();
  }

  @Override
  public void robotPeriodic() {
    PAShuffle.getRobotStatus();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    
    double spd = xGamepad.getFwd();
    double rot = xGamepad.getSteer();
    double drvthr = xGamepad.getRta();

    double deg = xGamepad2.getRightThumbY();
    double thr = xGamepad2.getLta();

    PAShuffle.inTeleopPeriod();

    Drive.arcadeDrv(-spd, -rot, drvthr);
    mainGripper.engageGripper(xGamepad);
    mainArm.move(deg,thr);
    gyro.readGyro();
  }
  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    
  }
}