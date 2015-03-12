package org.usfirst.frc2811.RecycleRush.commands;

import org.usfirst.frc2811.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BidentTestGroup extends CommandGroup {
    
    public  BidentTestGroup() {
    	
    	double moveduration=1;
    	double holdduration=1;

    	//* TEST ONE: Move up and down a short bit, just to test things
    	double testvelocity=100;
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
    	//*/
    	
    	/* TEST 2: Test homing commands
    	addSequential(new BidentHome());
    	
    	addSequential(new WaitCommand(holdduration));
    	
    	addSequential(new BidentSet(9),3);

    	addSequential(new WaitCommand(holdduration));
    	
    	addSequential(new BidentSet(20),4);

    	addSequential(new WaitCommand(holdduration));

    	
    	//*/
    	
    	
    }
}
