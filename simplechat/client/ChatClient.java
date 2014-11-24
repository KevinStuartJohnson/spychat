// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;

import java.io.*;

import spy.Mission;
import spy.Operative;
import spy.Resource;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  Operative operative;

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI  equalsIgnoreCase startsWith         
   * sendToServer(message);
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(Object msg, Operative operative)
  {

    BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));  

    try
    {
      if (msg.equals("#Mission")){
    	 sendToServer(operative.getCodeName()); // Sends just the codeName of operative, server will send back mission
      }
      
      if (msg.equals("#CreateMission")){
    	  
    	long endDate = System.currentTimeMillis() + 432000000; // Current time + 5 days 
    	
    	System.out.println("Enter completion date."); 
    	
    	try {
    		endDate = Long.parseLong(fromConsole.readLine());
    	}catch(Exception e){
    		System.out.println("Mission end date must be a date.");
    	}
    	System.out.println("Enter description.");
    	String description = fromConsole.readLine();
    	
    	sendToServer(new Mission(System.currentTimeMillis(),endDate,description));
      }
      
      if (msg.equals("#Validate")) {
    	  sendToServer(operative);
      }

      if (msg.equals("#MissionComplete")){
    	  System.out.println("Enter mission complete password.");
    	  String missionPassword = fromConsole.readLine();
    	  sendToServer(missionPassword);
      }

      if (msg.equals("#CreateResource")){
    	  
    	  System.out.println("Enter resource name.");
    	  
    	  String resourceName = null;
    	  try {
    		  resourceName = fromConsole.readLine();
    	  } catch (IOException e) {
    		  System.out.println("Resourse name invalid. Not resource created.");  
    	  }

    	  System.out.println("Enter resource location.");
    	  
    	  String resourceLocation = null;
    	  try {
    		  resourceLocation = fromConsole.readLine();
    	  } catch(IOException e) {
    		  System.out.println("Resourse name invalid. Not resource created.");
    	  }
    	  
    	  System.out.println("Enter resource price.");
    	  
    	  String resourcePrice = null;
    	  try {
    		  resourcePrice = fromConsole.readLine();
    	  } catch (IOException e) {
    		  System.out.println("Resourse name invalid. Not resource created.");
    	  }
    	  
    	  sendToServer(new Resource(resourceName,resourceLocation,resourcePrice));
    	  
      }
      
      if (msg.equals("#CreateOperative")){
    	  System.out.println("Enter Operative codeName");
    	  
    	  String codeName = null;
    	  try {
    		  codeName = fromConsole.readLine(); 
    	  } catch (Exception e) {
    		  System.out.println("Code Name not Valid. No Operative created.");
    	  }
    	  
    	  String pWord = null;
    	  try {
    		  pWord = fromConsole.readLine();
    	  } catch (Exception e) {
    		  System.out.println("Pass word is invalid. No Operative created");
    	  }
    	  
    	  sendToServer(new Operative(codeName,pWord));
    	  
      }
      
      
      if (msg.equals("#Quit")){
    	 sendToServer("Disconected");
    	 // Quit or something.
      }
      
      
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.");
    }

  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}

//End of ChatClient class
