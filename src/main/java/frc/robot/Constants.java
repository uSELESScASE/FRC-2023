package frc.robot;

import java.util.Map;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

public class Constants {
    public static double ACCELEROMETER_THROTTLE_MULTIPLY = 1.375;

    public static final int GAMEPAD_PORT = 0;
    public static final int RIGHT_TOP_PORT = 0;
    public static final int RIGHT_BOTTOM_PORT = 1;
    public static final int LEFT_BOTTOM_PORT = 3;
    public static final int LEFT_TOP_PORT = 2; 
    public static final int ARM_PORT = 9;

    public static MotorController RIGHT_TOP_SPARK = new PWMSparkMax(Constants.RIGHT_TOP_PORT);
    public static MotorController RIGHT_BOTTOM_SPARK= new PWMSparkMax(Constants.RIGHT_BOTTOM_PORT);
    public static MotorController LEFT_TOP_SPARK = new PWMSparkMax(Constants.LEFT_TOP_PORT);
    public static MotorController LEFT_BOTTOM_SPARK = new PWMSparkMax(Constants.LEFT_BOTTOM_PORT);

    public static MotorControllerGroup LEFT_DRIVE_GROUP = new MotorControllerGroup(LEFT_TOP_SPARK, LEFT_BOTTOM_SPARK);
    public static MotorControllerGroup RIGHT_DRIVE_GROUP = new MotorControllerGroup(RIGHT_TOP_SPARK, RIGHT_BOTTOM_SPARK);

    public static DifferentialDrive ROBOT_DRIVE = new DifferentialDrive(LEFT_DRIVE_GROUP, RIGHT_DRIVE_GROUP);

    public static final int PH_CAN_ID = 1;
    public static final int PH_FORWARD_CHANNEL = 6;
    public static final int PH_REVERSE_CHANNEL = 7;

    public static DoubleSolenoid MAIN_SOLENOID = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.PH_FORWARD_CHANNEL, Constants.PH_REVERSE_CHANNEL);
    public static Compressor MAIN_COMPRESSOR = new Compressor(1, PneumaticsModuleType.REVPH);

    public static SimpleWidget LEFT_Y_AXIS_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Left Y Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min",-1 , "max",1));

    public static SimpleWidget LEFT_X_AXIS_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Left X Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min",-1 , "max",1));

    public static SimpleWidget CHASSIS_RIGHT_TRIGGER_AXIS_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Right Trigger Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min",0 , "max",1));

    public static SimpleWidget ARM_RIGHT_TRIGGER_AXIS_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add(".Right Trigger Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min",0 , "max",1));

    public static SimpleWidget RIGHT_Y_AXIS_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Right Y Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min",-1 , "max",1));

    public static SimpleWidget PNEOMATIC_SETOFF_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Set Off", false)
    .withWidget(BuiltInWidgets.kBooleanBox);

    public static SimpleWidget PNEOMATIC_SETFORWARD_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Set Forward", false)
    .withWidget(BuiltInWidgets.kBooleanBox);

    public static SimpleWidget PNEOMATIC_SETREVERSE_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Set Reverse", false)
    .withWidget(BuiltInWidgets.kBooleanBox);

    public static SimpleWidget PNEOMATIC_STATUS_WIDGET = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Pneumatic Status", "N/A")
    .withWidget(BuiltInWidgets.kTextView);

    public static Thread VISION_THREAD = new Thread(() -> {
        int width = 400;
        int height = 240;

        // Get the UsbCamera from CameraServer
        UsbCamera camera = CameraServer.startAutomaticCapture();
        // Set the resolution
        camera.setResolution(width,height);
        
        // Get a CvSink. This will capture Mats from the camera
        CvSink cvSink = CameraServer.getVideo();
        // Setup a CvSource. This will send images back to the Dashboard
        CvSource outputStream = CameraServer.putVideo("Robot Camera", 640, 480);

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

    /**
      public static SimpleWidget DRIVE_STATION = Shuffleboard.getTab("uSELESScASE Shuffleboard")
      .add("Driver Station", 0)
      .withWidget(BuiltInWidgets.kDifferentialDrive);
    **/

    public static SimpleWidget BATTERY_STATUS = Shuffleboard.getTab("uSELESScASE Shuffleboard")
    .add("Battery Status", 0)
    .withWidget(BuiltInWidgets.kVoltageView)
    .withSize(6, 3)
    .withProperties(Map.of("min", 0, "max", 13));
}