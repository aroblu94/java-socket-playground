import java.io.*;
import java.net.*;

public class Server extends Thread {
	private Socket socket;

	public Server(Socket s) {
		socket = s;
	}

	// Implementing Thread.run()
	public void run() {
		try {
			// Debug connection
			System.out.println("Connected with host " + socket.getInetAddress() + ":" + socket.getPort());

			// IO
			BufferedReader fromCl = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter toCl = new PrintWriter(socket.getOutputStream(),true);
			String str = "";
			
			// Talking with client
			do {
				str = fromCl.readLine();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] > " + str);
				toCl.println("Received '" + str + "'");
			} while(!str.equals("."));

			// Close connection
			socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}