package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import frc.robot.Constants;

public class PAShuffle {
    private static XboxGamepad xGamepad;
    
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

      Constants.gamepadLayout.withPosition(0, 0);
      Constants.gamepadLayout.add("Left X Axis", Constants.leftXAxisWidget);
      Constants.gamepadLayout.add("Left Y Axis", Constants.leftYAxisWidget);
      Constants.gamepadLayout.add("Right Trigger Axis", Constants.rightTAxisWidget);
      Constants.gamepadLayout.add("Right Y Axis", Constants.rightYAxisWidget);
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

        Constants.leftXAxisWidget.getEntry().setDouble(-spd);
        Constants.leftYAxisWidget.getEntry().setDouble(-rot);
        Constants.rightTAxisWidget.getEntry().setDouble(drvthr);
        Constants.rightYAxisWidget.getEntry().setDouble(deg);
    }
}
