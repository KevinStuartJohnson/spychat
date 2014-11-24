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
    if (d=false && dcount<1 && pcount<1){
      if (msg instanceof Operative) {
        for(a<length(currOperatives)){
          if(currOperatives<a> == msg){
            Operative client=msg;
            this.sendToAllClients(client.aCodeName + " has been connected");
            d=true;
            dcount++;
          }
          else if(a<(length(currOperatives)-1)){
          a++;
          }
          else {
            for(b<length(privateOperatives)){
              if(privateOperatives<b> == msg){
                Operative client=msg;
                this.sendToAllClients('waring');
                d=true;
                pcount++;
              }
              else if(b<(length(privateOperatives)-1)){
                b++;
              }
              for(c<length(missionOperatives)){
                  if(missionOperatives<c> == msg && mcount<1){
                    client.setInfo("String",msg.aCodeName);
                    System.out.println(client.getInfo("String") + " has completed the mission");
                    setDymaticPassword(msg);
                    int pa = getDymaticPassword(msg);
                    System.out.println("yout new dymatic password will be" + pa);
                    quit();
                  }
                  else if(missionOperatives<c> == msg && mcount>1){
                    System.out.println("warning");
                    quit();
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
          Mission.add(msg);
        }
        else if (msg instanceof String) {
          if (msg.toString().startsWith("#getmission")){
            for(e<length(checklist)){
              if(client.aCodeName==checklist<e>.Operative.aCodeName){
                Mission a=checklist<e>.Mission;
                System.out.println("aAssignment Date"+a.aAssignmentDate + "EndDate", +a.aEndDate + "description" + a.aDescription)
              }
        }  
          }
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

  protected void setDymaticPassword(currOperative P){
    Random rnd = new Random();
    int len=8;
    char[] pswd = new char[len];
    int in = 0;
    for (int i = 0; i < 4; i++) {
      in = getNextIndex(rnd, len, pswd);
      pswd[in] = NUM.charAt(rnd.nextInt(NUM.length()));
    }
    for(int i = 0; i < len; i++) {
      if(pswd[i] == 0) {
        pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
      }
    }
    P.password=pswd;
  }
    protected void setPrivatePassword(privateOperative P){
    Random rnd = new Random();
    int len=8;
    char[] pswd = new char[len];
    int in = 0;
    for (int i = 0; i < 4; i++) {
      in = getNextIndex(rnd, len, pswd);
      pswd[in] = NUM.charAt(rnd.nextInt(NUM.length()));
    }
    for(int i = 0; i < len; i++) {
      if(pswd[i] == 0) {
        pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
      }
    }
    P.password=pswd;
  }
    private static int getNextIndex(Random rnd, int len, char[] pswd) {
    int index = rnd.nextInt(len);
    while(pswd[index = rnd.nextInt(len)] != 0);
    return index;
  }
    public int getDymaticPassword(currOperative P){
      return P.password;
    }
     public int getPrivatePassword(privateOperative P){
      return P.password;
    } 
    public int getMisssionPassword(misssionOperative P){
      return P.password;
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
    int b = 0;
    int c = 0;
    boolean c=false;
    private int dcount=0;
    private int pcount=0;
    private int mcount=0;
    private static final String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM   = "0123456789";


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

    ArrayList<Operative> missionOperatives = new ArrayList();

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
