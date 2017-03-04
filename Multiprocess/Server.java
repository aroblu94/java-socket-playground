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
			System.out.println("*** Connected with host " + socket.getInetAddress() + ":" + socket.getPort());

			// IO
			BufferedReader fromCl = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter toCl = new PrintWriter(socket.getOutputStream(),true);
			String str = "";
			
			// Talk
			while(true) {
				str = fromCl.readLine();

				// Do not print/send 'null' - Avoid NullPointerException
				if (str == null || str.equals("."))
					break;

				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] > " + str);
				toCl.println("Received \"" + str + "\"");
			}

			// Close the connection with current served client
			System.out.println("Connection with " + socket.getInetAddress() + ":" + socket.getPort() + " closed.");
			socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}