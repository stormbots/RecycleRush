
package org.usfirst.frc2811.RecycleRush.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2811.RecycleRush.Robot;

/**
 * Moves the Elevator a specific velocity. 
 * NOTE: Only for use in testing. If the robot is homed, bidentMonitor will override this.
Use Up/DownManual instead.
 */
public class ToteSetVelocity extends Command {
	private double tickVelocity;
    public ToteSetVelocity(double velocityInTicks) {
        // Use requires() here to declare subsystem dependencies
        //Do nothing, just update
    	tickVelocity=velocityInTicks;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ToteElevator::SetVelocity:: Set to " + tickVelocity);

    	Robot.toteElevator.setTickVelocity(tickVelocity);
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
