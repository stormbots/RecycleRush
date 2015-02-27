
package org.usfirst.frc2811.RecycleRush.subsystems;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TalonSRXPIDBase extends Subsystem {
	protected static CANTalon talonSRX;
	
	/**
	 * This is used in internal printlines and status messages
	 */
	protected String systemName="TalonSRX";
	
	/**
	 * The value (in inches) that the system will attempt to set itself to. 
	 */
	protected double setpoint;
		
	/**Indicate whether the setTickVelocity function will reverse the sign to invert the motor direction 
	 */
	private boolean reverseMotorDirection=false;
	
	/**
	 * This is the very bottom limit of the switch, set when the system is homed correctly
	 * This is used by the math functions to zero the encoder ticks
	 */
	private double tickValueOfBottomLimitSwitch=0;
	
	
	/*
	 * These are helper functions, used in calibrating the conversion from range to encoder ticks for the robot's current height 
	 * The default values should never be used, but are provided to avoid potential errors
	 */
	private double inchesBottom=0;
	private double inchesTop=10;
	private double ticksBottom=-100;
	private double ticksTop=100;
	
	private boolean softLimitsEnabled=false;
	private double softStopInchesBottom=-100;
	private double softStopInchesTop=100;

	private boolean homingStatus=false;

	/**
	 * This is used to enable or disable the {name}Monitor command
	 * use enable() or disable() to set it. 
	 */
	private boolean heightMonitoringEnabled=true;
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//Don't use this: It requires requires(), which screws up other stuff
        //setDefaultCommand(new BidentMonitor());
    	
    	//To ensure we don't cause runtime errors, we create a fake CanTALON, just in case.
    	//This will be overridden by the child class
    	talonSRX=new CANTalon(0);
    }
    
    /** SetRange: Used to calibrate the inches-to-range conversion for helpful utility functions
     * This is used to calibrate the TicksTo Inches and Inches 
     * @param inchesBottom 	Bottom limit of the robot's actuator (in inches)
     * @param inchesTop	   	Top limit of the robot's actuator (in inches)
     * @param ticksBottom	Bottom limit of the robot's actuator (in encoder ticks)
     * @param ticksTop		Bottom limit of the robot's actuator (in encoder ticks)
     */
    public void setRange(double InchesBottom, double InchesTop,double TicksBottom, double TicksTop){
    	inchesBottom=InchesBottom;
    	inchesTop=InchesTop;
    	ticksBottom=TicksBottom;
    	ticksTop=TicksTop;
    }
    public void setSoftLimits(double InchesBottom, double InchesTop){
    	softStopInchesBottom=InchesBottom;
    	softStopInchesTop=InchesTop;
    	softLimitsEnabled=true;
    }
    
    
    
    /**
     * Use this function to declare that the system is fully homed
     * @param homingStatus
     */
    public void setHomedState(boolean _homingStatus){
    	homingStatus = _homingStatus;
    	System.out.println("STATUS::"+systemName+":: Homing State now set to " +homingStatus);
    	tickValueOfBottomLimitSwitch=talonSRX.getPosition(); 	
    }
    
    /**Returns true if the system has been homed
     * @return
     */
    public boolean isHomed(){
    	return homingStatus;
    }
    
    
    
    
    /** Sets the velocity to match the current setpoint
     * Intended to be called repeatedly from a command
     */
    public void update(){
    	double difference=Math.abs(ticksToInches(talonSRX.getEncPosition())-setpoint);
    	double upSpeedSlow=2;
    	double upSpeedFast=5;
    	double downSpeedSlow=2;
    	double downSpeedFast=5;
    	
    	boolean error=false;

    	//Do some error checking to prevent hardships
    	if(homingStatus==false){
			System.out.println("ERROR::"+systemName+"::System not homed; Home before using update"); 
			error=true;
    	}
    	if(homingStatus && getHeight()>inchesTop+1){
			System.out.println("ERROR::"+systemName+"::Current height above robot limit"); 
			error=true;
	  	}
    	if(homingStatus &&  getHeight()<inchesBottom-1){
			System.out.println("ERROR::"+systemName+"::Current height below robot limit"); 
			error=true;
    	}	
    	if(isBottomSwitchPressed(false)){
			//System.out.println("WARNING::"+systemName+"::Current height at lower limit"); 
    	}
    	if(isTopSwitchPressed(false)){
			//System.out.println("WARNING::"+systemName+"::Current height at upper limit"); 
    	}
    	if(error){
    		return;
    		}
    		
    	
    	
    	/*
    	 * Set differential speeds depending on where we're trying to go
    	 * This is the hard-and-fast approach, but we can set various breakpoints as needed
    	 */
    	if(difference<0.5){
    		stop(false); //stop, but leave the I term to ensure we don't drop things
    	}
    	else if(getHeight()<setpoint && difference<2){
    		up(upSpeedSlow);
		}
    	else if(getHeight()<setpoint){
    		up(upSpeedFast);
		}
		else if(getHeight()>setpoint && difference<2){
			down(downSpeedSlow);
		}
		else if(getHeight()>setpoint){
			down(downSpeedFast);
		}
		else{
			stop();
			System.out.println("ERROR::"+systemName+"::Height is nonsensical"); 
		}



    }

    /**
     * Set the robot velocity in raw ticks. This should be used by every function in this class, but not used externally. 
     * @param ticks travelled every 10 ms.
     */
    public void setTickVelocity(double ticks){
    	if (reverseMotorDirection)ticks= -ticks;
    	talonSRX.set(ticks);
    }
    
    /**Use this function to reverse the motor direction without fiddling with the Talon
     * @param reversed
     */
    public void reverseMotorDirection(boolean reversed){
    	reverseMotorDirection=reversed;
    }
    
    /**Moves the system up at a given rate. 
     * @param inchesPerSecond A positive value means up, a negative value means go down
     */
    public void up(double inchesPerSecond){
    	//TODO: Test this, then use them exclusively when setting directions to the robot
    	double ticksPerSecond=inchesPerSecond*ticksPerInch()/100;
    	
    	//set(speed) requires units of ticks/10ms, 
    	setTickVelocity(ticksPerSecond);
    }
    public void up(){
    	up(5);
    }

    /**Moves the system downward at a given rate. 
     * @param inchesPerSecond A positive value means up, a negative value means go down
     */
    public void down(double inchesPerSecond){
    	up(-inchesPerSecond); //Shorthand, save typing by making Up do everything
    }
    public void down(){
    	down(5);
    }
    
    public void stop(boolean clearIAccumulation){      
    	talonSRX.set(0);
    	if(clearIAccumulation)talonSRX.ClearIaccum();
    }
    public void stop(){    
    	stop(true);
    }

    
    public void disable(){
    	heightMonitoringEnabled=false;
    	stop(false);//This seems like a logical thing to add, but maybe not
    }
    public void enable(){
    	heightMonitoringEnabled=true;
    	stop(false);
    	setHeight(getHeight());
    }
	public boolean isEnabled() {
		return heightMonitoringEnabled;
	}
    
    
    
    
    public double getTargetHeight(){
    	return setpoint;
    }
    public double getHeight(){
    	//TODO: Test function for correctness
    	return ticksToInches(talonSRX.getPosition()-tickValueOfBottomLimitSwitch);
    }

    public void setHeight(double inches){
    	setpoint=inches;
    }

    
    public boolean isOnTarget(double tolerance){
    	double difference=Math.abs(getHeight()-setpoint);
    	return difference<Math.abs(tolerance);
    }
    public boolean isOnTarget(){
    	return isOnTarget(1);
    }
    
    
    public boolean isBottomSwitchPressed(boolean includeSoftLimits){
    	/* Note, the change in nomenclature between Bottom and Reverse: 
    	 * Reverse is relative to the motor, which we (as a general rule) don't care about. 
    	 * Bottom ensures that we always grab the intended switch elsewhere in our code
    	 */
    	if(softLimitsEnabled && includeSoftLimits && homingStatus==true){
    		return getHeight()<inchesBottom;
    	}
    	
    	return talonSRX.isRevLimitSwitchClosed();   //TODO: Ensure that this is in fact the bottom switch 	
    }
    public boolean isBottomSwitchPressed(){
    	return isBottomSwitchPressed(true);
    }
    
    public boolean isTopSwitchPressed(boolean includeSoftLimits){
    	if(softLimitsEnabled && includeSoftLimits && homingStatus==true){
    		return getHeight()>inchesTop;
    	}
    	return talonSRX.isFwdLimitSwitchClosed();//TODO: Ensure that this is in fact the top switch    	
    }
    public boolean isTopSwitchPressed(){
    	return isTopSwitchPressed(true);
    }
    
    public void printStatus(){
    	//System.out.printf("Switches :: Homed? (%5b) Top(%5b) Bottom(%5b) onT($5b)\n",homingStatus,isTopSwitchPressed(false),isBottomSwitchPressed(false),isOnTarget());
    	//System.out.printf("Inches   :: Current(%5f) Top(%5f) Bottom(%5f) Set(%5f)\n",getHeight(),inchesTop,inchesBottom, setpoint);
    	//System.out.printf("Ticks    :: Current(%5f) Top(%5f) Bottom(%5f) Set(%5f)\n",bident.getEncPosition(),ticksTop-tickValueOfBottomLimitSwitch,inchesBottom-tickValueOfBottomLimitSwitch, 		inchesToTicks(setpoint));
    	
    	if(softLimitsEnabled){
    	//System.out.printf("SoftStops:: Current(%5f) Top(%5f) Bottom(%5f) TopHit(%5d) BotHit(%5d)\b",getHeight(),softStopInchesTop,softStopInchesTop, isTopSwitchPressed(true),isBottomSwitchPressed(true));
    	}
    }
    
    /*
     * Math utility functions to simplify conversions
     * TODO: Test utility functions for correctness 
     */
        
    /**
     * @return Number of ticks corresponding to 1 inch height
     */
    public double ticksPerInch(){
    	return Math.abs((ticksBottom-ticksTop)/(inchesBottom-inchesTop));
    }
    
    /** 
     * @return number of inches in 1 encoder tick
     */
    public double inchesPerTick(){
    	return Math.abs((inchesBottom-inchesTop)/(ticksBottom-ticksTop));
    }
    
    /**Uses Map to convert data using values set in setRange
     * @param inches
     * @return
     */
    public double ticksToInches(double ticks){
    	return map(ticks,ticksBottom-tickValueOfBottomLimitSwitch,ticksTop-tickValueOfBottomLimitSwitch,inchesBottom,inchesTop);
    }
    
    /**Shortcut to convert inches to ticks, using values defined with setRange
     * @param inches
     * @return encoder ticks corresponding to that height
     */
    public double inchesToTicks(double inches){
    	return map(inches,inchesBottom,inchesTop,ticksBottom-tickValueOfBottomLimitSwitch,ticksTop-tickValueOfBottomLimitSwitch);
    }
    
    /**
     * Converts from one data range to another.
     * @param input
     * @param maximum
     * @param minimum
     * @param outputMax
     * @param outputMin
     * @return
     */
    protected double map( double input, double maximum, double minimum, double outputMax, double outputMin){
    	double output = (input/(maximum-minimum)-minimum/(maximum-minimum))*(outputMax-outputMin)+outputMin;
    	if (output==Double.NaN){
    		output=minimum;//Shouldn't happen unless we divide by zero somewhere
    		}
    	return output; 
    	}
    
    /**
     * Helper function to restrict a particular value to a given range
     * @param value 
     * @param min: Minimum value that will be returned
     * @param max: Maximum value that will be returned
     * @return Unmodified value if it's within min and max, otherwise return min or max value
     */
    protected double constrain(double value, double min, double max){
    	if(value>max)value=max;
    	if(value<min)value=min;
    	
    	return value;
    }

    
}

