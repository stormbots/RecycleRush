
package org.usfirst.frc2811.RecycleRush.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * Subsystem to help manage large amounts of print statements 
 * Can be used to print common DEBUB, WARNING, INFO, or STATUS messages, 
 * as well as specially named channels  
 */
public class Logger extends Subsystem {
    
	/**
	 * Enable this to cause all channels to print if they haven't been explicitly disabled;
	 */
	public static boolean PRINT_BY_DEFAULT=false;
	
	//This is used to store special channels 
    private static HashMap<String, Boolean> channels=new HashMap<String, Boolean>(32);

    //lets us print the time of our error
    private static Timer timer;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Logger(){
    	//The 5 special channels are simple channels with special functions for accessing, since they're very common. 
    	setChannel("DEBUG",true);
    	setChannel("WARNING",true);
    	setChannel("INFO",true);
    	setChannel("ERROR",true);
    	setChannel("WARNING",true);
    }
        
    //Create some setter functions
    /**
     * Enable DEBUG level print statements
     * @param string
     */
    void setDebug(boolean state){setChannel("DEBUG",state);}
    
    /**
     * Enable WARNING level print statements
     * @param string
     */
    void setWarning(boolean state){setChannel("WARNING",state);}
    
    /**
     * Enable STATUS level print statements
     * @param string
     */
    void setStatus(boolean state){setChannel("STATUS",state);}
    
    /**
     * Enable INFO level print statements
     * @param string
     */
    void setInfo(boolean state){setChannel("INFO",state);}
    
    /**
     * Enable ERROR level print statements
     * @param string
     */
    void setError(boolean state){setChannel("ERROR",state);}

    
    /**
     * Print a DEBUG level statement, if enabled
     * @param string
     */
    public void debug(String string){
    	channel("DEBUG",string);   
	}
    
    /**
     * Print a WARNING level statement, if enabled
     * @param string
     */
    public void warning(String string){
    	channel("WARNING",string);   
	}
    
    /**
     * Print an INFO level statement, if enabled
     * @param string
     */
    public void info(String string){    
    	channel("INFO",string);   
    }
    
    /**
     * Print a STATUS level statement, if enabled
     * @param string
     */
    public void status(String string){  
    	channel("STATUS",string);   
    }
    
    /**
     * Print an ERROR level statement, if enabled
     * @param string
     */
    public void error(String string){  
    	channel("ERROR",string);   
    }
    	    
    /**
     * Write to a custom channel. All channels are disabled by default, and must be enabled using setChannel before text will appear
     * @param name : Provide a name for custom logging channels
     * @param string : The data to print
     */
    public void channel(String name, String string){
    	if(channels.containsKey(name) && PRINT_BY_DEFAULT==true){
    		//Channel is not created or specifically disabled, so enable it
    		channels.put(name,true);
    	}
    	
    	if(channels.containsKey(name) && channels.get(name)==true){
    		//FIXME See if you can print the time using this function
    		//System.out.printf("%5.2f  ",timer.getFPGATimestamp() );
    		System.out.print("("+name+") ");
    		System.out.println(string);
    	}
    	else{
    		//Channel disabled, do nothing.
    	}
    }
    
    /**
     * Create custom logging channels, and enable or disable printing from it.
     * @param name
     * @param state
     */
    public void setChannel(String name, boolean state){
    	channels.put(name, state);
    	/* Design Note, this will create channels with a value of "false", which seemingly does nothing.
    	 * However, if you tweak this to enable channel printing by default, not doing this will prevent you
    	 * from disabling specific channels in advance.
    	 */
    	
    }
}
