package org.usfirst.frc2811.RecycleRush.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class Camera extends Subsystem { //This subsystem gets object coordinates from RoboRealm
	private static double x;			//through a network table that converts them from Visual Basic data
	private static double y;			//to Java data that we can use. It then returns that data in the
	private static double x2;           //form of the doubles x and y.
	private static double y2;
	//private static String tableName="SmartDashboard";
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
				x = server.getNumber("COG_X", -3); //-3 and -2 are used as default values so it will be clear when
				y = server.getNumber("COG_Y", -2); //COG_X and COG_Y are returning default values.
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
		return x;
	}
	public static double getY(){
		update();
		//System.out.println("Getting Y");
		return y;
	}
	public static double getX2(){
		update();
		//System.out.println("Getting X2");
		return x2;
	}
	public static double getY2(){
		update();
		//System.out.println("Getting Y2");
		return y2;
	}
	
}

