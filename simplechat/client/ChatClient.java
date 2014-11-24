// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;

import java.io.*;
import java.util.ArrayList;

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
   * This method handles all data coming from the UI.
   * Possible client commands are;
   * #Mission - returns missions of current operative 
   * #CreateMission - Sends mission  with specified operative NOTE ARRAY LIST has 3 objects.
   * #Validate - Sends operative to server to see if Operative is legit 
   * #MissionComplete - Sends mission complete code         
   * #CreateResource - Sends resouirce to be created by server.
   * #CreateOperative - sends and Operative to be created in the server server 
   * Note third element in array list is arraylist of other operatives 
   * #Quit sends operative and disconnect message to server.
   * @param message The message from the UI.    
   * @param operative who is using the chat.
   */
  public void handleMessageFromClientUI(Object msg, Operative operative)
  {
	  
	ArrayList<Object> twoThingsToSend = 
			new ArrayList<Object>(); // An arrayList with two objects, the first is always the operative
	twoThingsToSend.add(operative);
	

    BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));  

    try
    {
      if (msg.equals("#Mission")){
    	 twoThingsToSend.add(msg);
    	 sendToServer(twoThingsToSend); // Requests mission of operative 
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
    	
    	
    	System.out.println("Enter operative code name.");
    	
    	String codeName = null;
    	try {
    		codeName = fromConsole.readLine();
    	} catch(Exception e){
    		System.out.println("Invalid entry.");
    	}
    	
    	twoThingsToSend.add(new Mission(System.currentTimeMillis(),endDate,description)); // Startdate is always current time
    	twoThingsToSend.add(codeName);
    	sendToServer(twoThingsToSend); 
      }
      
      if (msg.equals("#Validate")) {
    	  twoThingsToSend.add(operative);  // Sends double operative arrayList
    	  sendToServer(twoThingsToSend); 
      }

      if (msg.equals("#MissionComplete")){
    	  
    	  System.out.println("Enter mission complete password.");
    	  
    	  String missionPassword = null;
    	  try {
    		  missionPassword = fromConsole.readLine();
    	  } catch (Exception e){
    		  System.out.println("Mission password of invalid type. Mission not completed.");
    	  }
    	  
    	  twoThingsToSend.add(missionPassword);
    	  sendToServer(twoThingsToSend); 
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
    	  
    	  twoThingsToSend.add(new Resource(resourceName,resourceLocation,resourcePrice));
    	  sendToServer(twoThingsToSend); 
    	  
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
    	  
    	  boolean addMoreOperatives = true;
    	  ArrayList<String> subordinates = new ArrayList<String>();
    	  
    	  while (addMoreOperatives) {
    		  System.out.println("Add subordinates? (y/n)");
    		  if (fromConsole.readLine().equals("y")) {
    			 System.out.println("Enter code name of subordinate.");
    			 subordinates.add(fromConsole.readLine());
    		  } else {
    			 addMoreOperatives = false;
    		  }
    	  }
    	  
    	  twoThingsToSend.add(new Operative(codeName,pWord));
    	  twoThingsToSend.add(subordinates);
    	  sendToServer(twoThingsToSend); 
      }
      
      
      if (msg.equals("#Quit")){
    	  twoThingsToSend.add("#Disconected");
    	  sendToServer(twoThingsToSend); 

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
