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

import org.usfirst.frc2811.RecycleRush.Robot;
import org.usfirst.frc2811.RecycleRush.RobotMap;
import org.usfirst.frc2811.RecycleRush.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;


/**
 *  This contains the common functions for the two Lifer systems. 
 *  /For individual declarations, extend this class
 *  //NOTE
 */
public class TalonSRXPIDBase extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    protected CANTalon motor;
        
    protected double setpoint;
    
    //Will be used internally, but can be overridden in the inherited class
    //These are not static since they will change once when the function is homed after initialization
    protected double ENCODER_TICKS_FWD;
    protected double ENCODER_TICKS_REV;
    protected double ENCODER_TICKS_INDEX;

    //For the mapping functions, these will be used to convert to inches
    protected double INCHES_FWD;
    protected double INCHES_REV;
    protected double INCHES_INDEX;

    //TODO: Decide units to be used for this
    protected double VIRTUAL_STOP_FWD;
    protected double VIRTUAL_STOP_REV;
    
    //Constants for the Set function to indicate go up or down one tote
    public final static double GO_ONE_TOTE_UP=100;
    public final static double GO_ONE_TOTE_DOWN=-1;

    protected boolean isHomed = false;
    
    public void setRange(double fwd,double rev){
    	ENCODER_TICKS_FWD=fwd;
    	ENCODER_TICKS_REV=rev;
    }
    public void setVirtualStops(double fwd,double rev,double index){
    	VIRTUAL_STOP_FWD=fwd;
    	VIRTUAL_STOP_REV=rev;
    	}
    
    protected void useMotor(CANTalon newmotor){
    	motor=newmotor;
    }
    public void Home(){
    	Down(); // Additional check for switch
    	if (motor.isRevLimitSwitchClosed()){
    		isHomed = true ;
    	}
    }
    
    public boolean isHomed(){
    	
    	return isHomed; //TODO: Make the return function meaningful
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	// Override this with your own code in the extended class

    	//To avoid random compiler issues, just write out to a dummy talon.
    	//Since address 0 is the PDP, this should not cause issues
    	motor=new CANTalon(0);
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
        
    public double Speed(){
    	double speed = motor.getSpeed(); // gives speed  in the sensor's native ticks per 100ms
      	return speed;
    }

    public void Down(){
    	if(motor.isFwdLimitSwitchClosed() ){
    		motor.ClearIaccum();
    	}
    	if(motor.isRevLimitSwitchClosed() ){
    		stop();
    	} else {
    		setpoint = motor.getPosition()-55;//TODO tune this
    		motor.set(setpoint);
    	}
    }
    
    public void Up(){
    	//prevent I hangups when coming off of a switch
    	if(motor.isRevLimitSwitchClosed() ){
        	motor.ClearIaccum();
    	}
    	
    	//prevent I buildup when hitting a switch
    	if(motor.isFwdLimitSwitchClosed() ){
    		stop();
    	} else {
    	setpoint = motor.getPosition()+55;//TODO tune this
    	motor.set(setpoint);
    	}
    }
    public void stop(){
    	setpoint=motor.getPosition();
    	motor.ClearIaccum();
    }    
    
    public double get(){
    	//return current bident height in inches
    	double output;
    	setpoint=motor.getPosition();
    	//setpoint = Map(output,ENCODER_TICKS_FWD,ENCODER_TICKS_REV,INCHES_FWD,INCHES_REV);
    	return setpoint;
    }
        
    public double set(double inches){
    	//needs to set the target for the pid controller on the srx
    	//expects inches
    	setpoint=inches;
    	//setpoint= Map(setpoint,INCHES_FWD,INCHES_REV,ENCODER_TICKS_FWD,ENCODER_TICKS_REV); //TODO do a motor write
    	motor.set(setpoint);
    	return setpoint;
    } 
    public boolean onTarget(){
    	//find out where we are
    	//find out where we want to go
    	//find the absolute value of the difference
    	// if it is less than 50, return true 
    	// of it is greater than 50, return false
	    double position =motor.getPosition();
	    double difference = Math.abs(setpoint - position);
	
	    //FIXME Don't need print spam unless debugging
	    System.out.println("S: "+setpoint+" P: "+position+ "D:"+difference +" S?" +isIndexSwitchPressed());
		if (difference<=50){
			return true;
			}
			else {
				return false;
			}
    }

    public int getTotes(){
    	//return current bident height in totes
    	//TODO return bident height in totes
    	return 1;
    }
    
    public void setTotes(double toteheight){
    	//Set the pid to a specific height in totes
    	//TODO set tote height
    }

    public boolean isIndexSwitchPressed(){
    	//Switch is normally high (1), and low(0) when closed
    	return motor.getPinStateQuadIdx()==1?false:true;
    }


protected double Map( double input, double maximum, double minimum, double outputMax, double outputMin){
	double output = (input/(maximum-minimum)-minimum/(maximum-minimum))*(outputMax-outputMin)+outputMin;
	return output; 
	//NO ONE EXPECTS THE SPANISH INQUISITION
	}
}
           
    	
    
	
	
		
	
    
    


