package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveRotateUntil extends Command {
	double rotation;
	double timeout;
    double startLeft;
    double startRight;
    public AutoDriveRotateUntil(double degrees) {
    	
    	rotation = degrees;
    	//this.setTimeout(timeout);
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startLeft=Robot.chassis.ticksToDegreesBackLeft();
    	startRight=Robot.chassis.ticksToDegreesFrontRight();
    	System.out.println("Rotation init");
    	System.out.println(startLeft);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (rotation < 0){
    		Robot.chassis.autoDrive(0,0, .4);
    	}
    	Robot.chassis.autoDrive(0, 0, -.4);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return (startLeft + rotation <= Robot.chassis.ticksToDegreesBackLeft()) && (startRight + rotation <= Robot.chassis.ticksToDegreesFrontRight() );
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Rotation end Left");
    	System.out.println(Robot.chassis.ticksToDegreesBackLeft());
    	System.out.println("Rotation end Right");
    	System.out.println(Robot.chassis.ticksToDegreesFrontRight());
    	Robot.chassis.autoDrive(0,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
