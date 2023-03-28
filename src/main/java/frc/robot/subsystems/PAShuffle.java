package frc.robot.subsystems;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import frc.robot.Constants;

public class PAShuffle {
    private static XboxGamepad xGamepad;
    private static Thread m_visionThread;
    
    public static void onStart() {
        xGamepad = XboxGamepad.getInstance(Constants.XboxPort);

        Shuffleboard.getTab("uSELESScASE General");
        Shuffleboard.selectTab("uSELESScASE General");

        Constants.leftXAxisWidget.getEntry();
        Constants.leftYAxisWidget.getEntry();
        Constants.rightTAxisWidget.getEntry();
        Constants.rightYAxisWidget.getEntry();

        Constants.pneomaticSetOffWidget.getEntry();
        Constants.pneomaticSetForwardWidget.getEntry();
        Constants.pneomaticSetReverseWidget.getEntry();

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
      switch (Constants.mainSolenoid.get()) {
        case kOff:
          Constants.pneomaticStatusWidget.getEntry().setString("kOff");

          Constants.pneomaticSetOffWidget.getEntry().setBoolean(true);
          Constants.pneomaticSetForwardWidget.getEntry().setBoolean(false);
          Constants.pneomaticSetReverseWidget.getEntry().setBoolean(false);
          break;
        case kForward:
          Constants.pneomaticStatusWidget.getEntry().setString("kForward");
          
          Constants.pneomaticSetOffWidget.getEntry().setBoolean(false);
          Constants.pneomaticSetForwardWidget.getEntry().setBoolean(true);
          Constants.pneomaticSetReverseWidget.getEntry().setBoolean(false);
          break;
        case kReverse:
          Constants.pneomaticStatusWidget.getEntry().setString("kReverse");

          Constants.pneomaticSetOffWidget.getEntry().setBoolean(false);
          Constants.pneomaticSetForwardWidget.getEntry().setBoolean(false);
          Constants.pneomaticSetReverseWidget.getEntry().setBoolean(true);
          break;
        default:
          Constants.pneomaticStatusWidget.getEntry().setString("N/A");

          Constants.pneomaticSetOffWidget.getEntry().setBoolean(false);
          Constants.pneomaticSetForwardWidget.getEntry().setBoolean(false);
          Constants.pneomaticSetReverseWidget.getEntry().setBoolean(false);
          break;
      }
    }
    
    public static void inTeleopPeriod() {
        double spd = xGamepad.getFwd();
        double rot = xGamepad.getSteer();
        double drvthr = xGamepad.getRta();
        double deg = xGamepad.getRightThumbY();

        Constants.leftXAxisWidget.getEntry().setDouble(spd);
        Constants.leftYAxisWidget.getEntry().setDouble(rot);
        Constants.rightTAxisWidget.getEntry().setDouble(drvthr);
        Constants.rightYAxisWidget.getEntry().setDouble(deg);
    }
}
