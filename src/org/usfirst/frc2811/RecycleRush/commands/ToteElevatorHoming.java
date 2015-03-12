package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteElevatorHoming extends Command {

    public ToteElevatorHoming() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ToteElevator::Homing::Starting homing sequence");
    	System.out.println("ToteElevator::Homing::Printing uncalibrated values");
    	Robot.toteElevator.printStatus();
    	System.out.println("ToteElevator::Homing::End values");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteElevator.setTickVelocity(-60);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.toteElevator.isBottomSwitchPressed() || Robot.toteElevator.isTopSwitchPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteElevator.setHomedState(true);
    	Robot.toteElevator.stop();
    	System.out.println("toteElevator::Homing::Starting homing Sequence completed");
    	System.out.println("toteElevator::Homing::Printing calibration values");
    	Robot.toteElevator.printStatus();
    	System.out.println("toteElevator::Homing::End values");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
