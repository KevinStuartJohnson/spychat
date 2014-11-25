import ocsf.server.*;

import java.util.*;

import spy.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 */
public class SecretServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;


  /**
  * Lists of data to be stored on server.
  */

  ArrayList<Operative> operatives = new ArrayList();
  ArrayList<Operative> activeOperatives = new ArrayList();
  ArrayList<Mission>  missions = new ArrayList();
  ArrayList<Resource> resources = new ArrayList();
  ArrayList<String> privatePasswords = new ArrayList();
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public SecretServer(int port) 
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
  	// TODO Auto-generated method stub
  	
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
	  try {	
		  if (list.get(2).equals("#Validate")) { 
			  if (this.operatives.contains(list.get(1))){
				  this.activeOperatives.add((Operative) list.get(1));
			  } else {
				  System.out.println("i don't know you, sorry");
				  client.close();
			  }}
			  /*
			   * Check to see if operative is in operatives list.
			   * If they are, move them to activeOperatives list.
			   * If not, disconnect client.
			   * list(operative(codename,password))
			   */
		  if (list.get(2).equals("#Mission")) {
			  if(this.missions.get(1).equals(list.get(1))){
				  System.out.println("mission is " + list.get(2));
			  }
			  else{
				  System.out.println("i don't understand the question,");
				  System.out.println("Counld i have more information?");
			  }			  
			  /*
			   * We can assume that the operative is valide 
			   * so all we have to do is look for that operative in 
			   * mission list and return their mission
			   * list(operative,#Mission)
			   */  
		  }
		  if (list.get(2).equals("#CreateMission")) {
			  this.addMission((Mission) list.get(3));
			  /*
			   * Add a new mission to the mission list with the info 
			   * int the list 
			   * list(operative,#CreateMission,mission(aAssignmentDate,aEndDate,aDescription))
			   */  
		  }
		  
		  if (list.get(2).equals("#MissionComplete")) {
			  if(this.missions.get(3).equals(list.get(3))){
				  System.out.println("succes");
				  this.missions.clear();
			  }
			  else{
				  System.out.println("i don't understand the question,");
				  System.out.println("Counld i have more information?");
			  }
			  /*
			   * Search mission list for mission with mission complete 
			   * password. If found, tell the user success and delete that 
			   * mission from the mission list
			   * list(operative,#MissionComplete,missionpassword)
			   */
		  } 
		  if (list.get(2).equals("#CreateResource")) {
			  if(!this.resources.add((Resource) list.get(3))){
				  this.resources.add((int) list.get(3),(Resource)list.get(4));
			  }
			  else{
				  System.out.println("we already have this resource");  
			  }
			  /*
			   * add a new reseource to the resource list with info from the list
			   * list(operative,#CreateResource,(int) position, nameofresource)
			   */
			  
		  }
		  
		  if (list.get(2).equals("#CreateOperative")) {
			  if(!this.operatives.add((Operative) list.get(3))){
				  this.operatives.add((int) list.get(3),(Operative)list.get(4));
			  }
			  else{
				  System.out.println("Unkonw method"); 
			  }
			  /*
			   * Add a new operative with info from the list 
			   * NOTE that the arraylist has an arraylist in it with all the 
			   * subordinates for the new operative to be created 
			   * list(operative,#CreateOperative,position(int), operative(codename))
			   */
		  }
		  if (list.get(2).equals("#Quit")) {
			  this.activeOperatives.remove(list.get(1));
			  /*
			   * Move operative back to operative list 
			   * End client connection
			   * list(operative,#Quit)
			   */		  
		  }
		  
		  
		  
		  
	  } catch (Exception e) {
		  
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
	 
	    SecretServer sv = new SecretServer(port);
	    
	    /*
	     *  Here we will create some fake lists of operative and 
	     *  stuff.  
	     */
	    
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
