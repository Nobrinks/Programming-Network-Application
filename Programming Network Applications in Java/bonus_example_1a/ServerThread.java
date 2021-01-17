package bonus_example_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
	
	private Socket socket;
	private ServerMain server_main;
	public ServerThread(Socket socket, ServerMain server_main) {
		this.socket = socket;
		this.server_main = server_main;
	}
	@Override
	public void run() {
		try {
			int client_number = server_main.getClientNumber();
			System.out.println("Client " + client_number + ". has connected.");
			
			BufferedReader in_socket = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
			String message = "";
			while(!(message.equalsIgnoreCase("EXIT"))) {
				out_socket.println("Enter your text: ");
				message = in_socket.readLine();
				message = message.toUpperCase();
				System.out.println("Result: " + message);
			}
						
			socket.close();
			System.out.println("Client " + client_number + ".");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
