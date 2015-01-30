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
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;


/**
 *
 */
public class Bident extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANTalon bidentMotor = RobotMap.bidentMotor;
    Solenoid binSolenoid = RobotMap.bidentSolenoid;
    Ultrasonic binSonarTop = RobotMap.bidentSonarIntake;
    Ultrasonic binSonarBottom = RobotMap.bidentSonarBumper;
    //TODO remove the ultrasonic sensors because they are declared in the code
    //FIXME public Ultrasonic binRangeTop = new Ultrasonic(0,1);
    //FIXME public Ultrasonic binRangeBottom = new Ultrasonic(2,3);
    public boolean open = true;
    public boolean close = !open;
    //TODO fix port declaration binRangerTop and Bottom, port are for one wires and need to be two
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void init (){
    	bidentMotor.changeControlMode(CANTalon.ControlMode.Position);
    	double p = 1;
    	double i = 0;
    	double d = 0;
    	double f = 0;
    	int izone = 0; 
    	double ramprate = 36;// who knows what this will do
    	int profile = 0; 
    	bidentMotor.setPID(p,i,d,f,izone,ramprate,profile);
    }
    
    public double BidentSpeed(){
    	double speed = bidentMotor.getSpeed(); // gives speed  in the sensor's native ticks per 100ms
      	return speed;
    }

    
    public double Down(){
       	bidentMotor.getPosition();
    	double down = bidentMotor.getPosition()-100;//TODO tune this
    	return down;
    }
    
    public void Up(){
    	bidentMotor.getPosition();
    	double up = bidentMotor.getPosition()+1000;//TODO tune this
    	bidentMotor.set(up);
    }
    
    
    public double get(){
    	 double output = Map(bidentMotor.getPosition(),800,-700,78,0);
    	 return output;
    }
    
    public double set(double inches){
    	//needs to set the target for the pid controller on the srx
    	//expects inches
    	double output = Map(inches,78,0,800,-700);
    	return output;
    } 

    public double getDistanceTop(){
    	binSonarTop.setEnabled(true);
    	//TODO calibrate range maybe...(limits)
    	return binSonarTop.getRangeInches();
    }
    
    public double getDistanceBumper(){
    	binSonarBottom.setEnabled(true);
    	//TODO calibrate range maybe...(limits)
    	return binSonarBottom.getRangeInches();
    }
    
    public void bidentOpen(){
    	binSolenoid.set(open);
    }
    
    public void bidentClose(){
    	binSolenoid.set(close);
    }




private double Map( double input, double maximum, double minimum, double outputMax, double outputMin){
	double output = (input/(maximum-minimum)-minimum/(maximum-minimum))*(outputMax-outputMin)+outputMin;
	return output; 
	}
}
           
    	
    
	
	
		
	
    
    


