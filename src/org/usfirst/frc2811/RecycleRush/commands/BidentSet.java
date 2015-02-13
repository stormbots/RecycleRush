// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2811.RecycleRush.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2811.RecycleRush.Robot;
import org.usfirst.frc2811.RecycleRush.subsystems.Bident;
import org.usfirst.frc2811.RecycleRush.subsystems.TalonSRXPIDBase;

/**Move the Bin to a specific height
 *
 */
public class  BidentSet extends Command {
	int toteNum;

    public BidentSet() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    
    public BidentSet(int numberOfTotes){
    	//Intelligently set the tote height based on how many totes we currently have
    	if (numberOfTotes == TalonSRXPIDBase.GO_ONE_TOTE_UP){
    		toteNum = Robot.bident.getTotes() + 1;
    	} else if (numberOfTotes == TalonSRXPIDBase.GO_ONE_TOTE_DOWN){
    		toteNum = Robot.bident.getTotes() - 1;
    	} else {
    		toteNum = numberOfTotes;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//figure out our current height in totes, and go up or down as needed.
    	//NEED :Current height (in totes)
    	//need special values to detect;
    	//-1 for bottom
    	//100 for up
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bident.setTotes(toteNum);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
