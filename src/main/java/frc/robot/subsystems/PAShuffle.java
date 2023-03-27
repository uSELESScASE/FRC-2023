package frc.robot.subsystems;

import java.util.Map;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.Robot;

public class PAShuffle {
    private static Gamepad xGamepad;
    private static Thread m_visionThread;
    
    public static void onStart() {
        xGamepad = Gamepad.getInstance(Constants.JoystickPort);

        Shuffleboard.getTab("uSELESScASE General");
        Shuffleboard.selectTab("uSELESScASE General");

        Constants.leftXAxis.getEntry();
        Constants.leftYAxis.getEntry();
        Constants.rightTAxis.getEntry();
        Constants.rightYAxis.getEntry();

        SmartDashboard.setDefaultBoolean("Set Off", false);
        SmartDashboard.setDefaultBoolean("Set Forward", false);
        SmartDashboard.setDefaultBoolean("Set Reverse", false);

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
    }

    public static void getRobotStatus() {
        if (Robot.isReal()) {
            Shuffleboard.startRecording();   
        } else {
            Shuffleboard.stopRecording();
        }
    }
    
    public static void inTeleopPeriod() {
        double spd = xGamepad.getFwd();
        double rot = xGamepad.getSteer();
        double drvthr = xGamepad.getRta();
        double deg = xGamepad.getRightThumbY();

        SmartDashboard.putNumber("Left X Axis", spd);
        SmartDashboard.putNumber("Left Y Axis", rot);
        SmartDashboard.putNumber("Right Trigger Axis", drvthr);
    }
}
