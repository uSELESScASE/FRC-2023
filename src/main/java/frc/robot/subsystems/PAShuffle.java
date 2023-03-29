package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import frc.robot.Constants;

public class PAShuffle {
    private static XboxGamepad xGamepad;
    
    public static void onStart() {
      xGamepad = XboxGamepad.getInstance(Constants.CHASSIS_XBOX_PORT);

      Shuffleboard.getTab("uSELESScASE General");
      Shuffleboard.selectTab("uSELESScASE General");

      Constants.LEFT_X_AXIS_WIDGET.getEntry();
      Constants.LEFT_Y_AXIS_WIDGET.getEntry();
      Constants.RIGHT_TRIGGER_AXIS_WIDGET.getEntry();
      Constants.RIGHT_Y_AXIS_WIDGET.getEntry();

      Constants.PNEOMATIC_SETOFF_WIDGET.getEntry();
      Constants.PNEOMATIC_SETFORWARD_WIDGET.getEntry();
      Constants.PNEOMATIC_SETREVERSE_WIDGET.getEntry();
      
      Constants.VISION_THREAD.setDaemon(true);
      Constants.VISION_THREAD.start();
    }

    public static void getRobotStatus() {
      switch (Constants.MAIN_SOLENOID.get()) {
        case kOff:
          Constants.PNEOMATIC_STATUS_WIDGET.getEntry().setString("kOff");

          Constants.PNEOMATIC_SETOFF_WIDGET.getEntry().setBoolean(true);
          Constants.PNEOMATIC_SETFORWARD_WIDGET.getEntry().setBoolean(false);
          Constants.PNEOMATIC_SETREVERSE_WIDGET.getEntry().setBoolean(false);
          break;
        case kForward:
          Constants.PNEOMATIC_STATUS_WIDGET.getEntry().setString("kForward");
          
          Constants.PNEOMATIC_SETOFF_WIDGET.getEntry().setBoolean(false);
          Constants.PNEOMATIC_SETFORWARD_WIDGET.getEntry().setBoolean(true);
          Constants.PNEOMATIC_SETREVERSE_WIDGET.getEntry().setBoolean(false);
          break;
        case kReverse:
          Constants.PNEOMATIC_STATUS_WIDGET.getEntry().setString("kReverse");

          Constants.PNEOMATIC_SETOFF_WIDGET.getEntry().setBoolean(false);
          Constants.PNEOMATIC_SETFORWARD_WIDGET.getEntry().setBoolean(false);
          Constants.PNEOMATIC_SETREVERSE_WIDGET.getEntry().setBoolean(true);
          break;
        default:
          Constants.PNEOMATIC_STATUS_WIDGET.getEntry().setString("N/A");

          Constants.PNEOMATIC_SETOFF_WIDGET.getEntry().setBoolean(false);
          Constants.PNEOMATIC_SETFORWARD_WIDGET.getEntry().setBoolean(false);
          Constants.PNEOMATIC_SETREVERSE_WIDGET.getEntry().setBoolean(false);
          break;
      }
    }
    
    public static void inTeleopPeriod() {
        double spd = xGamepad.getFwd();
        double rot = xGamepad.getSteer();
        double drvthr = xGamepad.getRta();
        double deg = xGamepad.getRightThumbY();

        Constants.LEFT_X_AXIS_WIDGET.getEntry().setDouble(-spd);
        Constants.LEFT_Y_AXIS_WIDGET.getEntry().setDouble(-rot);
        Constants.RIGHT_TRIGGER_AXIS_WIDGET.getEntry().setDouble(drvthr);
        Constants.RIGHT_Y_AXIS_WIDGET.getEntry().setDouble(deg);
    }
}
