package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteUpManual extends Command {

    public ToteUpManual() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.toteElevator.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteElevator.up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteElevator.enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.toteElevator.enable();
    }}
