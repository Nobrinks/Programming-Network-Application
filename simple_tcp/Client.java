package simple_tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public Client() throws Exception{
		
		//We need to create a socket, and this time we only have one type of socket.
		//On the server side, we had two types of sockets: the server socket and then the regular communication socket
		//On the Client side we're only going to have a regular communication socket
		//we're going to forward two arguments to this method, the ip address of the server and the listening port
		//so the port number where our server is expecting our connection
		Socket socket = new Socket("192.168.1.70", 2020);//we're using localhost since the server is on this machine, and 2020 that is our port number
		System.out.println("Successful connection to the server.");
		//I/O Buffers: and they're exactly the same as on the server side
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		//These are the basic requirements for our client-server communication, and now we need to worry about the protocol
		//A protocol is pretty much just a set of rules by which communication between two or more sides occurs, so we need to decide who
		//is the first to send a message, what kind of message, what kind of responses, for now we're going to use a simple protocol
		//The client is going to connect to the server, the server is going to say "welcome", the client is going to say "thanks" and then
		//they're going to disconnect
		
		String message = in_socket.readLine();
		System.out.println("Server says: " + message);
		out_socket.println("Thanks");
		socket.close();
		System.out.println("Socket closed.");
		//In order to switch between console messages for server and client, you need to press "display selected console"
		
	}
	
	public static void main(String[] args) {
		try {
			new Client();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
//6. 
//Client Side
//All we need is the Regular Socket on the client side

//8. Bonus. Wireshark Analysis
//What the communication between our server and client looks like in Wireshark
//A modification, instead of connecting to the loopback address, we're goint to connect to the network address of this host. To see 
//what that address is, go to prompt and type: ipconfig, at Ethernet Adapater Ethernet, IPv4 Address
//In Wireshark software, select the Npcap Loopback Adapter option, and then run the Server and after the Client. When we open Wireshark, we can 
//see our results.
//In the first few packet we can see the typpical tcpconnection being established. They are called the three way handshake, SYNpacket, then SYN acknowledgement
// and then another acknowledgement

