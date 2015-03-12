package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraightUntil extends Command {
	double distance;
	double timeout;
    double startLeft;
    double startRight;
    public AutoDriveStraightUntil(double inches) {
    	
    	distance = inches;
    	//this.setTimeout(timeout);
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startLeft=Robot.chassis.getInchesForwardLeft();
    	startRight=Robot.chassis.getInchesForwardRight();
    	System.out.println("Drive Straight init");
    	System.out.println(startLeft);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (distance < 0){
    		Robot.chassis.autoDrive(0,.4, 0);
    	}
    	Robot.chassis.autoDrive(0, -0.4, 0);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return (startLeft + distance <= Robot.chassis.getInchesForwardLeft()) && (startRight + distance <= Robot.chassis.getInchesForwardRight() );
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Drive Straight end Left");
    	System.out.println(Robot.chassis.getInchesForwardLeft());
    	System.out.println("Drive Straight end Right");
    	System.out.println(Robot.chassis.getInchesForwardRight());
    	Robot.chassis.autoDrive(0,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
