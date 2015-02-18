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
    	Robot.logger.setChannel("HOMING",false);
    	Robot.logger.channel("HOMING","Starting homing sequence for ToteElevator");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteElevator.Home();
    	Robot.logger.channel("HOMING","Homing. Status: "+ Robot.toteElevator.isHomed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.toteElevator.isHomed() == true){
    		//Robot.logger.channel("HOMING","Not homed" + "Encoder:"+Robot.bident.getRawEncoder());
    		return true;
    	}
       
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.logger.channel("HOMING","Successfully homed!" + Robot.toteElevator.isHomed());
    	Robot.logger.channel("HOMING","Current height: " + Robot.toteElevator.get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
