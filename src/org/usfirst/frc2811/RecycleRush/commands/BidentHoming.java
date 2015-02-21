package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BidentHoming extends Command {

    public BidentHoming() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.logger.setStatus(true);
    	Robot.logger.status("Starting Bident Homing sequence");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bident.home();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.bident.isHomed() && Robot.bident.onTarget()){
    		//System.out.println(Robot.bident.getRawEncoder());
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.logger.status("Finishing Bident Homing sequence");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
