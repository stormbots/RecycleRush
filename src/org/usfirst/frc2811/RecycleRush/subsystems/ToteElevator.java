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
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 *
 */
public class ToteElevator extends TalonSRXPIDBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Ultrasonic elevatorSonar = new Ultrasonic(4,5);
    private  DigitalInput elevatorReadySwitch = new DigitalInput(6);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
    	setName("toteElevator");
    	useMotor(new CANTalon(4));//correct ID 
    	//useMotor(new CANTalon(5));//FIXME: faking mirror mode for now to 
    	
    	talonSRX.setProfile(0);
    	talonSRX.changeControlMode(CANTalon.ControlMode.Speed);
    	talonSRX.set(0);
    	talonSRX.setPID(6, 0.01,0);
    	talonSRX.setVoltageRampRate(6);
    	talonSRX.setCloseLoopRampRate(0);
    	talonSRX.enableBrakeMode(false);
    	talonSRX.clearStickyFaults();
    	
    	talonSRX.reverseOutput(false);
    	talonSRX.reverseSensor(true);
    	reverseMotorDirection(true);
    	    	
    	setRange(10, 60, 0, 9000);
    	
        
    	elevatorSonar.setEnabled(true);
    	elevatorSonar.setAutomaticMode(true);   
    	stop();

    }
        
    public double getDistanceToTote(){
    	double distance=0;
    	if(elevatorSonar.isRangeValid() && elevatorSonar.isEnabled()){
        	distance= elevatorSonar.getRangeInches();
        } else if (elevatorSonar.isEnabled() == false) {
        	distance= -1;
        } else if (elevatorSonar.isRangeValid() == false) {
        	distance= -2;
        } else {
        	System.out.println("B0RK3D!!!1!");
        	distance = -3;
        }
    	return distance;
	}

    public boolean switchIsPressed(){
    	return elevatorReadySwitch.get()==true;
    	//TODO Make sure this is the correct reading for have a tote
    }
    
    //FIXME: We need tote-handling functions. They should be similar or close to identical to the ones in Bident
    //Or, alternatively, move them to the TalonSRXPIDBase class 
    

}

