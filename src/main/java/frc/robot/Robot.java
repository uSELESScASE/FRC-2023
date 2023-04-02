// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ChassisGamepad;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FlightStick;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.PAShuffle;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private ChassisGamepad chassisGamepad;
  private FlightStick armGamepad;
  private Drivetrain driveTrain;
  private Gripper mainGripper;
  private Arm mainArm;
  private Gyroscope acceleroMeter;
  double value;
  // private FlightStick flightStick;
  private final Timer m_generalTimer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = Drivetrain.getInstance();
    chassisGamepad = ChassisGamepad.getInstance(Constants.CHASSIS_XBOX_PORT);
    armGamepad = FlightStick.getInstance();
    mainGripper = Gripper.getInstance();
    mainArm = Arm.getInstance();
    acceleroMeter = Gyroscope.getInstance();
    // flightStick = FlightStick.getInstance();

    PAShuffle.onStart();
  }

  @Override
  public void robotPeriodic() {
    PAShuffle.getRobotPeriodic();
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {

    System.out.println("Starting Autonomous Mode...");

    m_generalTimer.reset();
    m_generalTimer.start();
    mainGripper.autonomousPnoForward();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    while (m_generalTimer.get() < 1.9 ){
      Drivetrain.simpleTankDrv(0.77);
    }
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
    
    double spd = chassisGamepad.getForwardDrive();
    double rot = chassisGamepad.getTurnDrive();
    double drvthr = chassisGamepad.getRightTriggerAxis();

    double deg = armGamepad.getRightThumbY();
    double thr = armGamepad.secondStickThrottleAxis();

    PAShuffle.inTeleopPeriod();

    driveTrain.arcadeDrv(-spd, -rot, drvthr);
    mainGripper.engageGripper(armGamepad);
    mainArm.move(deg,thr);
  }

    /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}
      
    /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}