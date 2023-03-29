package frc.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;

public class Constants {
    public static final double leftSlow = 0.24;
    public static final double rightSlow = -0.24;
    public static final double rotateSpeed = 0.35;
    public static final double rotateSpeedSlow = 0.25;

    public static double throttleMult = 0;
    public static double armThrottleMult = 0;
    public static double accelerometerThrottleMult = 1.5;

    public static final int XboxPort = 0;
    public static final int Xbox_Port_2 = 1;
    public static final int JoystickPort = 1;
    public static final int R_T_Port = 2;
    public static final int R_B_Port = 3;
    public static final int L_T_Port = 0;
    public static final int L_B_Port = 1;
    public static final int gyroPort = 0;
    public static final int Arm_Port = 4;
    public static final int Switch_Chan = 1;

    public static MotorController m_R_Top = new PWMSparkMax(Constants.R_T_Port);
    public static MotorController m_R_Bot= new PWMSparkMax(Constants.R_B_Port);
    public static MotorController m_L_Top = new PWMSparkMax(Constants.L_T_Port);
    public static MotorController m_L_Bot = new PWMSparkMax(Constants.L_B_Port);

    public static MotorControllerGroup m_leftDrive = new MotorControllerGroup(m_L_Top, m_L_Bot);
    public static MotorControllerGroup m_rightDrive = new MotorControllerGroup(m_R_Top, m_R_Bot);

    public static DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
    
    public static int gyroCalib = 0;
    public static int gyroReset = 0;
    public static int MotorPost = 0;

    public static final int PH_CAN_ID = 1;
    public static final int forwardChannel = 6;
    public static final int reverseChannel = 7;

    public static DoubleSolenoid mainSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.forwardChannel, Constants.reverseChannel);
    public static Compressor mainCompressor = new Compressor(1, PneumaticsModuleType.REVPH);

    public static ShuffleboardLayout gamepadLayout = Shuffleboard.getTab("uSELESScASE General")
    .getLayout("Elevator", BuiltInLayouts.kList)
    .withSize(12, 5)
    .withProperties(Map.of("Label position", "MINIMAL"));

    public static SimpleWidget leftYAxisWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Left Y Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min",-1 , "max",1));

    public static SimpleWidget leftXAxisWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Left X Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min",-1 , "max",1));

    public static SimpleWidget rightTAxisWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Right Trigger Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min",0 , "max",1));

    public static SimpleWidget rightYAxisWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Right Y Axis", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min",-1 , "max",1));

    public static SimpleWidget pneomaticSetOffWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Set Off", false)
    .withWidget(BuiltInWidgets.kBooleanBox);
    public static SimpleWidget pneomaticSetForwardWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Set Forward", false)
    .withWidget(BuiltInWidgets.kBooleanBox);
    public static SimpleWidget pneomaticSetReverseWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Set Reverse", false)
    .withWidget(BuiltInWidgets.kBooleanBox);
    public static SimpleWidget pneomaticStatusWidget = Shuffleboard.getTab("uSELESScASE General")
    .add("Pneumatic Status", "N/A")
    .withWidget(BuiltInWidgets.kTextView);
}