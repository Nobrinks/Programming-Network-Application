//Due to the package declaration, it takes to go back a directory and run the following command:
//java simple_tcp.Server. Or just take the package out and run normally:
//javac Server.java -> java Server
package simple_tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public Server() throws Exception {
		//Server Side
		//to import, ctrl + shift + O
		//firstly there's an error because we need to import a package that contains the ServerSocket method, which is java.net
		//the second error is because we need to throw an exception
		ServerSocket server_socket = new ServerSocket(2020); // opening a new port
		System.out.println("Port 2020 is open.");
		//now we are ready to open this regular communication socket, using the socket method for it
		//accept is a blocking method tha accepts incoming connections
		//a "blocking method" means that java is going to go through our lines of code and then is going to stop at this line
		//and wait for an incoming connection to come up and it's not going to continue to go through the other lines until an incoming 
		//connection actually appears and it is accepted
		Socket socket = server_socket.accept();
		System.out.println("Client "+ socket.getInetAddress()+ " has connected.");//retrieves the client ip address from the socket object
		//I/O buffers:
		// a place to store our data that is coming out of our socket and the data that is coming into our socket
		//for input
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream())); //data that is coming int our socket, from the client
		//For out outgoing buffer, we're going to use PrintWriter
		//OutputStreamWriter, here we're actuallly writing the data into the buffer
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		
		//From this moment on, we're deciding what kind of communication we're going to have between our server and client
		//as a server we'll send a 'welcome'
		
		out_socket.println("Welcome!"); //send "welcome" to the client
		String message = in_socket.readLine();
		System.out.println("Client says: " + message);//display Client message in the console
		//after they're done communicating, we need to close the socket
		socket.close();
		System.out.println("Socket is close.");// the purpose of this notifications is to know when we have passed a certain line of code
		
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
//6. thorough explanation of the code for Simple TCP Program
//Part 1 Server
//The entry point to any application is its main method, when running a program it'll look for the main method
//Creating a ServerSocket means that we're going to reserve, or to open a certain port, that we're going to use for this application
//Then we create a regular socket (Socket object) that we're going to use to communicate and it can be used as soon as we have an 
//incoming connection that we can accept. The line that contains: server_socket.accept(); it'll block the rest of the program until an
//incoming connection shows up.
//The Socket object holds all the important information that's relevant to our connection.

//Incoming and outgoing stream of data, the in_socket gets the incoming data that is coming from the client to the server and the out_socket
//is for the outgoing data which are all the messages that the server sends to the client .
//getInputStream, we're looking at the same socket, for incoming and outgoing data. A socket is like a door so you can come in and can get out
//Using this command, we're selecting the data that we want to look at. This data is going to come in like a gibberish(something messed up)
//That's why we need InputStreamReader to switch that unreadable data into something a user can understand
//BufferedReader we need to put all of that data into a buffer.
//the "true" in PrintWriter turns on the the option of flushing. So this says to the socket that doesn't matter if the buffer is full or not, just flush the message
//There are tcp packets that have PSH flag on, the 'push' flag
