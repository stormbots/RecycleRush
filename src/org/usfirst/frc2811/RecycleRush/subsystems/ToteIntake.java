// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2811.RecycleRush.subsystems;

import org.usfirst.frc2811.RecycleRush.Robot;
import org.usfirst.frc2811.RecycleRush.RobotMap;
import org.usfirst.frc2811.RecycleRush.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class ToteIntake extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController toteRollerL = RobotMap.intakeRollerL;
    SpeedController toteRollerR = RobotMap.intakeRollerR;
    Solenoid toteSolenoidL = RobotMap.intakeSolenoidL;
    Solenoid toteSolenoidR = RobotMap.intakeSolenoidR;
    
    private double defaultSpeed = .4;

    //TODO these true and false for open/close functions may change
    private boolean open = true;
    private boolean close = !open;
    private int rollerState = 0;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void open() {
    	//solenoid disabled in hardware
    	//toteSolenoidL.set(open);
    	//toteSolenoidR.set(open);
    	
    }
    
    public void close(){
    	//toteSolenoidL.set(close);
    	//toteSolenoidR.set(close);
    }
    
    public void rollersOff(){
    	toteRollerL.set(0);
    	toteRollerR.set(0);
    	rollerState = 0;
    }
    
    public void rollersIn(){
    	rollersIn(-defaultSpeed);
    }
    
    public void rollersIn(double speed){
    	toteRollerL.set(-speed);
    	toteRollerR.set(speed);
    	rollerState = -1;
    }
    
    public void rollersOut(){
    	rollersOut(defaultSpeed);
    }
    
    public void rollersOut(double speed){
    	toteRollerL.set(-speed);
    	toteRollerR.set(speed);
    	rollerState = 1;
    }
    public int rollerState(){
    	return rollerState;
    }

    public boolean switchIsPressed(){
    	return Robot.toteElevator.switchIsPressed();
    	//TODO Make sure this is the correct reading for have a tote
    }


}

