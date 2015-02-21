// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2811.RecycleRush;

import org.usfirst.frc2811.RecycleRush.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	public static String driver="testing";

	public Joystick gamePad;
	    public JoystickButton buttonG1 ;
		public JoystickButton buttonG2 ;
		public JoystickButton buttonG3 ;
		public JoystickButton buttonG4 ;
		public JoystickButton buttonG5 ;
		public JoystickButton buttonG6 ;
		public JoystickButton buttonG7 ;
		public JoystickButton buttonG8 ;
		public JoystickButton buttonG9 ;
		public JoystickButton buttonG10;
		
	public Joystick leftStick;
		public JoystickButton buttonL1 ;
		public JoystickButton buttonL2 ;
		public JoystickButton buttonL3 ;
		public JoystickButton buttonL4 ;
		public JoystickButton buttonL5 ;
		public JoystickButton buttonL6 ;
		public JoystickButton buttonL7 ;
		public JoystickButton buttonL8 ;
		public JoystickButton buttonL9 ;
		public JoystickButton buttonL10;
    
    public Joystick rightStick;
	    public JoystickButton buttonR1 ;
		public JoystickButton buttonR2 ;
		public JoystickButton buttonR3 ;
		public JoystickButton buttonR4 ;
		public JoystickButton buttonR5 ;
		public JoystickButton buttonR6 ;
		public JoystickButton buttonR7 ;
		public JoystickButton buttonR8 ;
		public JoystickButton buttonR9 ;
		public JoystickButton buttonR10;
    	
    public Joystick threeAxis;
	    public JoystickButton buttonT1 ;
		public JoystickButton buttonT2 ;
		public JoystickButton buttonT3 ;
		public JoystickButton buttonT4 ;
		public JoystickButton buttonT5 ;
		public JoystickButton buttonT6 ;
		public JoystickButton buttonT7 ;
		public JoystickButton buttonT8 ;
		public JoystickButton buttonT9 ;
		public JoystickButton buttonT10;
		public JoystickButton buttonT11;
		public JoystickButton buttonT12;
	
    public double getAxisForward(){
        switch(driver.toLowerCase()){
    		case "jd":	
    			return gamePad.getY();
    		case "austin":	
    			return leftStick.getY();
    		case "testing":	
    			return threeAxis.getY();
    		default :
    			return 0;
        	}
        }
    public double getAxisTranslate(){
        switch(driver.toLowerCase()){
    		case "jd":	
    			return gamePad.getX();
    		case "austin":	
    			return leftStick.getX();
    		case "testing":	
    			return threeAxis.getX();
    		default :
    			return 0;
        	}
        }
    public double getRotation(){
        switch(driver.toLowerCase()){
    		case "jd":	
    			return gamePad.getZ();
    		case "austin":	
    			return rightStick.getX();
    		case "testing":	
    			return threeAxis.getZ();
    		default :
    			return 0;
        	}
    }
    
    public double getGyro(){
    	switch(driver.toLowerCase()){
    	case "jd":
    		return 0;
    	case "austin":
    		return Robot.chassis.adjustedYaw();
    	case "testing":
    		return Robot.chassis.adjustedYaw();
    	default:
    		return 0;
    	}
    }
        
    public OI(String new_driver){
    	driver=new_driver;
    	if(driver=="default"){
    		driver = "jd";
    	}
        SmartDashboard.putData("SetDriver(JD)", new SwitchDriver("jd"));
        SmartDashboard.putData("SetDriver(Austin)", new SwitchDriver("austin"));
        SmartDashboard.putData("SetDriver(Test Mode)", new SwitchDriver("testing"));
    		
    	switch(driver.toLowerCase()){
    		case "jd":
	    		gamePad = new Joystick(0);
		        
		        buttonG1  = new JoystickButton(gamePad,  1);
		        buttonG1.whenPressed(new ToteStop());
		               
		        buttonG2  = new JoystickButton(gamePad,  2);
		        buttonG2.whileHeld(new ToteOut());
		        //buttonG2.whenReleased(new BidentStop());
		        
		        buttonG3  = new JoystickButton(gamePad,  3);
		        buttonG3.whenPressed(new BidentStop());
		        
		        buttonG4  = new JoystickButton(gamePad,  4);
		        buttonG4.whileHeld(new ToteIn());
		        //buttonG4.whenReleased(new BidentStop());		        		
		        
		        buttonG5  = new JoystickButton(gamePad,  5);
		        buttonG5.whenPressed(new BidentUp());
		        //buttonG5.whenReleased(new BidentStop());
		        
		        buttonG6  = new JoystickButton(gamePad,  6);
		        buttonG6.whileHeld(new ToteUpManual());
		        buttonG6.whenReleased(new ToteStop());
		        
		        buttonG7  = new JoystickButton(gamePad,  7);
		        buttonG7.whenPressed(new BidentDown());
		        //buttonG7.whenReleased(new BidentStop());
		        
		        buttonG8  = new JoystickButton(gamePad,  8);
		        buttonG8.whileHeld(new ToteDownManual());
		        buttonG8.whenReleased(new ToteStop());
		        
		        buttonG9  = new JoystickButton(gamePad,  9);
		        buttonG9.whenPressed(new BidentToggle());
		        
		        buttonG10 = new JoystickButton(gamePad, 10);
		        buttonG10.whenPressed(new BidentHoming());
		        
		        
		    break;
		    
    		case "austin":
        		leftStick = new Joystick(0);
    	        
    	        buttonL1  = new JoystickButton(leftStick,  1);
    	        buttonL1.whenPressed(new BidentToggle());
    	               
    	        buttonL2  = new JoystickButton(leftStick,  2);
    	        buttonL2.whileHeld(new BidentUpManual());
    	
    	        buttonL3  = new JoystickButton(leftStick,  3);
    	        buttonL3.whileHeld(new BidentDownManual());
    	        
    	        buttonL4  = new JoystickButton(leftStick,  4);
    	        
    	        
    	        buttonL5  = new JoystickButton(leftStick,  5);
    	        
    	        
    	        buttonL6  = new JoystickButton(leftStick,  6);
    	        
    	        
    	        buttonL7  = new JoystickButton(leftStick,  7);
    	        
    	        
    	        buttonL8  = new JoystickButton(leftStick,  8);
    	        
    	        
    	        buttonL9  = new JoystickButton(leftStick,  9);
    	        
    	        
    	        buttonL10 = new JoystickButton(leftStick, 10);
    	        
    	        rightStick = new Joystick(1);
    	        
    	        buttonR1 = new JoystickButton(rightStick,   1);
    	        buttonR1.whenPressed(new ToteStack());
    	
    		    buttonR2 =  new JoystickButton(rightStick,  2);
    		    buttonR2.whenPressed(new ToteUpManual());
    		    
    		    buttonR3 =  new JoystickButton(rightStick,  3);
    		    buttonR3.whenPressed(new ToteDownManual());
    		    
    		    buttonR4 =  new JoystickButton(rightStick,  4);
    	        buttonR4.whenPressed(new ToteIn());
    	        
    	        buttonR5 =  new JoystickButton(rightStick,  5);
    	        buttonR5.whenPressed(new ToteOut());
    	        
    	        buttonR6  = new JoystickButton(rightStick,  6);
    	        
    	        
    	        buttonR7  = new JoystickButton(rightStick,  7);
    	        
    	        
    	        buttonR8  = new JoystickButton(rightStick,  8);
    	        
    	        
    	        buttonR9  = new JoystickButton(rightStick,  9);
    	        
    	        
    	        buttonR10 = new JoystickButton(rightStick, 10);
    	        
    	        
    	    break;
    		    
    		case "testing":
        		threeAxis = new Joystick(0);
    	         
    	        buttonT1  = new JoystickButton(threeAxis,  1);
    	        buttonT1.whenPressed(new BidentToggle()); 
    	               
    	        buttonT2  = new JoystickButton(threeAxis,  2);
    	        buttonT2.whileHeld(new BidentUpManual());
    	
    	        buttonT3  = new JoystickButton(threeAxis,  3);
    	        buttonT3.whileHeld(new BidentDownManual());
    	             	        
    	        buttonT4  = new JoystickButton(threeAxis,  4);
    	        buttonT4.whenPressed(new ToteStack());
    	
    		    buttonT5  = new JoystickButton(threeAxis,  5);
    		    buttonT5.whenPressed(new ToteUpManual());
    		    
    		    buttonT6  = new JoystickButton(threeAxis,  6);
    		    buttonT6.whenPressed(new ToteDownManual());
    		    
    		    buttonT7  = new JoystickButton(threeAxis,  7);
    	        buttonT7.whenPressed(new ToteIn());
    	            	
    	        buttonT8  = new JoystickButton(threeAxis,  8);
    	        buttonT8.whenPressed(new ToteOut());
    	        
    	        buttonT9  = new JoystickButton(threeAxis,  9);
    	        
    	        
    	        buttonT10 = new JoystickButton(threeAxis, 10);
    	        
    	        
    	        buttonT11 = new JoystickButton(threeAxis, 11);
    	        
    	        
    	        buttonT12 = new JoystickButton(threeAxis, 12);
    	        
    	        

    		break;
    	}    
	        
    }    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
 }

