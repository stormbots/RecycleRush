package org.usfirst.frc2811.RecycleRush.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;


/*SUBSYSTEM METHODS:
 * ---These methods work with the network table---
 * init : checks for connection to the network table
 * update : gets new values from the network table
 * initDefaultCommand : runs init method
 * ---These methods get the raw coordinate data from the network table---
 * getX : returns the x value of the first object
 * getY : returns the y value of the first object
 * getX2 : returns the x value of the second object
 * getY2 : returns the y value of the second object
 * 
*/

public class Camera extends Subsystem { //This subsystem gets object coordinates from RoboRealm
	private static double x;			//through a network table that converts them from Visual Basic data
	private static double y;			//to Java data that we can use. It then returns that data in the
	private static double x2;           //form of the doubles x and y.
	private static double y2;
	private static double offY;
	private static double offX;
	private static String tableName="SmartDashboard";
	static NetworkTable server = NetworkTable.getTable(tableName);
	
	public static void init(){			  //Runs once in AutonomousInit to check
		if(server.isConnected() == true){ //if the code can retrieve data from the network table.
			System.out.println("Connection established.");
			server.putBoolean("Success", true);
		}else{
			System.out.println("Cannot access the network table! Reconnecting...");
			server = NetworkTable.getTable(tableName);
		}
	}
	
	
	
	
	public static void update(){
		//put code here
		//init();
		//System.out.println("Camera Connection Status: " + server.isConnected());
		if(server.isConnected() == true){
			try{ //RoboRealm draws a bounding box around yellow objects, and records the coordinates
				 //of the center of gravity of said box in the variables COG_X and COG_Y.
				 //NetworkTables transfers the Visual Basic data from RoboRealm into Java data we can use.
				x = server.getNumber("cogX", -3); //-3 and -2 are used as default values so it will be clear when
				y = server.getNumber("cogY", -2); //COG_X and COG_Y are returning default values.
				x2 = server.getNumber("cogX2", -5);
				y2 = server.getNumber("cogY2", -4);
				System.out.print(" OK?" +server.getBoolean("Success",false) + " ");
			}catch(TableKeyNotDefinedException ex){
				System.out.println("Camera Exception: Key not defined");
			}
		}else{
			System.out.println("Cannot access the network table!");
		}
		//set x
		//set y
	}

	@Override
	protected void initDefaultCommand() {
		//runs when we create the subsystem
		init();
		
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	public static double getX(){		 //These methods return the values retrieved in the 
		update();                        //update method as doubles.
		//System.out.println("Getting X");
		return Map(x, 0, 640, -320, 320);
	}
	public static double getY(){
		update();
		//System.out.println("Getting Y");
		return Map(y, 0, 480, -240, 240);
	}
	public static double getX2(){
		update();
		//System.out.println("Getting X2");
		return Map(x2, 0, 640, -320, 320);
	}
	public static double getY2(){
		update();
		//System.out.println("Getting Y2");
		return Map(y2, 0, 480, -240, 240);
	}
	
	
	
	
	private static double Map( double input, double maximum, double minimum, double outputMax, double outputMin){
		double output = (input/(maximum-minimum)-minimum/(maximum-minimum))*(outputMax-outputMin)+outputMin;
		return output; 
		}

}

