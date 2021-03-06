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

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Sendable;
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
    Command bidentMonitor;
    Command bidentTestGroup;


    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static ToteIntake toteIntake;
    
    public static Bident bident;
    public static ToteElevator toteElevator;
    public static Map map;
    public static Lights lights;
    public static UnderGlow underGlow;
    public static Logger logger;
    public static BidentHome bidentHoming;
    public static ToteElevatorHoming toteElevatorHoming;

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

        //SmartDashboard.putNumber("Yaw", Robot.chassis.adjustedYaw());
        //SmartDashboard.putNumber("GyroYaw", RobotMap.chassisGyro.updateTable(); );

        logger = new Logger();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI("default");
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
        joystickDrive = new ManualDrive();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        
        bidentTestGroup=new BidentTestGroup();
    }


    public void autonomousInit() {
    	//Set up our Monitoring systems for the Bident
		bident.stop(true);
 		bidentMonitor=new BidentMonitor();
 		bidentMonitor.start();
 		bident.enable();
 		
 		//TODO: Test out this autonomous function
 		//bidentTestGroup.start();
 		
 		//Set up montitoring systems for the toteElevator
 		//TODO: Set up toteElevator
 		
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        Camera.init();
    	//Simple command for testing the homing state
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        // TODO: Add camera code to Auto mode
        //Robot.logger.setChannel("CAMERA",false); //disable  camera loggers
        Robot.logger.channel("CAMERA","Getting Values");		//getX and getY return the doubles x (COG_X) and y (COG_Y)
        Robot.logger.channel("CAMERA","X = " + Camera.getX()); //from the Camera subsystem.
        Robot.logger.channel("CAMERA","Y = " + Camera.getY()); //RoboRealm draws a bounding box around yellow objects, 
        Robot.logger.channel("CAMERA","Got Values");           //and records the coordinates of the center of gravity of said box 



    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.

    	if (autonomousCommand != null) autonomousCommand.cancel();
    	joystickDrive.start();

    	//Set up our Monitoring systems for the Bident
		bident.stop(true);
 		bidentMonitor=new BidentMonitor();
 		bidentMonitor.start();
 		bident.enable();
 		
 		toteElevator.enable();
 		
 		//Set up montitoring systems for the toteElevator
 		//TODO: Set up toteElevator

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        SmartDashboard.putData("Robot Front",RobotMap.chassisGyro );
        SmartDashboard.putData("PDP", RobotMap.powerPanel);
        SmartDashboard.putData("Accelerometer",RobotMap.onboardAccelerometer);
        SmartDashboard.putData("Compressor", RobotMap.compressor);
        SmartDashboard.putString("whoami", Robot.oi.getDriver());
        //System.out.println(Robot.toteIntake.rollerState);
    }

    public void testInit(){
    	oi = new OI("testing");
    	
    	
    /*	System.out.println("Bident::Ticks per inch "+bident.ticksPerInch());
    	System.out.println("Bident::Current height "+bident.getHeight());
    	System.out.println("Bident::Current Tote Position "+bident.getTotePosition());
        System.out.println("Bident:: Bottom switch state" + Robot.bident.isBottomSwitchPressed());
        System.out.println("Bident:: Top switch state" + Robot.bident.isTopSwitchPressed());

    	System.out.println("toteElevator::Ticks per inch "+toteElevator.ticksPerInch());
    	System.out.println("toteElevator::Current height "+toteElevator.getHeight());
        System.out.println("toteElevator:: Bottom switch state" + Robot.toteElevator.isBottomSwitchPressed());
        System.out.println("toteElevator:: Top switch state" + Robot.toteElevator.isTopSwitchPressed());//*/
        System.out.println("Chassis:: Raw Encoder Ticks FrontRight" + Robot.chassis.frontRightEncoder.get());
        System.out.println("Chassis:: Raw Encoder Ticks BackLeft" + Robot.chassis.backLeftEncoder.get());
        System.out.println("");
        
    	
    }
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        //LiveWindow.run();
        //System.out.println("Bident Switch pressed?"+bident.isReverseSwitchPressed() +" "+bident.getRawEncoder());
        //bident.printStatus();

        LiveWindow.run();
        //underGlow.setColor();
        //System.out.println(bident.getDistanceBumper());
        //System.out.println("range finder");
        
        
        //logger.info("whatever");
        //logger.debug("anything");
        //logger.warning("something");
        //logger.status("a thing");
        
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

        //Ensure that if we stop the robot, the lifters stop what they're doing
    	//bident.stop();
    	//toteElevator.stop();
    	

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        
        //print lifter status
        toteElevator.disable();
        logger.setChannel("TALON", false);
        toteElevator.printStatus();
        logger.setChannel("TALON", false);
        
        //Bident Status
        bident.disable();
        logger.setChannel("TALON", false);
        bident.printStatus();        
        logger.setChannel("TALON", false);

    }
    
}
