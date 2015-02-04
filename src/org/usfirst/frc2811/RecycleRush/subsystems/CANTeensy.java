package org.usfirst.frc2811.RecycleRush.subsystems;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.can.CANExceptionFactory;
import edu.wpi.first.wpilibj.can.CANJNI;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CANTeensy extends Subsystem {
    
    
    /* Check the datasheets for your device for the arbitration IDs of the
    messages you want to send.  By convention, this is a bitstring
    containing the model number, manufacturer number, and api number. */
	protected static final int MESSAGE1_ARB_ID = 0x1;
	protected static final int MESSAGE2_ARB_ID = 0x2;
	
	/*  Device ID, from 0 to 63. This should match the CAN address or filter on the remote end 
	 */
	protected static final int DEVICE_NUMBER = 55;
	
	
	/*  Milliseconds in which to re-send the data. Can be set to CANJNI.CAN_SEND_PERIOD_NO_REPEAT to disable auto-resend*/
	protected static final int REPEAT_PERIOD = 100;
	
	//Status holds the "return" data from the recieveCANData function
	//Within subsystem functions, it can be accessed using .getType methods, such as .getChar, .getInt, .getFloat,getByte, etc.
	protected IntBuffer status = ByteBuffer.allocateDirect(4).asIntBuffer();
	
	//MessageID is the data sent to the remote CAN object. It can be added by using the .putType methods, such as .putChar, .putInt, .putFloat, .putByte, etc.
	//Due to the return type, you can also link this as .putChar('a'),
	protected IntBuffer messageId = ByteBuffer.allocateDirect(4).asIntBuffer();
	protected ByteBuffer data = ByteBuffer.allocateDirect(8);
	
	//further buffer documentation can be viewed here: http://docs.oracle.com/javase/7/docs/api/java/nio/ByteBuffer.html
	//NOTE: FRC CAN packages can only contain 29 bits of data, so care should be taken when sending long datatypes, as they may need to be broken into multiple messages.
	//TODO: Verify number of bits in can frame
	
	//TODO: Document the precise details on timestamp format and data to use on remote side
	protected ByteBuffer timestamp = ByteBuffer.allocate(4);

    
    public void initDefaultCommand() {
        //This is called when the subsystem is created, and can be used to do basic initialization.
    	
    	//to sppecify a command that runs when this subsystem is doing nothing, you can set it using the following function
        //setDefaultCommand(new MySpecialCommand()); 
    	//http://wpilib.screenstepslive.com/s/3120/m/7952/l/129364-default-commands
    }
    
    /**
     * Print out the human-readable data currently being used.
     */
    protected void printBuffers(){
    	System.out.print("Data to send:");
    	System.out.print(data.toString());
    	System.out.print("Recieved Data:");
    	System.out.print(status.toString());
    	
    }
   
    /**
     * These methods should be referenced by an extended subclass, after specifying
     * and stuffing the data object according to the desired format. For example, a system 
     */
    protected void sendCANData(){
        /* Alternatively, instead of CAN_SEND_PERIOD_NO_REPEAT, you can specify
        a period in milliseconds to automatically send the message over
        and over again. */
	    status.clear();
	    //try(exce e){
		    CANJNI.FRCNetworkCommunicationCANSessionMuxSendMessage(
		            MESSAGE1_ARB_ID | DEVICE_NUMBER,
		            data,
		            REPEAT_PERIOD,
		            status
		    );
	    //CANExceptionFactory.checkStatus(status.get(0), MESSAGE1_ARB_ID);
	    //}//try?
    }
    protected void recieveCANData(){
        /* To receive a message, put the message ID you're looking for in this
        buffer.  CANJNI...ReceiveMessage  will not block waiting for it,
        but just return null if it hasn't been received yet. */
	    messageId.clear();
	    messageId.put(0, MESSAGE2_ARB_ID | DEVICE_NUMBER);
	
	    status.clear();
	    ByteBuffer data = CANJNI.FRCNetworkCommunicationCANSessionMuxReceiveMessage(
	            messageId,
	            CANJNI.CAN_MSGID_FULL_M,
	            timestamp,
	            status
	    );
	
	    if (data != null) {
	        CANExceptionFactory.checkStatus(status.get(0), MESSAGE1_ARB_ID);
	        System.out.println("Received a message: " + Arrays.toString(data.array()));
	    }
    	
    }
}
