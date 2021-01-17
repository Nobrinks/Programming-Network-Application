package simple_tcp_multithread;

import java.net.ServerSocket;
import java.net.Socket;

//In our first example we could only run one client at a time. In this example the goal is to enable several clients to talk to the server in parallel, using threads.
//A thread is just going to be an instance of the same application, same code, operations, methods, just run them separately for each new client, on the server 
//we're going to have one main class with the main method, firstly opening the server socket, then run an infinite loop, where for each incoming connection, we're 
//going to open a new thread. So whenever a new connection comes to the server, we create a regular socket by accepting that connection and then create athread for 
//that particular connection. 
//The code in the Client is not going to change, only the server, enabling it to accept several connections at a time.

public class ServerMain {

	public ServerMain() throws Exception {
		ServerSocket server_socket = new ServerSocket(2020);
		System.out.println("Port 2020 is now opened.");
		
		//infinite while loop: wait for new connections
		while(true) { //it will create a new thread for every new client that shows up
			//we get the socket object when we accept a connection coming into the server socket
			Socket socket = server_socket.accept();
			ServerThread server_thread = new ServerThread(socket, this);//(*)
			Thread thread = new Thread(server_thread);
			thread.start(); // whenever we run into this line, we're going to call a run method in the ServerThread class
		}
	}
	
	//10. Advancing the multithreaded TCP program
	//adding a counter of threads
	private int client_number = 1;
	//In order to later be able to access  this method from our server thread class, we're going to use a ServerMain object. 
	//We need to forward this ServerMain class to our ServerThread constructor. It's enough to type, "this". It pretty much forwards 
	//everything that we have in this class. So, we're going to be able to use this method from the ServerThread class(*)
	public int getClientNumber() {
		return client_number++;
	}
	
	public static void main(String[] args) {
		try {
			new ServerMain();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
