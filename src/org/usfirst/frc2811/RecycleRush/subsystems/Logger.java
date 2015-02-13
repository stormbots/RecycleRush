
package org.usfirst.frc2811.RecycleRush.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to help manage large amounts of print statements 
 * Can be used to print common DEBUB, WARNING, INFO, or STATUS messages, 
 * as well as specially named channels  
 */
public class Logger extends Subsystem {
    
	//This is used to store special channels 
    public HashMap<String, Boolean> specials=new HashMap<String, Boolean>(32);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Logger(){
       	WARNING=true;
    	STATUS=true;
    }
        
    private boolean DEBUG=false;
    private boolean WARNING=false;
    private boolean INFO=false;
    private boolean STATUS=false;
    
    //Create some setter functions
    /**
     * Enable DEBUG level print statements
     * @param string
     */
    void setDebug(boolean state){DEBUG=state;}
    /**
     * Enable WARNING level print statements
     * @param string
     */
    void setWarning(boolean state){DEBUG=state;}
    /**
     * Enable STATUS level print statements
     * @param string
     */
    void setStatus(boolean state){DEBUG=state;}
    /**
     * Enable INFO level print statements
     * @param string
     */
    void setInfo(boolean state){DEBUG=state;}

    
    /**
     * Print a DEBUG level statement, if enabled
     * @param string
     */
    public void debug(String string){
    	if(DEBUG)System.out.println("(DEBUG) "+string);   
    	}
    /**
     * Print a WARNING level statement, if enabled
     * @param string
     */
    public void warning(String string){
    	if(WARNING)System.out.println("(WARNING) "+string);  
    	}
    /**
     * Print a INFO level statement, if enabled
     * @param string
     */
    public void info(String string){    
    	if(INFO)System.out.println("(INFO) "+string);   
    }
    /**
     * Print a STATUS level statement, if enabled
     * @param string
     */
    public void status(String string){  
    	if(STATUS)System.out.println("(STATUS) "+string);   
    }
    	    
    /**
     * Print a special logging statement, if enabled by setSpecial
     * @param name : Provide a name for custom logging channels
     * @param string : The data to print
     */
    public void special(String name, String string){
    	if(specials.get(name)!=null){
        	//if it exists, it's true
    		System.out.println("("+name+")	" +string);
    	}
    }
    /**
     * Create custom logging channels, and enable or disable printing from it.
     * @param name
     * @param state
     */
    public void setSpecial(String name, boolean state){
    	//This will create a key with the value of True, or remove it from the channel list
    	if(state){
    		specials.put(name, state);
    	}
    	else{
    		specials.remove(name);
    	}
    	
    }
}

