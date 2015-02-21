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
 *  Wraps the CAN Talon classes with helper functions and conversions to common units. 
 *  /For individual declarations, extend this class and add additional sensors and sensor reading functions
 *  //NOTE
 */
public class TalonSRXPIDBase extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    protected CANTalon motor;
    protected void useMotor(CANTalon newMotor){
    	motor=newMotor;
    }
        
	double TOTEHEIGHT = 12;

    protected double setpoint;
    
    //Will be used internally, but can be overridden in the inherited class
    //These are not static since they will change once when the function is homed after initialization
    
    protected static double ENCODER_TICKS_FWD=100;
    protected static double ENCODER_TICKS_REV=-100;
    protected static double ENCODER_TICKS_INDEX;
    protected static double ENCODER_TICKS_HEIGHT=ENCODER_TICKS_FWD-ENCODER_TICKS_REV; 

    //For the mapping functions, these will be used to convert to inches
    //Using 0/100 is consistent for %, so let's use that as a default
    protected double INCHES_FWD=100;
    protected double INCHES_REV=0;
    protected double INCHES_INDEX=INCHES_REV;
    
    //This should be in inches
    protected double VIRTUAL_STOP_FWD=INCHES_FWD;
    protected double VIRTUAL_STOP_REV=INCHES_REV;
    
    //Constants for the Set function to indicate go up or down one tote
    public final static double GO_ONE_TOTE_UP=100;
    public final static double GO_ONE_TOTE_DOWN=-1;
    protected static double totePosition=0;

    protected boolean isHomed = false;
    
    /**
     * Set the height of the system in inches
     * This is used for internal mapping to provide accurate results
     * @param fwd
     * @param rev
     */
    public void setRangeInInches(double fwd,double rev){
    	INCHES_FWD=fwd;
    	INCHES_REV=rev;
    }
    
    /**
     * Set the height of the system in encoder ticks. 
     * This should be consistent barring hardware changes
     * @param ticks
     */
    public void setHeightInTicks(double ticks){
    	ENCODER_TICKS_HEIGHT=ticks;
    	Robot.logger.channel("TALON","Talon height set to "+ticks+" ticks");
    }
    
    /**
     * Save some math, and provide 2 ticks for the end stop of the ticks
     * @param ticksFwd Number of ticks for the FWD direction of the robot
     * @param ticksRev Number of ticks for the REV direction of the robot
     */
    public void setHeightInTicks(double ticksFwd,double ticksRev){
    	setHeightInTicks(ticksFwd-ticksRev);

    }
       
    /**
     * Set up virtual stops for when the robot is done homing
     * Units should be in inches
     * @param fwd (Units: Inches)
     * @param rev (Units: Inches)
     */
    public void setVirtualStops(double fwd,double rev){
    	VIRTUAL_STOP_FWD=fwd;
    	VIRTUAL_STOP_REV=rev;
    }
    /**
     * Write virtual stops to motor
     * Requires VIRTUAL_STOP_FWD and VIRTUAL_STOP_REV to be set
     */
    private void writeVirtualStops(){
    	double ticksFWD= map(VIRTUAL_STOP_FWD,INCHES_FWD,INCHES_REV,ENCODER_TICKS_FWD,ENCODER_TICKS_REV);
    	double ticksREV= map(VIRTUAL_STOP_REV,INCHES_FWD,INCHES_REV,ENCODER_TICKS_FWD,ENCODER_TICKS_REV);
    	
    	motor.enableForwardSoftLimit(true);
    	motor.enableReverseSoftLimit(true);
    	motor.setForwardSoftLimit((int) ticksFWD);
    	motor.setReverseSoftLimit((int) ticksREV);
    	Robot.logger.channel("TALON","Virtual Limits set to "+ENCODER_TICKS_FWD+"in ("+ENCODER_TICKS_FWD+" ticks) " +ENCODER_TICKS_FWD+"in ("+"ENCODER_TICKS_REV"+"ticks)");
	}
    
    /**
     * 
     */
    public void home(){
    	enable();
    	//down(); // Additional check for switch
    	motor.set(Math.signum(ENCODER_TICKS_REV)*ENCODER_TICKS_HEIGHT);
    	if (motor.isRevLimitSwitchClosed()){
    		isHomed = true ;
    		totePosition=0;
    	    ENCODER_TICKS_REV=motor.getEncPosition();
    	    ENCODER_TICKS_FWD=ENCODER_TICKS_REV+ENCODER_TICKS_HEIGHT;
    	    //writeVirtualStops(); //FIXME enable virtual stops
        	Robot.logger.channel("TALON","Homing sequence complete; System homed");
    	}
    }
    
    public void  printStatus(){
    	Robot.logger.channel("TALON","Forward Limits: "+INCHES_FWD+ "\t (ticks: "+ENCODER_TICKS_FWD+")");
    	Robot.logger.channel("TALON","Rev Limits    : "+INCHES_REV+ "\t (ticks: "+ENCODER_TICKS_REV+")");
    	Robot.logger.channel("TALON","Current State(IN) : Target:"+onTarget()+"\tH:"+get());
    	Robot.logger.channel("TALON","Current State(ticks) : Target:"+onTarget()+ "\tCurrent"+ getRawEncoder() +"\tTarget:"+setpoint);
    	Robot.logger.channel("TALON","Homing Status : " +isHomed+" Switch:"+isReverseSwitchPressed(false));
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
        
    public double speed(){
    	double speed = motor.getSpeed(); // gives speed  in the sensor's native ticks per 100ms
      	return speed;
    }

    public void down(){
    	if(motor.isFwdLimitSwitchClosed() ){
    		//motor.ClearIaccum();
    	}
    	if(motor.isRevLimitSwitchClosed() ){
    		stop();
    	} else {
    		setpoint = motor.getPosition()-50;//TODO tune this
    		motor.set(setpoint);
    	}

    	//Correct tote position when moving manually
    	totePosition=Math.floorDiv((int)get(),(int) TOTEHEIGHT);

    }
    
    public void up(){
    	//prevent I hangups when coming off of a switch
    	if(motor.isRevLimitSwitchClosed() ){
        	//motor.ClearIaccum();
    	}
    	
    	//prevent I buildup when hitting a switch
    	if(motor.isFwdLimitSwitchClosed() ){
    		stop();
    	} else {
    	setpoint = motor.getPosition()+25;//TODO tune this
    	motor.set(setpoint);
    	}
    	
    	//Correct tote position when moving manually
    	totePosition=Math.floorDiv((int)get(),(int) TOTEHEIGHT);
    	
    }
    public void stop(){
    	setpoint=motor.getEncPosition();
    	motor.set(setpoint);
    	motor.ClearIaccum();
    	Robot.logger.channel("TALON","Talon Stopped");
    }    
    
    public double get(){
    	//return current bident height in inches
    	double input;
    	double output;
    	input=motor.getPosition();
    	output = map(input,ENCODER_TICKS_FWD,ENCODER_TICKS_REV,INCHES_FWD,INCHES_REV);
    	return output;
    }
        
    public double set(double inches){
    	//todo: Should we use this?
    	//totePosition=Math.floorDiv((int)inches,(int) TOTEHEIGHT);
    	
    	//Convert to ticks and write to the motor
    	setpoint= map(inches,INCHES_FWD,INCHES_REV,ENCODER_TICKS_FWD,ENCODER_TICKS_REV); //TODO do a motor write
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
	    //System.out.println("S: "+setpoint+" P: "+position+ "D:"+difference +" S?" +isIndexSwitchPressed());
		if (difference<=50){
			return true;
			}
			else {
				return false;
			}
    }

    public double getTotes(){
    	return totePosition;    	
    }
    
	/**
	 * 
	 * @param toteheight
	 */
    public void setTotes(double toteheight){
    	//Set the pid to a specific height in totes
    	//TODO set tote height
		double GAP = 6;
		totePosition=toteheight;
        set( (getTotes() )*TOTEHEIGHT-GAP);

    }

    public void setOneToteUp(){
		double GAP = 6;
		double GRAB = -6;
    	//Set the pid to a specific height in totes
    	//TODO set tote height
		totePosition=(getTotes()+1);
;        set( totePosition*TOTEHEIGHT+GAP);

    }
    public void setOneToteDown(){
    	double GAP = 6;
    	double GRAB = -6;
    	totePosition=(getTotes()-1);
    	set( totePosition*TOTEHEIGHT+GAP);
    }
    
    
    public boolean isIndexSwitchPressed(){
    	//Switch is normally high (1), and low(0) when closed
    	return motor.getPinStateQuadIdx()==1?false:true;
    }
    
    /**
     * Return Switch state, with ability to disable soft limits
     * @param enableSoftLimits
     * @return
     */
    public boolean isForwardSwitchPressed(boolean enableSoftLimits){
    	//Switch is normally high (1), and low(0) when closed
    	if(enableSoftLimits==true){
    		return isForwardSwitchPressed();
    	}
    	else return motor.isFwdLimitSwitchClosed();
    }

    /**
     * Returns switch state
     * @return true if switch is pressed, or if virtual limits exceeded
     */
    public boolean isForwardSwitchPressed(){
    	if(get()>=VIRTUAL_STOP_FWD){
    		return true;
    	}
    	//Switch is normally high (1), and low(0) when closed
    	return motor.isFwdLimitSwitchClosed();//==1?false:true;
    }

    /**
     * Return Switch state, with ability to disable soft limits
     * @param enableSoftLimits
     * @return
     */
    public boolean isReverseSwitchPressed(boolean enableSoftLimits){
    	//Switch is normally high (1), and low(0) when closed
    	if(enableSoftLimits==true){
    		return isReverseSwitchPressed();
    	}
    	else return motor.isRevLimitSwitchClosed();
    }
    
    /**
     * Returns switch state
     * @return true if switch is pressed, or if virtual limits exceeded
     */
    public boolean isReverseSwitchPressed(){
    	if(get()<=VIRTUAL_STOP_REV){
    		return true;
    	}
    	return isReverseSwitchPressed();
    }

    public double getRawEncoder(){
    	return motor.getEncPosition() ;
    	
    }
    
    public void disable(){
    	//FIXME: Disable/enable doesn't work
    	motor.disable();
    	motor.disableControl();
    	Robot.logger.channel("TALON","Talon control disabled");
    }
    
    public void enable(){
    	//FIXME: Disable/enable doesn't work
    	motor.enableControl();
    	Robot.logger.channel("TALON","Talon control enabled");

    }

protected double map( double input, double maximum, double minimum, double outputMax, double outputMin){
	double output = (input/(maximum-minimum)-minimum/(maximum-minimum))*(outputMax-outputMin)+outputMin;
	if (output==Double.NaN){
		//System.out.println("Map::Error::"+input+"  "+minimum+"  "+maximum+"  "+outputMin+"  "+outputMax);
		output=minimum;
		}
	return output; 
	//NO ONE EXPECTS THE SPANISH INQUISITION
	}
}
           
    	
    
	
	
		
	
    
    


