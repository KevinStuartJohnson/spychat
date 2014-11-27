// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ocsf.server.*;
import spy.Mission;
import spy.Operative;
import spy.Resource;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedfsdfdil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  /**
  * Lists of data to be stored on server.
  */

  ArrayList<Operative> operatives = new ArrayList<Operative>(500); 
  ArrayList<Operative> activeOperatives = new ArrayList<Operative>();
  ArrayList<Mission>  missions = new ArrayList<Mission>();
  ArrayList<Resource> resources = new ArrayList<Resource>();
  ArrayList<String> privatePasswords = new ArrayList<String>(); 
  
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  

  public boolean addOperative(Operative aOperative)
  {
    boolean wasAdded = false;
    if (operatives.contains(aOperative)) { 
    	return false; 
    } else {
  		operatives.add(aOperative);
  	}

    wasAdded = true;
    return wasAdded;
  }
  
  public Operative getOperative(int index)
  {
    Operative aOperative = operatives.get(index);
    return aOperative;
  }

  public List<Operative> getOperatives()
  {
    List<Operative> newOperatives = Collections.unmodifiableList(operatives);
    return newOperatives;
  }

  public int numberOfOperatives()
  {
    int number = operatives.size();
    return number;
  }

  public boolean hasOperatives()
  {
    boolean has = operatives.size() > 0;
    return has;
  }

  public int indexOfOperative(Operative aOperative)
  {
    int index = operatives.indexOf(aOperative);
    return index;
  }
  
  public boolean addMission(Mission aMission)
  {
    boolean wasAdded = false;
    if (missions.contains(aMission)) { 
    	return false; 
    	}
    else
    {
      missions.add(aMission);
    }
    wasAdded = true;
    return wasAdded;
  }

  public Mission getMission(int index)
  {
    Mission aMission = missions.get(index);
    return aMission;
  }

  public List<Mission> getMissions()
  {
    List<Mission> newMissions = Collections.unmodifiableList(missions);
    return newMissions;
  }

  public int numberOfMissions()
  {
    int number = missions.size();
    return number;
  }

  public boolean hasMissions()
  {
    boolean has = missions.size() > 0;
    return has;
  }

  public int indexOfMission(Mission aMission)
  {
    int index = missions.indexOf(aMission);
    return index;
  }
  
  public boolean addResource(Resource aResource)
  {
    boolean wasAdded = false;
    if (resources.contains(aResource)) { 
    	return false; 
    } else
    {
      resources.add(aResource);
    }
    wasAdded = true;
    return wasAdded;
  }

  public Resource getResource(int index)
  {
    Resource aResource = resources.get(index);
    return aResource;
  }

  public List<Resource> getResources()
  {
    List<Resource> newResources = Collections.unmodifiableList(resources);
    return newResources;
  }

  public int numberOfResources()
  {
    int number = resources.size();
    return number;
  }

  public boolean hasResources()
  {
    boolean has = resources.size() > 0;
    return has;
  }

  public int indexOfResource(Resource aResource)
  {
    int index = resources.indexOf(aResource);
    return index;
  }

  public static int minimumNumberOfOperatives()
  {
    return 0;
  }
  

  public static int minimumNumberOfMissions()
  {
    return 0;
  }


  public static int minimumNumberOfResources()
  {
    return 0;
  }
  
  
  @Override
  protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	  System.out.println("Message recieved from " + client + " : " + msg);
  	
  }

  /**
   * This method handles any messages received from the client.
   * #Mission - returns missions of current operative 
   * #CreateMission - Sends mission  with specified operative NOTE ARRAY LIST has 3 objects.
   * #Validate - Sends operative to server to see if Operative is legit 
   * #MissionComplete - Sends mission complete code         
   * #CreateResource - Sends resouirce to be created by server.
   * #CreateOperative - sends and Operative to be created in the server server 
   * Note third element in array list is arraylist of other operatives 
   * #Quit sends operative and disconnect message to server.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  @SuppressWarnings("unchecked")
public void handleMessageFromClient(ArrayList<Object> list, ConnectionToClient client) {
	  
	  System.out.println("Message recieved from " + client + " : " + list);
	  
	  try {	
		  if (list.get(2).equals("#Validate")) { 
			  if (this.operatives.contains(list.get(1))){
				  this.activeOperatives.add((Operative) list.get(1));
			  } else {
				  System.out.println("Operative Invalid.");
				  client.close();
			  }
      }
		  
		
		  if (list.get(2).equals("#Mission")) {
			  
			  
			  for (Mission m : missions){
				  if (m.getOperative().equals(((Operative) list.get(1)))) {
					  System.out.println(m);
				  } else {
				  System.out.println("Operative no missions.");
			  }
			  	  
		  }
			  
		  if (list.get(2).equals("#CreateMission")) {
			  this.addMission((Mission) list.get(3));
		
		  }
		  
		  if (list.get(2).equals("#MissionComplete")) {
			  
			  for (Mission m : missions) {
				  if (m.getMissionPassword().equals((String) list.get(3))) {
					  System.out.println("Mission has been completed");
				 }
			  } 
		  	}
		  
		  
		  if (list.get(2).equals("#CreateResource")) {
			  this.addResource((Resource) list.get(3));
		  } 
		  
		  
		  
		  if (list.get(2).equals("#CreateOperative")) {
			  this.addOperative((Operative) list.get(3));
			  
			  for (Operative o : operatives) {
				  if (o.equals((Operative) list.get(3))) {
					  o.setSupervises((ArrayList<Operative>) list.get(4)); 
				  }
			  }
		  }
		  
		
		  if (list.get(2).equals("#Quit")) {
			  this.activeOperatives.remove(list.get(1));
			  /*
			   * Move operative back to operative list 
			   * End client connection
			   * list(operative,#Quit)
			   */		  
		  	}
		  }
	  	}catch (Exception e) {
		  
		  /*
		   * Type send some thing to client 
		   */
		  
	  }
  }
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    EchoServer sv = new EchoServer(port);
    
    
    Operative operativea = new Operative("Magician", "29491031mg");
    Operative operativeb = new Operative("Hunter", "41591955ht");
    Operative operativec = new Operative("Player", "15619211py");
    Operative operatived = new Operative("Climber", "54815835cm");
    Operative operativee = new Operative("Ascetic", "15858465ac");
    
    sv.operatives.add(operativea);
    sv.operatives.add(operativeb);
    sv.operatives.add(operativec);
    sv.operatives.add(operatived);
    sv.operatives.add(operativee);
    
    System.out.println("bleep");
    
    Mission missiona=new Mission(31/21/2014,5/1/2015,"kill the morkingbird");
    Mission missionb=new Mission(15/1/2015,25/1/2015,"kill the messenge");
    Mission missionc=new Mission(3/2/2015,15/2/2015,"kill the noise");
    Mission missiond=new Mission(1/3/2014,1/2/2015,"kill the moon");
    
    System.out.println("bleep");
    
    sv.missions.add(missiona);
    sv.missions.add(missionb);
    sv.missions.add(missionc);
    sv.missions.add(missiond);
    
    System.out.println("bleep");
    
    Resource resourcea = new Resource("AIRE" ,"Carnac Stones,France" ,"$14M");
    Resource resourceb = new Resource("Ship" ,"The Devil’s Sea" ,"$54M");
    Resource resourcec = new Resource("Pharaoh's Scepter" ,"Nile River,Giza" ,"$15M");
    Resource resourced = new Resource("Batmobile" ,"Gotham City,US" ,"$8M");
    
    System.out.println("bleep");
    
    sv.resources.add(resourcea);
    sv.resources.add(resourceb);
    sv.resources.add(resourcec);
    sv.resources.add(resourced);    
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }



}
//End of EchoServer class
