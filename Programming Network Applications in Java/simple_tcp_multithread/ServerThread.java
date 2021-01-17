package simple_tcp_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//this class is going to have to implement Runnable, this could be done in a different manner, but it is going to be Runnable
//Runnable is an interface and is like a template for an object that is intended to be executed by a thread.
//The Runnable defines a single method, run(), and this method is meant to contain the code that is executed by the thread
public class ServerThread implements Runnable{
	
	private Socket socket;//private because we don't want other threads or classes to be able to access a socket in a particular thread
	private ServerMain server_main;
	public ServerThread(Socket socket, ServerMain server_main) {
		this.socket = socket;
		this.server_main = server_main;
	}
	
	//Override because Thread class already has its own run method
	@Override
	public void run() {
		try {
			int client_number = server_main.getClientNumber();
			System.out.println("Client " + client_number + ". has connected.");
			
			BufferedReader in_socket = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
			
			out_socket.println("Welcome! You are client number " + client_number + ". Whats your name? ");
			String message = in_socket.readLine();
			System.out.println("Client says: " + message);
			
			socket.close();
			System.out.println("Client " + client_number + ".");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
