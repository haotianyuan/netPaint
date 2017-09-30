package quizzes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// Take Home Quiz: Client/Server
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Connect to a Server and get the two streams from the server
			// System.out.println("Client started");
			Socket server = new Socket("localhost", 4000);
			ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(server.getInputStream());
			String name = "";
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String messageFromClient = (String)input.readObject();
			while(name.toUpperCase().compareTo("QUIT")!=0) {
				System.out.print("Enter a message: ");
				name = reader.readLine();
				
				output.writeObject(name);
				
				System.out.println("Hi client, you wrote: " + name);
				//server.close();
			}
			// Close the connection to the server
			System.out.println("You entered the magic word");
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
