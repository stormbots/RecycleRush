
package org.usfirst.frc2811.RecycleRush.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2811.RecycleRush.OI;
import org.usfirst.frc2811.RecycleRush.Robot;

/**
 *
 */
public class SwitchDriver extends Command {
	private String next_driver;
	
    public SwitchDriver() {
    	//Without arguments, toggles the current driver. 
    	switch(Robot.oi.driver){
	    	case  "jd":
	    		next_driver="austin";
	    	break;
	    	
	    	case  "austin":
	    		next_driver="jd";
	    	break;
			
	    	default: 
				next_driver="twodrivers";
    	}

    }
    public SwitchDriver(String newdriver) {
    	//manually specify the new driver
    	next_driver=newdriver;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO? Set a timeout for this function when when running on held?
    	//Detect whether function has timed out, then switch the drivers, then exit.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.print("Switching drivers from " + Robot.oi.driver );

		Robot.oi=new OI(next_driver);

		System.out.println(" to " + Robot.oi.driver );
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
