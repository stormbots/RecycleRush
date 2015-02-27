package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BidentStop extends Command {

    public BidentStop() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bident.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//must be used while held()
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//return PID control
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
