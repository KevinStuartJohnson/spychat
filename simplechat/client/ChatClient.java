// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import spy.Mission;
import spy.Operative;
import spy.Resource;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Kevin Johnson 6017605;
 * @author ZhenHao Wu 6911292;
 * @version Dec 2014
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
  
  public ChatClient(String host, int port, ChatIF clientUI,Operative operative) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.operative = operative;
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
public void handleMessageFromServer(Object msg){ 
	
	if (this.operative.getCodeName().equals(((Operative) ((ArrayList<Object>) msg).get(0)).getCodeName())) {
		System.out.println(((ArrayList<Object>) msg).get(1));
	}
  }
  
  
  /*
   * Alphabet in characters 
   */
  private static char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  
  
  /*
   * Method to create random strings 
   * @Param array of characters 
   * @Param int the length of the random string 
   */
  public static String randomString(char[] characterSet, int length) {
	    Random random = new SecureRandom();
	    char[] result = new char[length];
	    for (int i = 0; i < result.length; i++) {
	        // picks a random index out of character set > random character
	        int randomCharIndex = random.nextInt(characterSet.length);
	        result[i] = characterSet[randomCharIndex];
	    }
	    return new String(result);
	}


  /**
   * This method handles all data coming from the UI.
   * Possible client commands are;
   * Server responds to the following commands.
   * #Mission - returns missions of current operative. 
   * #CreateMission - creates an mission.
   * #Validate - verifies the identity of the connected operative. 
   * #MissionComplete - deletes completed mission from the server.   
   * #CreateResource - creates new resource.
   * #CreateOperative - creates new operative.
   * #Abort - wipes all data from the server. 
   * #Quit - disconnects client.
   * 
   * @param message The message from the UI.    
   * @param operative who is using the chat.
 * @throws IOException 
   */
  public void handleMessageFromClientUI(String msg, Operative operative) throws IOException
  {
	
	ArrayList<Object> thingsToSend = new ArrayList<Object>(); // An arrayList with multiple objects, the first is always the operative
	thingsToSend.add(operative);
	thingsToSend.add(msg);
	
    BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));  
    
    try {
    	
    	
    	if (msg.equals("#Validate")) {
       	  sendToServer(thingsToSend);// sends (Operative,#Validate)
         }
	      if (msg.equals("#Mission")){
	    	 sendToServer(thingsToSend); // sends (operative,"#Mission")
	    	 
	    	 
	      }
	      
	      if (msg.equals("#CreateMission")){
	    	
	    	long endDate = System.currentTimeMillis() + 432000000; // Current time + 5 days 
	    	
	    	System.out.println("Enter completion date."); 
	    	
	    	try {
	    		endDate = Long.parseLong(fromConsole.readLine());
	    	}catch(Exception e){
	    		System.out.println("Mission end date must be a date, 5 day default used.");
	    	}
	    	System.out.println("Enter description.");
	    	String description = fromConsole.readLine();
	    	
	    	System.out.println("Enter operative code name you want to do the mission.");
	    	
	    	String codeName = null;
	    	try {
	    		codeName = fromConsole.readLine();
	    	} catch(Exception e){
	    		System.out.println("Invalid entry.");
	    	}
	    	
	    	thingsToSend.add(new Mission(System.currentTimeMillis(),endDate,description)); // Startdate is always current time
	    	thingsToSend.add(codeName);
	    	sendToServer(thingsToSend);  // sends (operative,#CreateMission,Mission,CodeName)
	      }
	      
	      if (msg.equals("#MissionComplete")){
	    	  
	    	  System.out.println("Enter mission complete password.");
	    	  
	    	  String missionPassword = null;
	    	  try {
	    		  missionPassword = fromConsole.readLine();
	    	  } catch (Exception e){
	    		  System.out.println("Mission password of invalid type. Mission not completed.");
	    	  }
	    	  
	    	  thingsToSend.add(missionPassword);
	    	  sendToServer(thingsToSend); // Sends (operative,#MissionComplete,missionPassword) 
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
	    	  
	    	  thingsToSend.add(new Resource(resourceName,resourceLocation,resourcePrice));
	    	  
	    	  sendToServer(thingsToSend); // Sends (operative, #CreateResource, resource)
	    	  
	      }
	      
	      if (msg.equals("#CreateOperative")){
	    	  
	    	  System.out.println("Enter Operative codeName.");
	    	  
	    	  String codeName = null;
	    	  try {
	    		  codeName = fromConsole.readLine(); 
	    	  } catch (Exception e) {
	    		  System.out.println("Stupid code name. No Operative created.");
	    	  }
	    	  
	    	  System.out.println("Enter Operative password.");
	    	  
	    	  String pWord = randomString(CHARSET_AZ_09,16);
	    	  String privWord = null;
	    	  privWord = randomString(CHARSET_AZ_09,16);
	    	  
	    	  
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
	    	  
	    	  Operative operativeToSend = new Operative(codeName,pWord);
	    	  operativeToSend.setPrivatePassword(privWord);
	    	  
	    	  thingsToSend.add(operativeToSend);
	    	  thingsToSend.add(subordinates);
	    	  
	    	  sendToServer(thingsToSend); // Sends (operative, #CreateOperative, Operative, Subordinates)
	    	  
	    	  System.out.println("Operative with private password " + operativeToSend.getPrivatePassword() + " and dynamic password " + operativeToSend.getDynamicPassword() + " Send to server");
	      }
	      
	      if (msg.equals("#Abort")) {
	    	  System.out.println("Enter private password.");
	    	  String ppWord = fromConsole.readLine();
	    	  thingsToSend.add(ppWord);
	    	  sendToServer(thingsToSend);
	      }
	      
	      if (msg.equals("#GetResource")) {
	    	  String ppWord = fromConsole.readLine();
	    	  thingsToSend.add(ppWord);
	    	  sendToServer(thingsToSend);
	      }
	      
	      
	      if (msg.equals("#Quit")){ 
	    	  sendToServer(thingsToSend);
	      }
      
    }
    catch(IOException e)
    {
    	System.out.println(e);
      clientUI.display
        ("Could not boggads send message to server.");
    }

  }
  
 public void connectionException(Exception e)
 {
	clientUI.display("Client has been disconected.");
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
