// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import ocsf.server.*;
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
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
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
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient(Object msg)
  {
    if (c=false){
      if (msg instanceof Operative) {
        for(a<length(currOperatives)){
          if(currOperatives<a> == msg){
            client.setInfo("String",msg.aCodeName);
            this.sendToAllClients(client.getInfo("String") + " has been connected");
            c=true;
          }
          else if(a<(length(currOperatives)-1)){
          a++;
          }
          else {
            for(b<length(privateOperatives)){
              if(privateOperatives<b> == msg){
                client.setInfo("String",msg.aCodeName);
                this.sendToAllClients(client.getInfo("String") + " has been connected");
                c=true;
              }
              else
              {
                b++;
              }
            }
      else 
      {
        System.out.println("Server has stopped listening for connections.");
      }}}}}
      else{
        if (msg instanceof Mission) {
          Mission.add(msg);
        }
        if (msg instanceof String) {
      if (msg.equals("#getmission")){
        for(a<length(currOperatives)){
          if(currOperatives<a> == msg){
            client.setInfo("String",msg.aCodeName);
            this.sendToAllClients(client.getInfo("String") + " has been connected");
            c=true;
          }
          else if(a<(length(currOperatives)-1)){
          a++;
          }
      }
    }
    if(A.a)
    {
      A t = msg.toString();
      String login = t.subString(6);
      String password=login.subString(8);
      if(login==login.setID() && password==login.getpassword()){
      logn.trim();
      client.setInfo("String",logn);
      this.sendToAllClients(client.getInfo("String") + " has been connected");
      }
    }
    System.out.println("Message received: " + msg + " from " + client);
    this.sendToAllClients(msg);
  }
    else
    {
      System.out.println("Message received: " + msg + " from " + client.getInfo("String"));
      this.sendToAllClients(client.getInfo("String") + ": " + msg);
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
    int a = 0;
    int b= 0;
    boolean c=false;

    ArrayList<Operative> currOperatives = new ArrayList();

    currOperatives.add(new Operative("jimmmy","jhfsdh2"));
    currOperatives.add(new Operative("jimgdfgmmy","4gtegdf"));
    currOperatives.add(new Operative("jimfgfdgmmy","gdfg4"));
    currOperatives.add(new Operative("jimfdgmmy","dfgdfgf"));


    ArrayList<Operative> privateOperatives = new ArrayList();

    privateOperatives.add(new Operative("jimmmy","5254345"));
    privateOperatives.add(new Operative("jimgdfgmmy","5435428"));
    privateOperatives.add(new Operative("jimfgfdgmmy","78637524"));
    privateOperatives.add(new Operative("jimfdgmmy","57324257"));

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
