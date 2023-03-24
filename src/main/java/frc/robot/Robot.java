// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gamepad;
import frc.robot.subsystems.Gripper;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private Thread m_visionThread;
  
  private Drivetrain Drive;
  private Gamepad xGamepad;

  private Gripper mainGripper;
  private final Timer m_timer = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    Drive = Drivetrain.getInstance();
    xGamepad = Gamepad.getInstance();
    mainGripper = Gripper.getInstance();

    m_visionThread =
        new Thread(
            () -> {
              int width = 400;
              int height = 240;

              // Get the UsbCamera from CameraServer
              UsbCamera camera = CameraServer.startAutomaticCapture();
              // Set the resolution
              camera.setResolution(width, height);

              // Get a CvSink. This will capture Mats from the camera
              CvSink cvSink = CameraServer.getVideo();
              // Setup a CvSource. This will send images back to the Dashboard
              CvSource outputStream = CameraServer.putVideo("Robot Kamerasi", width, height);

              // Mats are very memory expensive. Lets reuse this Mat.
              Mat mat = new Mat();

              // This cannot be 'true'. The program will never exit if it is. This
              // lets the robot stop this thread when restarting robot code or
              // deploying.
              while (!Thread.interrupted()) {
                // Tell the CvSink to grab a frame from the camera and put it
                // in the source mat.  If there is an error notify the output.
                if (cvSink.grabFrame(mat) == 0) {
                  // Send the output the error.
                  outputStream.notifyError(cvSink.getError());
                  // skip the rest of the current iteration
                  continue;
                }
                // Give the output stream a new image to display
                outputStream.putFrame(mat);
              }
            });

    m_visionThread.setDaemon(true);
    m_visionThread.start();

    SmartDashboard.setDefaultBoolean("Set Off", false);
    SmartDashboard.setDefaultBoolean("Set Forward", false);
    SmartDashboard.setDefaultBoolean("Set Reverse", false);
  }

  @Override
  public void robotPeriodic() {
  
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


    xGamepad.getSensRotPressed();

    double spd = xGamepad.getFwd() * xGamepad.getRta();
    double rot = xGamepad.getSteer();

    Drive.arcadeDrv(-spd, -rot);

    mainGripper.engageGripper(xGamepad);
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