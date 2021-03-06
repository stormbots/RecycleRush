package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BidentUp extends Command {
	
	public BidentUp() {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);

	        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	    // Called just before this Command runs the first time
	protected void initialize() {
		Robot.logger.setDebug(true);
		Robot.logger.debug("bident tote up init");
		Robot.logger.debug(""+Robot.bident.getTotePosition());
	    Robot.bident.goUpOneTote();
	    System.out.println(Robot.bident.getTotePosition());
	   Robot.logger.debug(""+Robot.bident.getTotePosition());
	}
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.bident.isOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println(Robot.bident.getTotePosition());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
