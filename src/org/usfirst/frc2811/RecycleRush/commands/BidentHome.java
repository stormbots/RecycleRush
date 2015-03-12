package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BidentHome extends Command {

    public BidentHome() {
        // Use requires() here to declare subsystem dependencies
        //Do nothing, just update
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Bident::Homing::Starting homing sequence");
    	System.out.println("Bident::Homing::Printing uncalibrated values");
    	Robot.bident.printStatus();
    	System.out.println("Bident::Homing::End values");

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.bident.down(4);
    	Robot.bident.setTickVelocity(-60);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*NOTE: Ensure that the robot goes the correct way for homing, since it requires the switch on the physical bottom of the robot
    	 * Since the switches might have reversed hardware, this ensures that the homing sequence always exits, even if we fiddle with them
    	 */
    	return Robot.bident.isBottomSwitchPressed() || Robot.bident.isTopSwitchPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.bident.setHomedState(true);
    	Robot.bident.stop();
    	System.out.println("Bident::Homing::Starting homing Sequence completed");
    	System.out.println("Bident::Homing::Printing calibration values");
    	Robot.bident.printStatus();
    	System.out.println("Bident::Homing::End values");
    	
     }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
