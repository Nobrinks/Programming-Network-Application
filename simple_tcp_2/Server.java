package simple_tcp_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public Server() throws Exception {

		ServerSocket server_socket = new ServerSocket(2020); // opening a new port
		System.out.println("Port 2020 is open.");
		
		Socket socket = server_socket.accept();
		System.out.println("Client "+ socket.getInetAddress()+ " has connected.");//retrieves the client ip address from the socket object

		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream())); //data that is coming int our socket, from the client
		
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		String message;
		int secret_number = (int)(Math.random()*10+1);
		
		do {
			out_socket.println("Guess a number [1 to 10]: ");
			message = in_socket.readLine();//we need to translate this string to an integer, and it's going to be done in the while condition
		} while (!(Integer.parseInt(message)==secret_number));
		
		out_socket.println("You got it!!!");
		System.out.println("Secret number is out. Exiting the app.");
				
		/*out_socket.println("Welcome!"); //send "welcome" to the client
		String message = in_socket.readLine();
		System.out.println("Client says: " + message);*/ //display Client message in the console
		
		socket.close();
		System.out.println("Socket is close.");
		
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
