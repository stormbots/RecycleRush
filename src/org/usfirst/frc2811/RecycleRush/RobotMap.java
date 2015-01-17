// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2811.RecycleRush;
    
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController frontLeftDrive;
    public static SpeedController frontRightDrive;
    public static SpeedController backLeftDrive;
    public static SpeedController backRightDrive;
    public static RobotDrive robotDrive41;
    public static Gyro rotationGyro;
    public static SpeedController toteRollerL;
    public static SpeedController toteRollerR;
    public static Solenoid toteSolenoidL;
    public static Solenoid toteSolenoidR;
    public static CANTalon toteLifterMotor;
    public static DigitalInput toteLimitSwitch;
    public static AnalogInput toteSonar;
    public static CANTalon binLifterMotor;
    public static Solenoid binSolenoid;
    public static AnalogInput binSonarTop;
    public static AnalogInput binSonarBottom;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontLeftDrive = new Talon(1);
        LiveWindow.addActuator("Chassis", "FrontLeftDrive", (Talon) frontLeftDrive);
        
        frontRightDrive = new Talon(2);
        LiveWindow.addActuator("Chassis", "FrontRightDrive", (Talon) frontRightDrive);
        
        backLeftDrive = new Talon(3);
        LiveWindow.addActuator("Chassis", "BackLeftDrive", (Talon) backLeftDrive);
        
        backRightDrive = new Talon(4);
        LiveWindow.addActuator("Chassis", "BackRightDrive", (Talon) backRightDrive);
        
        robotDrive41 = new RobotDrive(frontLeftDrive, backLeftDrive,
              frontRightDrive, backRightDrive);
        
        robotDrive41.setSafetyEnabled(true);
        robotDrive41.setExpiration(0.1);
        robotDrive41.setSensitivity(0.5);
        robotDrive41.setMaxOutput(1.0);
        robotDrive41.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        robotDrive41.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);

        rotationGyro = new Gyro(0);
        LiveWindow.addSensor("Chassis", "RotationGyro", rotationGyro);
        rotationGyro.setSensitivity(0.007);
        toteRollerL = new Talon(5);
        LiveWindow.addActuator("ToteIntake", "ToteRollerL", (Talon) toteRollerL);
        
        toteRollerR = new Talon(6);
        LiveWindow.addActuator("ToteIntake", "ToteRollerR", (Talon) toteRollerR);
        
        toteSolenoidL = new Solenoid(0, 0);
        LiveWindow.addActuator("ToteIntake", "ToteSolenoidL", toteSolenoidL);
        
        toteSolenoidR = new Solenoid(0, 1);
        LiveWindow.addActuator("ToteIntake", "ToteSolenoidR", toteSolenoidR);
        
        toteLifterMotor = new CANTalon(7);
        //LiveWindow.addActuator("ToteElevator", "ToteLifterMotor", (TalonSRX) toteLifterMotor);
        
        toteLimitSwitch = new DigitalInput(2);
        LiveWindow.addSensor("ToteElevator", "ToteSwitch", toteLimitSwitch);
        
        toteSonar = new AnalogInput(1);
        LiveWindow.addSensor("ToteElevator", "ToteSonar", toteSonar);
        
        binLifterMotor = new CANTalon(8);
        //LiveWindow.addActuator("Bident", "BinLifterMotor", (TalonSRX) binLifterMotor);
        
        binSolenoid = new Solenoid(0, 2);
        LiveWindow.addActuator("Bident", "BinSolenoid", binSolenoid);
        
        binSonarTop = new AnalogInput(2);
        LiveWindow.addSensor("Bident", "BinSonarTop", binSonarTop);
        
        binSonarBottom = new AnalogInput(3);
        LiveWindow.addSensor("Bident", "BinSonarBottom", binSonarBottom);
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
