package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BidentUp extends Command {

	
		public double TOTE = 12;
		public double GAP = 2;
		public double GRAB = 12;
		public double GAPPOSITIONONE = TOTE + GAP;
		public double GAPPOSITIONTWO = 2*TOTE + GAP;
		public double GAPPOSITIONTHREE = 3*TOTE + GAP;
		public double GAPPOSITIONFOUR = 4*TOTE + GAP;
		public double GAPPOSITIONFIVE = 5*TOTE + GAP;
		public double GAPPOSITIONSIX = 6*TOTE + GAP;
		
	    public BidentUp() {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);

	        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	if(Robot.bident.get()>GAPPOSITIONSIX){
	    		Robot.bident.set(GAPPOSITIONSIX);
	    	}
	    	if(Robot.bident.get() >= GAPPOSITIONFIVE&& Robot.bident.get()<GAPPOSITIONSIX){
	    		Robot.bident.set(GAPPOSITIONSIX);
	    	}
	    	if(Robot.bident.get() >= GAPPOSITIONFOUR&& Robot.bident.get()<GAPPOSITIONFIVE){
	    		Robot.bident.set(GAPPOSITIONFIVE);
	    	}
	    	if(Robot.bident.get() >= GAPPOSITIONTHREE&& Robot.bident.get()<GAPPOSITIONFOUR){
	    		Robot.bident.set(GAPPOSITIONFOUR);
	    	}
	    	if(Robot.bident.get() >= GAPPOSITIONTWO&& Robot.bident.get()<GAPPOSITIONTHREE){
	    		Robot.bident.set(GAPPOSITIONTHREE);
	    	}
	    	if(Robot.bident.get() >= GAPPOSITIONONE&& Robot.bident.get()<GAPPOSITIONTWO){
	    		Robot.bident.set(GAPPOSITIONTWO);
	    	}
	    	if(Robot.bident.get()<GAPPOSITIONONE){
	    		Robot.bident.set(TOTE+GRAB);
	    	}
	    	else{
	    		System.out.println("ERROR IN BIDENT UP");
	    	}
	    	
	    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
