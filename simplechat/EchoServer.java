import java.io.IOException;
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
public void handleMessageFromClient(Object list, ConnectionToClient client) {
	
	ArrayList<Object> thingsToSend = new ArrayList<Object>();	
	thingsToSend.add(((ArrayList<Operative>) list).get(0));
			
	  
	  System.out.println("Message recieved from " + client + " : " + list);
	 
	  if (((ArrayList<Object>) list).get(1).equals("#Validate")) {
		  boolean opFound = false;
		  for (Operative op : operatives){
			  if (((Operative) ((ArrayList<Object>) list).get(0)).getCodeName().equals(op.getCodeName()) && 
					  ((Operative)  ((ArrayList<Object>) list).get(0)).getDynamicPassword().equals(op.getDynamicPassword()) )  {
				  
				  this.activeOperatives.add((Operative) ((ArrayList<Object>) list).get(0));
				  thingsToSend.add("Operative " + op.getCodeName() + " Has been validated");
				  this.sendToAllClients(thingsToSend);
				  opFound = true;
				  break;
				  
			  } 
		  }
		  if (opFound == false){
			  System.out.print("Operative invalid.");
		  }
	  }
	
		
		  if (((ArrayList<Object>) list).get(1).equals("#Mission")) {
			  
			  boolean misFound = false;
			  for (Mission m : missions){
					  if (m.getOperative().getCodeName().equals(((Operative) ((ArrayList<Object>) list).get(0)).getCodeName())) {
						  thingsToSend.add("MISSION*** Startdate: " +m.getAssignmentDate()+ " EndDate: "+ m.getEndDate() + " Description: " + m.getDescription() + " Mission Password: " + m.getMissionPassword());
						  this.sendToAllClients(thingsToSend);
						  misFound = true;				  
						  break;
					  }
			  }
			  if (misFound == false) {
				  System.out.println("Operative no missions.");
			  }
		  }
			  

		  if (((ArrayList<Object>) list).get(1).equals("#CreateMission")) {
			  this.addMission(((Mission) ((ArrayList<Object>) list).get(2)));
			  thingsToSend.add("New mission updated.");
			  this.sendToAllClients(thingsToSend);
		
		  }

		  
		  if (((ArrayList<Object>) list).get(1).equals("#MissionComplete")) {
			  
			  for (Mission m : missions) {
				  if (m.getMissionPassword().equals((String) ((ArrayList<Object>) list).get(2))) {
					  thingsToSend.add("Mission has been completed and has been deleted from the system.");
					  this.sendToAllClients(thingsToSend);
					  this.missions.remove(m);
					  break;
				 }
			  } 
		  	}
	  
		  if (((ArrayList<Object>) list).get(1).equals("#CreateResource")) {
			  this.addResource((Resource) ((ArrayList<Object>) list).get(3));
			  thingsToSend.add("New resource has been updated.");
			  this.sendToAllClients(thingsToSend);
		  } 
		  
		  	  
		  if (((ArrayList<Object>) list).get(1).equals("#CreateOperative")) {
			  this.addOperative((Operative) ((ArrayList<Object>) list).get(2));
			  
			  for (Operative o : operatives) {
				  if (o.getCodeName().equals(((Operative) ((ArrayList<Object>) list).get(2)).getCodeName())) {
					  o.setSupervises((List<Operative>) ((ArrayList<Object>) list).get(3)); 
					  thingsToSend.add("New operative created.");
					  this.sendToAllClients(thingsToSend);
					  break;
				  }
			  }
		  }
		  
		
		  if (((ArrayList<Object>) list).get(1).equals("#Quit")) {
			  this.activeOperatives.remove((Operative) ((ArrayList<Object>) list).get(0)); 
			  thingsToSend.add("Session ended.");
			  this.sendToAllClients(thingsToSend);
			  try {
				client.close();
			} catch (IOException e) {
				System.out.println("This client cannot be disconected");
				e.printStackTrace();
			}
		  }

  }
  
  public void handleMessageFromServer(Object msg)
  {
	  
	  System.out.println("Message received: " + msg + " (From server)");
	  this.sendToAllClients(msg);
  }
  
  /** 
   * 
   * This method called each time a new client connection is
   * accepted. The default implementation does nothing.
   * @param client the connection connected to the client.
   */
  protected void clientConnected(ConnectionToClient client) {
	  System.out.println("Client " + client + " has connected!");
  }
  
  /*
   * This method called each time a client disconnects.
   * The default implementation does nothing. The method
   * may be overridden by subclasses but should remains synchronized.
   *
   * @param client the connection with the client.
   */
  synchronized protected void clientDisconnected(ConnectionToClient client) {
	  System.out.println("Client " + client + " has disconnected!");  
  }
  
  /** 
   * This method called each time an exception is thrown in a
   * ConnectionToClient thread.
   * The method may be overridden by subclasses but should remains
   * synchronized.
   *
   * @param client the client that raised the exception.
   * @param Throwable the exception thrown.
   */
  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
	  
	  System.out.println("Client " + client.getInfo("String") + " has disconnected!");
	  this.sendToAllClients(client.getInfo("String") + " has disconnected");
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
    
    
    Operative operativea = new Operative("1", "1");
    Operative operativeb = new Operative("Hunter", "41591955ht");
    Operative operativec = new Operative("Player", "15619211py");
    Operative operatived = new Operative("Climber", "54815835cm");
    Operative operativee = new Operative("Ascetic", "15858465ac");
    
    sv.operatives.add(operativea);
    sv.operatives.add(operativeb);
    sv.operatives.add(operativec);
    sv.operatives.add(operatived);
    sv.operatives.add(operativee);
    
    
    Mission missiona=new Mission(31/21/2014,5/1/2015,"kill the morkingbird");
    Mission missionb=new Mission(15/1/2015,25/1/2015,"kill the messenge");
    Mission missionc=new Mission(3/2/2015,15/2/2015,"kill the noise");
    Mission missiond=new Mission(1/3/2014,1/2/2015,"kill the moon");
    
    
    sv.missions.add(missiona);
    sv.missions.get(0).setOperative(sv.operatives.get(0));
    sv.missions.get(0).setMissionPassword("1");
    sv.missions.add(missionb);
    sv.missions.add(missionc);
    sv.missions.add(missiond);
    
    
    Resource resourcea = new Resource("AIRE" ,"Carnac Stones,France" ,"$14M");
    Resource resourceb = new Resource("Ship" ,"The Devil’s Sea" ,"$54M");
    Resource resourcec = new Resource("Pharaoh's Scepter" ,"Nile River,Giza" ,"$15M");
    Resource resourced = new Resource("Batmobile" ,"Gotham City,US" ,"$8M");
    
    sv.resources.add(resourcea);
    sv.resources.add(resourceb);
    sv.resources.add(resourcec);
    sv.resources.add(resourced);    
    
    
    sv.privatePasswords.add("1");
    
    sv.operatives.get(0).setPrivatePassword(sv.privatePasswords.get(0));
    
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
