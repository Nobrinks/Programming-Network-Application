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
		System.out.println("Port 2020 is noow opened.");
		
		//infinite while loop: wait for new connections
		while(true) { //it will create a new thread for every new client that shows up
			//we get the socket object when we accept a connection coming into the server socket
			Socket socket = server_socket.accept();
			ServerThread server_thread = new ServerThread(socket);
			Thread thread = new Thread(server_thread);
			thread.start(); // whenever we run into this line, we're going to call a run method in the ServerThread class
		}
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
