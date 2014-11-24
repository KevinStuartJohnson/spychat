//this method has been added for receiving message from the server (50(c))
// Kevin Johnson 6107605 
// ZhenHao Wu 6911292

import ocsf.client.*;
import common.*;
import java.io.*;

public class ServerConsole implements ChatIF {
	
	final public static int DEFAULT_PORT = 5555;
	EchoServer test;
	public ServerConsole(EchoServer s){
		test=s;
	}
//Answer for 50(C)
	public void accept() 
	  {
	    try
	    {
	      BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
	      String message;
	      while (true) 
	      {   	 
	        message = fromConsole.readLine();
	        if	(message.charAt(0) == '#')
	        {
	        	if(message.equals("#quit"))
	        	{
	        		display("Server terminated.");
	        		System.exit(0);
	        		}
	        	else if(message.equals("#stop"))
	        	{
	        		test.stopListening();
	        		}
	        	else if(message.equals("#close"))
	        	{
	        		test.close();
	        		}
	        	else if (message.startsWith("#setport"))
	        	{
	        		String temp= message.substring(9);
	        		temp.trim();
	        		int tempInt = Integer.parseInt(temp);
	        		test.setPort(tempInt);
	        		display("Port changed to" + test.getPort());
	        		}
	        	else if(message.equals("#start"))
	        	{
	        		test.listen();
	        		}
	        	else if(message.equals("#getport"))
	        	{
	        		display(" port is " + test.getPort());
	        		}
	        	}
	        else
	        {
	        	test.handleMessageFromServer("Server: " + message);
	        	}
	        }
	      }
	    catch (Exception ex) 
	    {
	        System.out.println
	          ("Expected problem with server.");
	      }
	  }
	 public void display(String message){
		 System.out.println(message);
		 }
	 }
