// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import ocsf.server.*;
import java.util.*;
import spy.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class SecretServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;


  /**
  * Server Lists
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
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
  		(Object msg, ConnectionToClient client)
  {
	
	  int a = 0;
	    int b = 0;
	    int c = 0;
	    int e = 0 ;
	    if (d=false && dcount<1 && pcount<1){
	      if (msg instanceof Operative) {
	        while(a < currOperatives.size()){
	          if(currOperatives.get(a).equals(msg)){
	            //Operative client = msg;
	            this.sendToAllClients(msg.getCodeName() + " has been connected");
	            d=true;
	            dcount++;
	          }
	          else if(a < currOperatives.size()-1){
	          a++;
	          }
	          else {
	            while(b< privateOperatives.size()){
	              if(privateOperatives.get(b).equals(msg)){
	                //Operative client=msg;
	                this.sendToAllClients("waring");
	                d=true;
	                pcount++;
	              }
	              else if(b< privateOperatives.size()-1){
	                b++;
	              }
	              while(c< missionOperatives.size()){
	                  if(missionOperatives.get(c).equals(msg) && mcount<1){
	                    System.out.println(msg.getCodeName() + " has completed the mission");
	                    setDymaticPassword(msg);
	                    int pa = getDymaticPassword(msg);
	                    System.out.println("yout new dymatic password will be" + pa);
	                  }
	                  else if(missionOperatives.get(c).equals(msg) && mcount>1){
	                    System.out.println("warning");
	                  }
	                  else{
	                  c++; 
	                  }}}}}}
	      else 
	      {
	        System.out.println("Server has stopped listening for connections.");
	      }}
	      else{
	        if (msg instanceof Mission) {
	          MissionList.add(msg);
	        }
	        else if (msg instanceof String) {
	          if (msg.toString().startsWith("#getmission")){
	            while(e<MissionList.size()){
	              if(MissionList.get(e).getOperative().getCodeName().equals(client.getCodeName())){
	                Mission a=MissionList.get(e).Mission;
	                System.out.println("aAssignment Date"+a.aAssignmentDate + "EndDate"+a.aEndDate + "description" + a.aDescription);
	              }
	            }  
	          }
	      }
	    }  
	  
    System.out.println("Message received: " + msg + " from " + client);
    this.sendToAllClients(msg);
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
	    currOperatives.add(new Operative("jimmmy","jhfsdh2"));
	    currOperatives.add(new Operative("jimgdfgmmy","4gtegdf"));
	    currOperatives.add(new Operative("jimfgfdgmmy","gdfg4"));
	    currOperatives.add(new Operative("jimfdgmmy","dfgdfgf"));
	    
	    privateOperatives.add(new Operative("jimmmy","5254345"));
	    privateOperatives.add(new Operative("jimgdfgmmy","5435428"));
	    privateOperatives.add(new Operative("jimfgfdgmmy","78637524"));
	    privateOperatives.add(new Operative("jimfdgmmy","57324257"));

	    missionOperatives.add(new Operative("jimmmy","sdfgfdsg"));
	    missionOperatives.add(new Operative("jimgdfgmmy","sdfgfdsg"));
	    missionOperatives.add(new Operative("jimfgfdgmmy","sdfgfds"));
	    missionOperatives.add(new Operative("jimfdgmmy","57gfh7"));
	    try
	    {
	      port = Integer.parseInt(args[0]); //Get port from command line
	    }
	    catch(Throwable t)
	    {
	      port = DEFAULT_PORT; //Set port to 5555
	    }
	 
	    EchoServer sv = new EchoServer(port);
	    
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
