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
import com.kauailabs.nav6.frc.IMU;


/**
 *
 */
public class Chassis extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController frontLeftDrive = RobotMap.frontLeftDrive;
    SpeedController frontRightDrive = RobotMap.frontRightDrive;
    SpeedController backLeftDrive = RobotMap.backLeftDrive;
    SpeedController backRightDrive = RobotMap.backRightDrive;
    RobotDrive robotDrive41 = RobotMap.robotDrive41;
    IMU rotationGyro = RobotMap.chassisGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ManualDrive());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double adjustedYaw(){
    	double changedYaw = rotationGyro.getYaw();
    	if(rotationGyro.getYaw()<0){
    		changedYaw=rotationGyro.getYaw()+360;
    	} else {
    		changedYaw = rotationGyro.getYaw();
    	}
    	return -changedYaw;
    }
    
    public void joystickDrive(){
    	robotDrive41.mecanumDrive_Cartesian(Robot.oi.getAxisTranslate(), Robot.oi.getAxisForward(), 
    		//-Robot.oi.joystick.getZ(), adjustedYaw());
    		Robot.oi.getRotation(),Robot.oi.getGyro());
    }
}

