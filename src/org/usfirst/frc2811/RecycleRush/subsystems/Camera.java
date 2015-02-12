package org.usfirst.frc2811.RecycleRush.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class Camera extends Subsystem {
	private static double x;
	private static double y;
	
	//static NetworkTable server;
	static NetworkTable server = NetworkTable.getTable("SmartDashboard");
	
	public static void init(){
		if(server.isConnected() == true){
			System.out.println("Connection established.");
		}else{
			System.out.println("Cannot access the network table!");
		}
	}
	
	
	
	
	public static void update(){
		//put code here
		System.out.println(server.isConnected());
		if(server.isConnected() == true){
			try{
				x = server.getNumber("COG_X", -3);
				y = server.getNumber("COG_Y", -2);
			}catch(TableKeyNotDefinedException ex){
				
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
		
		
		// TODO Auto-generated method stub
		
	}
	public static double getX(){
		update();
		return x;
	}
	public static double getY(){
		update();
		return y;
	}
	
}

