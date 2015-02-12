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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2811.RecycleRush.commands.*;
import org.usfirst.frc2811.RecycleRush.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    Command joystickDrive;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static ToteIntake toteIntake;
    public static ToteElevator toteElevator;
    public static Bident bident;
    public static Map map;
    public static Lights lights;
    public static UnderGlow underGlow;

    //public static DriverStation lcd;
    //private Preferences powerPanelReadout;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        toteIntake = new ToteIntake();
        toteElevator = new ToteElevator();
        bident = new Bident();
        map = new Map();
        lights = new Lights();
        underGlow = new UnderGlow();
        bident.init();
        oi = new OI();
        
/*		powerPanelReadout = Preferences.getInstance();
        lcd = DriverStation.getInstance();
        powerPanelReadout = Preferences.getInstance();
        SmartDashboard.putNumber("Temperature", RobotMap.powerPanel.getTemperature());	
        SmartDashboard.putNumber("TotalCurrent", RobotMap.powerPanel.getTotalCurrent());
        SmartDashboard.putNumber("TotalPower", RobotMap.powerPanel.getTotalPower());
        SmartDashboard.putNumber("Voltage", RobotMap.powerPanel.getVoltage());
        SmartDashboard.putNumber("Current1", RobotMap.powerPanel.getCurrent(1));
        SmartDashboard.putNumber("Current2", RobotMap.powerPanel.getCurrent(2));
        SmartDashboard.putNumber("Current3", RobotMap.powerPanel.getCurrent(3));
        SmartDashboard.putNumber("Current4", RobotMap.powerPanel.getCurrent(4));
        
        System.out.println(RobotMap.powerPanel.getCurrent(1));
        */ 
        autonomousCommand = new AutonomousCommand();
        joystickDrive = new ManualDrive();
    }

    /**
      * This function is called when the disabled button is hit.
      * You can use it to reset subsystems before shutting down.
      **/
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        Camera.init();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        Camera.update();
        System.out.println("Getting Values");
        System.out.println("X = " + Camera.getX());
        System.out.println("Y = " + Camera.getY());
        System.out.println("Got Values");
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        joystickDrive.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        /*SmartDashboard.putNumber("Temperature", RobotMap.powerPanel.getTemperature());	
    	SmartDashboard.putNumber("TotalCurrent", RobotMap.powerPanel.getTotalCurrent());
    	SmartDashboard.putNumber("TotalPower", RobotMap.powerPanel.getTotalPower());
    	SmartDashboard.putNumber("Voltage", RobotMap.powerPanel.getVoltage());
    	SmartDashboard.putNumber("Current1", RobotMap.powerPanel.getCurrent(0));
    	SmartDashboard.putNumber("Current2", RobotMap.powerPanel.getCurrent(1));
    	SmartDashboard.putNumber("Current3", RobotMap.powerPanel.getCurrent(2));
    	SmartDashboard.putNumber("Current4", RobotMap.powerPanel.getCurrent(3));
    	
        RobotMap.test++;
        SmartDashboard.putNumber("test", RobotMap.test);*/
    	//System.out.println(RobotMap.powerPanel.getCurrent(1));
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    	//NetworkTable server = NetworkTable.getTable("SmartDashboard");
		//double x = server.getNumber("COG_X", -3);
		//double d=server.getNumber("danrules", 0);
		//server.putNumber("danrules", d+1);
		//System.out.println("Testing!"+d);
        Camera.update();
        System.out.println("X = " + Camera.getX());
        System.out.println("Y = " + Camera.getY());
		       
    }
    
}
