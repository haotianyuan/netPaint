package quizzes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Take Home Quiz: Client/Server
public class Server {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
	  try {
		
		
		  ServerSocket server = new ServerSocket(4000);
		  Socket connection = server.accept();
		  String messageFromServer=" ";
		
		  ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
		  ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
		  // Do some IO.
		
		  while(messageFromServer.toUpperCase().compareTo("QUIT")!=0) {
		  output.writeObject(messageFromServer);
		  messageFromServer=(String) input.readObject();
		 
		  System.out.print("Server read: ");
		  System.out.println(messageFromServer);
		  }
		  // Close the connection
		  connection.close();
		  } catch (IOException e) {
		  e.printStackTrace();
		  } catch (ClassNotFoundException e) {
		  e.printStackTrace(); }
  }
}