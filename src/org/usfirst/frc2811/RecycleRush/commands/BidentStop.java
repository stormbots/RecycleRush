package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the bident where it's currently at. 
 * Optionally, can be set to clear the I accumulation for safety on mode switching
 * @params optional, ClearIaccumulation: Clear I accumulation of PID
 */
public class BidentStop extends Command {
	boolean clearIaccumulation=false;
    public BidentStop() {
        // Use requires() here to declare subsystem dependencies
        //Do nothing, just update
    }
    public BidentStop(boolean ClearIaccumulation) {
        // Use requires() here to declare subsystem dependencies
        //Do nothing, just update
    	clearIaccumulation=ClearIaccumulation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.bident.stop(clearIaccumulation);
    	System.out.println("Bident::Stop::Stopping at "+Robot.bident.getHeight()+ (clearIaccumulation?" and cleared Iaccumulation":"") );
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
