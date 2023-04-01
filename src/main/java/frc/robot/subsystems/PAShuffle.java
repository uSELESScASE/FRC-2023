package frc.robot.subsystems;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class PAShuffle {
    private static ChassisGamepad chassisGamepad;
    private static FlightStick armGamepad;

    public static void onStart() {
      chassisGamepad = ChassisGamepad.getInstance(Constants.CHASSIS_XBOX_PORT);
      armGamepad = FlightStick.getInstance();

      Shuffleboard.getTab("uSELESScASE General");
      Shuffleboard.selectTab("uSELESScASE Shuffleboard");

      Constants.LEFT_X_AXIS_WIDGET.getEntry();
      Constants.LEFT_Y_AXIS_WIDGET.getEntry();
      Constants.CHASSIS_RIGHT_TRIGGER_AXIS_WIDGET.getEntry();
      Constants.ARM_RIGHT_TRIGGER_AXIS_WIDGET.getEntry();
      Constants.RIGHT_Y_AXIS_WIDGET.getEntry();

      Constants.PNEOMATIC_SETOFF_WIDGET.getEntry();
      Constants.PNEOMATIC_SETFORWARD_WIDGET.getEntry();
      Constants.PNEOMATIC_SETREVERSE_WIDGET.getEntry();

      Constants.DRIVE_STATION.getEntry();
      Constants.BATTERY_STATUS.getEntry();
      
      Constants.VISION_THREAD.setDaemon(true);
      Constants.VISION_THREAD.start();
    }

    public static void getRobotPeriodic() {
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
      SmartDashboard.putData(Constants.ROBOT_DRIVE);
      Constants.BATTERY_STATUS.getEntry().setDouble(RobotController.getBatteryVoltage());
    }
    
    public static void inTeleopPeriod() {
        double spd = chassisGamepad.getForwardDrive();
        double rot = chassisGamepad.getTurnDrive();
        double chsrta = chassisGamepad.getRightTriggerAxis();
      
        double deg = armGamepad.getRightThumbY();
        double armrta = armGamepad.secondStickThrottleAxis();

        Constants.LEFT_X_AXIS_WIDGET.getEntry().setDouble(-spd);
        Constants.LEFT_Y_AXIS_WIDGET.getEntry().setDouble(-rot);
        Constants.CHASSIS_RIGHT_TRIGGER_AXIS_WIDGET.getEntry().setDouble(chsrta);
        Constants.ARM_RIGHT_TRIGGER_AXIS_WIDGET.getEntry().setDouble(armrta);
        Constants.RIGHT_Y_AXIS_WIDGET.getEntry().setDouble(deg);
    }
}
