// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2811.RecycleRush.subsystems;

import org.usfirst.frc2811.RecycleRush.RobotMap;
import org.usfirst.frc2811.RecycleRush.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Bident extends TalonSRXPIDBase {
    
	private Solenoid binSolenoid;
    //TODO remove the ultrasonic sensors because they are declared in the code
    //FIXME public Ultrasonic binRangeTop = new Ultrasonic(0,1);
    //FIXME public Ultrasonic binRangeBottom = new Ultrasonic(2,3);
    public boolean open = true;
    public boolean close = !open;
    private Ultrasonic bidentSonarIntake = new Ultrasonic(0,1);
    private Ultrasonic bidentSonarBumper = new Ultrasonic(2,3);

    
    //*/Practice Robot
    protected double ENCODER_TICKS_HEIGHT=29990.0; //Testbench jig
    protected double INCHES_FWD=58;
    protected double INCHES_REV=7.75;
    protected double INCHES_INDEX=INCHES_REV;
    protected double VIRTUAL_STOP_FWD=INCHES_FWD-.5;
    protected double VIRTUAL_STOP_REV=INCHES_REV+.5;
    //*/
    
    
    private double setpoint;
    //TODO fix port declaration binRangerTop and Bottom, port are for one wires and need to be two
    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {

    	//Initialization stuff
    	useMotor( new CANTalon(5) );
    	binSolenoid=new Solenoid(1);
    	
    	
        INCHES_FWD=55;
        INCHES_REV=0;
        INCHES_INDEX=30; //index is on the lowest switch

        //Set up the PID function
    	init();
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void init (){
    	motor.changeControlMode(CANTalon.ControlMode.Position);
    	motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);//  changeFeedbackDevice(CANTalon.ControlMode.Position);
    	setpoint=motor.getPosition();
    	double p = .375;
    	double i = 0.01;
    	double d = 0;
    	double f = 0;
    	int izone = 0; 
    	double ramprate = 1;// who knows what this will do
    	int profile = 0; 
    	motor.setPID(p,i,d,f,izone,ramprate,profile);
    	bidentSonarIntake.setAutomaticMode(true);
     	bidentSonarBumper.setAutomaticMode(true);
     	bidentSonarBumper.setEnabled(true);
    	bidentSonarIntake.setEnabled(true);
    	//If crashing, then update to the newest wpilib...  :)
    	
    	
    	
    	//Declare constants for use in the main function
    	setRangeInInches(7.5,58);
    	setHeightInTicks(29990);//Testbench



     	/*/FIXME when the ultrasonic.setAutomaicMade(false) or commented out, the robot code doesn't delete, but when the automatic mode is true, the robot
     	 * code is deleted. Why is this??
     	 * Try setAutomaticMode then isEnabled...
     	 * Sandra Hughey
     	*/
    }
        
    public double getDistanceTop(){
    	double distance = 0;
    	//FIXME Does not return correctly. Returns void, kills robot
    	if(bidentSonarBumper.isRangeValid() && bidentSonarBumper.isEnabled()){
    		distance=   bidentSonarBumper.getRangeInches();
        } else if (bidentSonarBumper.isEnabled() == false) {
        	distance = -1;
        } else if (bidentSonarBumper.isRangeValid() == false) {
        	distance = -2;
        } else {
        	System.out.println("B0RK3D!!!1!");
        	distance = -3;
        }
    	return distance;
    }
    
    public double getDistanceBumper(){
    	double distance = 0;
    	//TODO calibrate range maybe...(limits)
        if(bidentSonarIntake.isRangeValid() && bidentSonarIntake.isEnabled()){
        	distance = bidentSonarIntake.getRangeInches();
        } else if (bidentSonarIntake.isEnabled() == false) {
        	//System.out.println("not enabled");
        	distance = -1;
        } else if (bidentSonarIntake.isRangeValid() == false) {
        	distance= -2;
        } else {
        	System.out.println("B0RK3D!!!1!");
        	distance = -3.;
        }    	
        return distance;
    }
    
    public void Open(){
    	binSolenoid.set(open);
    }
    
    public void Close(){
    	binSolenoid.set(close);
    }    
    
}

