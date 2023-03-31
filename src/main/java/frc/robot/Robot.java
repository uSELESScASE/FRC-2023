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
import frc.robot.subsystems.XboxGamepad;
import frc.robot.subsystems.XboxGamepad2;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private XboxGamepad chassisGamepad;
  private XboxGamepad armGamepad;
  private Drivetrain driveTrain;
  private Gripper mainGripper;
  private Arm mainArm;
  private Gyroscope acceleroMeter;
  // private FlightStick flightStick;

  private final Timer m_timer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = Drivetrain.getInstance();
    chassisGamepad = XboxGamepad.getInstance(Constants.CHASSIS_XBOX_PORT);
    armGamepad = XboxGamepad.getInstance(Constants.ARM_XBOX_PORT);
    mainGripper = Gripper.getInstance();
    mainArm = Arm.getInstance();
    acceleroMeter = Gyroscope.getInstance();
    // flightStick = FlightStick.getInstance();


  }

  @Override
  public void robotPeriodic() {
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();

    System.out.println("Starting Autonomous Mode...");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
    while (m_timer.get() < 3){
      Drive.simpleTankDrv(-0.55);
    }
    acc.accStabilize();
    
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    System.out.println("Starting Teleoperated Mode...");
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    
    double spd = chassisGamepad.getFwd();
    double rot = chassisGamepad.getSteer();
    double drvthr = chassisGamepad.getRta();

    double deg = xGamepad2.getRightThumbY();
    double thr = xGamepad2.getLta();

    PAShuffle.inTeleopPeriod();

    Drive.arcadeDrv(-spd, -rot, drvthr);
    mainGripper.engageGripper(xGamepad);
    mainArm.move(deg,thr);
  }

    /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}
      
    /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}