package org.usfirst.frc2811.RecycleRush.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class NetTables extends Subsystem {
	static double x;
	static double y;
	public NetTables(){
	
	
}
	
	public static void update(){
		//put code here
		NetworkTable server = NetworkTable.getTable("SmartDashboard");
		try{
			x = server.getNumber("COG_X", 000);
			y = server.getNumber("COG_Y", 000);
			
		}catch(TableKeyNotDefinedException ex)
		{
			
		}
		//set x
		//set y
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public static double getX(){
		update();
		return x;
	}
	public static double getY(){
		return y;
	}
	
}

