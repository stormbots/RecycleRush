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

import org.usfirst.frc2811.RecycleRush.RobotMap;
import org.usfirst.frc2811.RecycleRush.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;


/**
 *
 */
public class ToteElevator extends TalonSRXPIDBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DigitalInput toteSwitch = RobotMap.elevatorReadySwitch;
    AnalogInput toteSonar = RobotMap.elevatorSonar;
    
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //FIXME public Ultrasonic rangeFinder = new Ultrasonic(1,0);
    //TODO fix port declaration, port currently using one wire, need to wires
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//Copy motor instance to ensure this function works right
        motor = RobotMap.elevatorMotor;
        
        
        isHomed=false;
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getDistanceToTote(){
    	//
    	//FIXME rangeFinder.setEnabled(true);
    	//TODO calibrate range maybe...(limits)
    	 return 0;//toteSonar.getAverageValue();
    	
    	
    }
}

