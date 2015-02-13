package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Provides a manual way to move the system up and down. Requires whileHeld button to function
 */
public class BidentUpManual extends Command {

    public BidentUpManual() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.bident);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bident.Up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//must be used while held()
        return true;
        //return Robot.bident.bidentMotor.isFwdLimitSwitchClosed()||Robot.bident.onTarget();
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
