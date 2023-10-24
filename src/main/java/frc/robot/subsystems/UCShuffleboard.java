package frc.robot.subsystems;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import frc.robot.Constants;

public class UCShuffleboard {

    public static void robotInit() {
        Shuffleboard.getTab("uSELESScASE Shuffleboard");
        Shuffleboard.selectTab("uSELESScASE Shuffleboard");

        // Create a layout for the subsystems and add it to the dashboard
        ShuffleboardLayout layout = Shuffleboard.getTab("uSELESScASE Shuffleboard").getLayout("Controller Status", BuiltInLayouts.kGrid);
        

        Constants.LEFT_X_AXIS_WIDGET.getEntry();
        Constants.LEFT_Y_AXIS_WIDGET.getEntry();

        Constants.BATTERY_STATUS.getEntry();
    }

    public static void robotPeriodic() {
        Constants.BATTERY_STATUS.getEntry().setDouble(RobotController.getBatteryVoltage());
    }
    
    public static void teleopPeriod() {
        
    }
}