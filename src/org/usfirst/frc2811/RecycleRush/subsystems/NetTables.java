package org.usfirst.frc2811.RecycleRush.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class NetTables extends Subsystem {
	static double x;
	static double y;
	//static NetworkTable server;
	static NetworkTable server=NetworkTable.getTable("SmartDashboard");
	
	public static void update(){
		//put code here
		try{
			x = server.getNumber("COG_X", -3);
			y = server.getNumber("COG_Y", -2);
			System.out.println("Updated");
			Timer.delay(7);
			
		}catch(TableKeyNotDefinedException ex)
		{
			y=-1;
			x=-1;
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
		System.out.println("Getting X");
		Timer.delay(1);
		return x;
	}
	public static double getY(){
		update();
		System.out.println("Getting Y");
		Timer.delay(1);
		return y;
	}
	
}

