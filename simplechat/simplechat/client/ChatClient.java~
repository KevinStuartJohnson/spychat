// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;
import java.io.*;

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
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(Object msg)
  {

    if (msg.equalsIgnoreCase("login")) {

    }




    if  (msg.charAt(0)== '#')
    { 
    if (msg.equalsIgnoreCase("#quit"))
    {
      clientUI.display("Client has ended session");
      quit();
      }
    else if (msg.equalsIgnoreCase("#logoff"))
    {
      clientUI.display("Client has logged off");
      try 
      {
        closeConnection();
        } 
      catch (IOException e) 
      {   
        e.printStackTrace();
        }
      }
    else if (msg.startsWith("#sethost"))
    {
      String t = msg.substring(8);
      t.trim();
      setHost(t);
      clientUI.display("Host has been set to " + getHost());
      }
    else if (msg.startsWith("#setport"))
    {
      String t= msg.substring(8);
      t.trim();
      int tInt = Integer.parseInt(t);
      setPort(tInt);
      clientUI.display("Port has been changed to " + getPort());
      }
    else if (msg.equalsIgnoreCase("#login"))
    {
      if (isConnected())
      {
      clientUI.display("Client has already Connected");
      }
      else
        try
      {
          openConnection();
          sendToServer("#login " + loginID);
          }
      catch(IOException e)
      {
      e.printStackTrace();
      }
      }
    else if (msg.equalsIgnoreCase("#gethost"))
    {
      clientUI.display(getHost());
      }
    else if (msg.equalsIgnoreCase("#getport"))
    {
      clientUI.display(Integer.toString(getPort()));  
      }
    else 
    {
      clientUI.display("Server does not recognize command.");     
      }
    }
    else
      try
    {
      sendToServer(msg);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send msg to server.  Terminating client.");
      quit();
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

  /**
   * Get Objects from input stream. 
   */
  private Object getInput() {
    return input.readObject();
  }
}

//End of ChatClient class
