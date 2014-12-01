import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ocsf.server.*;
import spy.Mission;
import spy.Operative;
import spy.Resource;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Kevin Johnson 6017605;
 * @author ZhenHao Wu 6911292;
 * @version Dec 2014
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

  private ArrayList<Operative> operatives = new ArrayList<Operative>(500); 
  private ArrayList<Operative> activeOperatives = new ArrayList<Operative>();
  private ArrayList<Mission>  missions = new ArrayList<Mission>();
  private ArrayList<Resource> resources = new ArrayList<Resource>();
  private ArrayList<String> privatePasswords = new ArrayList<String>(); 
  
  
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
  
  
  
  private static char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  
  
  /*
   * Method to create random strings 
   * @param characterSet a set of characters 
   * @param length the length of the random string 
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
   * This method handles any messages received from the client.
   * This method recieves an arraylist of objects from the client 
   * and does certain things depending on the objects recieved.
   *
   * Server responds to the following commands.
   * #Mission - returns missions of current operative. 
   * #CreateMission - creates an mission.
   * #Validate - verifies the identity of the connected operative. 
   * #MissionComplete - deletes completed mission from the server.   
   * #CreateResource - creates new resource.
   * #CreateOperative - creates new operative.
   * #Quit - disconnects client.
   *
   * @param list The message received from the client.
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
			try {
				if (m.getOperative().getCodeName().equals(((Operative) ((ArrayList<Object>) list).get(0)).getCodeName())) {
					m.setMissionPassword(randomString(CHARSET_AZ_09,16));
					thingsToSend.add("MISSION*** Startdate: " +m.getAssignmentDate()+ "\n EndDate: "+ m.getEndDate() + "\n Description: " + m.getDescription() + "\n Mission Password: " + m.getMissionPassword());
					this.sendToAllClients(thingsToSend);
					misFound = true;				  
					break;
				}
			} catch (Exception e) {
			System.out.println(list);
			}

		}
		if (misFound == false) {
			thingsToSend.add("Operative "+ ((Operative) ((ArrayList<Object>) list).get(0)).getCodeName()+" has no missions.");
			this.sendToAllClients(thingsToSend);
		}
	}
				  
			  
	if (((ArrayList<Object>) list).get(1).equals("#CreateMission")) {	  
		Operative temp = null;
		boolean foundOp = false;

		try {
			for (Operative o : operatives){
				if (o.getCodeName().equals(((ArrayList<Object>) list).get(3))){
					temp = o;
					foundOp = true;
				}
			}
		} catch (Exception e){
			System.out.println(e);
			System.out.println("1");
		}

		try {
			if (foundOp){
				this.addMission(((Mission) ((ArrayList<Object>) list).get(2)));
				for (Mission m : missions){
					if (m.equals(((ArrayList<Object>) list).get(2))){
						m.setOperative(temp);
					}
				}
				thingsToSend.add("New mission updated.");
				this.sendToAllClients(thingsToSend);
			} else {
				this.sendToAllClients("Operative not found, mission not added");
			}
		} catch (Exception e) {
		System.out.println(e);
		System.out.println("2");
		}
	}

			  
	if (((ArrayList<Object>) list).get(1).equals("#MissionComplete")) {

		for (Mission m : missions) {
			if (m.getMissionPassword().equals((String) ((ArrayList<Object>) list).get(2))) {
				thingsToSend.add("Mission with mission "+ m.getMissionPassword() + " has been completed.");
				this.sendToAllClients(thingsToSend);
				this.missions.remove(m);
				break;
			}  
		} 
	}

	if (((ArrayList<Object>) list).get(1).equals("#CreateResource")) {
		this.addResource((Resource) ((ArrayList<Object>) list).get(2));
		thingsToSend.add("New resource has been updated.");
		this.sendToAllClients(thingsToSend);
	} 


	if (((ArrayList<Object>) list).get(1).equals("#CreateOperative")) {
		this.addOperative((Operative) ((ArrayList<Object>) list).get(2));
		this.privatePasswords.add(((Operative) ((ArrayList<Object>) list).get(2)).getPrivatePassword());

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
	
	if (((ArrayList<Object>) list).get(1).equals("#Abort")) {
		if (this.privatePasswords.contains(((ArrayList<Object>) list).get(2))){
			this.missions.removeAll(missions);
			this.activeOperatives.removeAll(activeOperatives);
			this.operatives.removeAll(operatives);
			this.resources.removeAll(resources);
			thingsToSend.add("System compromised, information destroyed.");
			this.sendToAllClients(thingsToSend);
		} else {
			thingsToSend.add("Incorrect private password.");
			this.sendToAllClients(thingsToSend);
		}
	}
	
	if (((ArrayList<Object>) list).get(1).equals("#GetResource")) {
		if (this.activeOperatives.contains(((ArrayList<Object>) list).get(0))) {
			thingsToSend.add(this.resources);
			this.sendToAllClients(thingsToSend);
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
    
    /*
     * Create some fake data for server to process.
     */
    
    // Operatives
    
    Operative a = new Operative("steve", "bebe");
    a.setPrivatePassword(randomString(CHARSET_AZ_09,16));
    System.out.println(a.getPrivatePassword());
    sv.privatePasswords.add(a.getPrivatePassword());
    sv.operatives.add(a);
    
    Operative b = new Operative("Hunter", "41591955ht");
    b.setPrivatePassword(randomString(CHARSET_AZ_09,16));
    sv.privatePasswords.add(b.getPrivatePassword());
    sv.operatives.add(b);
    
    Operative c = new Operative("Player", "15619211py");
    c.setPrivatePassword(randomString(CHARSET_AZ_09,16));
    sv.privatePasswords.add(c.getPrivatePassword());
    sv.operatives.add(c);
    
    Operative d = new Operative("Climber", "54815835cm");
    d.setPrivatePassword(randomString(CHARSET_AZ_09,16));
    sv.privatePasswords.add(d.getPrivatePassword());
    sv.operatives.add(d);
    
    Operative e = new Operative("Ascetic", "15858465ac");
    e.setPrivatePassword(randomString(CHARSET_AZ_09,16));
    sv.privatePasswords.add(e.getPrivatePassword());
    sv.operatives.add(e);
    
    // Resources
    
    Resource ra = new Resource("AIRE" ,"Carnac Stones,France" ,"$14M");
    Resource rb = new Resource("Ship" ,"The Devil’s Sea" ,"$54M");
    Resource rc = new Resource("Pharaoh's Scepter" ,"Nile River,Giza" ,"$15M");
    Resource rd = new Resource("Batmobile" ,"Gotham City,US" ,"$8M");
    
    sv.resources.add(ra);
    sv.resources.add(rb);
    sv.resources.add(rc);
    sv.resources.add(rd);
    
    ArrayList<Resource> all = new ArrayList<Resource>();
    all.add(ra);
    all.add(rb);
    all.add(rc);
    
    // Missions 
    
    long now = 1417363849329l; // some time on november 30th 
    
    Mission ma=new Mission(now,now + 432000000,"Meet lorenzo at the Mill Street. \n Take package. \n Deliver package to Tonny at LZ2.");
    ma.setOperative(a);
    ma.setResources(all);
    
    Mission mb=new Mission(now,now + 2*432000000,"kill yourself");
    Mission mc=new Mission(now,now + 3*432000000,"Infiltrate CIBC, steel gold.");
    Mission md=new Mission(now,now + 4*432000000,"Wait for suzan at zanubars.");
    
    
    sv.missions.add(ma);
    sv.missions.add(mb);
    sv.missions.add(mc);
    sv.missions.add(md); 

    
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
