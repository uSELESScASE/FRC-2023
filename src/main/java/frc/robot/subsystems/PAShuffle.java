package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class PAShuffle {
    private static Gamepad xGamepad;
    
    public static void onStart() {
        xGamepad = Gamepad.getInstance();

        Shuffleboard.getTab("uSELESScASE Shuffleboard");
        Shuffleboard.startRecording();

        SmartDashboard.setDefaultNumber("Get R-Y Axis", 0.0);
        SmartDashboard.setDefaultNumber("Get R-X Axis", 0.0);
        SmartDashboard.setDefaultNumber("Get RTA", 0.0);
        SmartDashboard.setDefaultNumber("Get Arm Angle", 0.0);
        SmartDashboard.setDefaultBoolean("Set Off", false);
        SmartDashboard.setDefaultBoolean("Set Forward", false);
        SmartDashboard.setDefaultBoolean("Set Reverse", false);   
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
        double deg = xGamepad.getSensRotPressed();

        SmartDashboard.putNumber("Get R-Y Axis", spd);
        SmartDashboard.putNumber("Get R-X Axis", rot);
        SmartDashboard.putNumber("Get Arm Angle", deg);
        SmartDashboard.putNumber("Get RTA", Constants.throttleMult);
    }
}
