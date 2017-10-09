package controller_view;
/*Author: Haotian Yuan
 *This is the server part of a multi-client server.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import model.PaintObject;

public class Server {

	private static ServerSocket serverSocket;
	private static List<ObjectOutputStream> outputStreams = new Vector<>();//the list to store all clients
	private static List<PaintObject> list = new Vector<>();//the list to store all paintobjects from the client
	public static void main(String[] args) throws IOException {
		serverSocket = new ServerSocket(4000);
		while (true) {
			Socket socket = serverSocket.accept();
			ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());//get the input from clients
			ObjectOutputStream ouputToClient = new ObjectOutputStream(socket.getOutputStream());//convey output to clients
			outputStreams.add(ouputToClient);
			
			ClientHandler clientHandler = new ClientHandler(inputFromClient);
			Thread thread = new Thread(clientHandler);
			thread.start();

		}

	}
	
	/*
	 * class name:ClientHandler
	 * This class is to deal with clients
	 */
	private static class ClientHandler implements Runnable {

		private ObjectInputStream input;
		
		public ClientHandler(ObjectInputStream input) {
			this.input = input;
			
		}
		
		/*
		 * Method name: run 
		 * Purpose:this method is to read objects from the client, then write these objects all back to clients
		 */
		@Override
		public void run() {
			PaintObject  message = null;	
			writeStringToClients();//once we have a new client, write all objects to that new client firstly
			while (true) {
				//PaintObject  message = null;
				try {
					message = (PaintObject) input.readObject();//read an object, then add to the list
					list.add(message);
					writeStringToClients();
				} catch (IOException ioe) {
				} catch (ClassNotFoundException cnfe) {
				}
				//writeStringToClients(list);
			}
			

		}
		
		/*
		 * Method name:  writeStringToClients()
		 * Purpose:this method is to write the object back to clients
		 */
		private void writeStringToClients() {

			for (int i=0;i<outputStreams.size();i++) {
				try {
					outputStreams.get(i).reset();
					outputStreams.get(i).writeObject(list);
					
				} catch (IOException ioe) {
					
				}
			}
		}
	}

}
