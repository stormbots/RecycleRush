
package org.usfirst.frc2811.RecycleRush.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2811.RecycleRush.Robot;

/**
 * Bident Monitor controls the PID loop to ensure the bident is where it should be.   
 * Should be created during an init function, and then 
 */
public class BidentMonitor extends Command {

	double oldsetpoint;
    public BidentMonitor() {
        // Use requires() here to declare subsystem dependencies
        //Do nothing, just update

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("STATUS::BIDENT::Monitor::Starting up bident control sequence.");
    	oldsetpoint=Robot.bident.getTargetHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(oldsetpoint!=Robot.bident.getTargetHeight()){
        	System.out.println("STATUS::BIDENT::Detected Setpoint Change from "+oldsetpoint+" to "+Robot.bident.getTargetHeight() );
        	if(!Robot.bident.isHomed()){
                	System.out.println("STATUS::BIDENT::No action::not homed");
        	}
        	if(!Robot.bident.isEnabled()){
            	System.out.println("STATUS::BIDENT::No action::not enabled");
        	}
        	oldsetpoint=Robot.bident.getTargetHeight();
    	}

    	
    	if(Robot.bident.isHomed()&& Robot.bident.isEnabled()){
    		Robot.bident.update();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //should never exit
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("ERROR::BIDENT::Monitor::This function should not exit.");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("ERROR::BIDENT::Monitor::Bident command killed::Will need to be restarted in next Init mode to operate");
    }
}
