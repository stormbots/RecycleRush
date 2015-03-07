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
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand() {
        // Add Commands here:
    	//FIXME: Make these parallel and wait for all to quit
    	
    	double moveduration=1;
    	double holdduration=1;

    	//* TEST ONE: Move up and down a short bit, just to test things
    	/*double testvelocity=100;
    	addSequential(new BidentSetVelocity(0));
    	addSequential(new WaitCommand(holdduration));
    	
    	//Moves practice bot bident upward
    	addSequential(new BidentSetVelocity(testvelocity));
    	addSequential(new WaitCommand(moveduration));
    	
    	addSequential(new BidentSetVelocity(0));
    	addSequential(new WaitCommand(holdduration));
    	
    	//Moves practice bot bident downward
    	addSequential(new BidentSetVelocity(-testvelocity));
    	addSequential(new WaitCommand(moveduration));
    	
    	addSequential(new BidentSetVelocity(0),1);
    	*/
    	addSequential(new BidentOpen());
    	addSequential(new BidentClose());
    	addSequential(new WaitCommand(1));
    	addSequential(new BidentSetVelocity(200));
    	addSequential(new WaitCommand(1.5));
    	addSequential(new BidentSetVelocity(0));
    	addSequential(new AutoDriveStraightUntil(48),4);//FIXME test timeout.
    	addSequential(new AutoDriveStraightUntil(90),4);
    	//addSequential(new AutoDrive(0, 0, .6, .5));
    	//addSequential(new BidentOpen());

    	
    	
    	/*
    	addSequential(new BidentOpen(),2);
    	
    	addSequential(new BidentStop());
        addSequential(new BidentHome(),2);
        addSequential(new BidentStop());
        addSequential(new BidentSetTotes(1));
    	
    	
    	addSequential(new ToteStop());
        addSequential(new ToteElevatorHoming(),2);
        addSequential(new ToteStop());
        addSequential(new ToteSetTotes(1));
        addSequential (new ToteStack());
       	*/
        
        /*
        addSequential (new RollersInUntil(6)); //TODO tune this value
     	addSequential (new ToteSetTotes(0));
     	addSequential (new RollersInUntil());
     	addSequential (new ToteSetTotes(1));
        */
     
     	/*        
    	addSequential(new BidentOpen(),2);
    	addSequential(new BidentClose(),2);
        addSequential(new BidentSet(7));
    	*/
    	
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//TODO: Home bident and lifter
    }
}
