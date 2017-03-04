import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			// Create server socket
			ServerSocket ss = new ServerSocket(0); // Port chosen by OS
			System.out.println("SERVER RUNNING ON " + ss.getInetAddress() + ":" + ss.getLocalPort());

			// Accept connection(s)
			while(true) {
				Socket s = ss.accept();
				System.out.println("*** Now talking with " + s.getInetAddress() + ":" + s.getPort());
				// IO
				BufferedReader fromCl = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter toCl = new PrintWriter(s.getOutputStream(),true);
				String str = "";
				
				// Talk
				while(true) {
					str = fromCl.readLine();

					// Do not print/send 'null' - Avoid NullPointerException
					if (str == null || str.equals("."))
						break;

					System.out.println(" > " + str);
					toCl.println("Received \"" + str + "\"");
				}

				// Close the connection with current served client
				System.out.println("Connection with " + s.getInetAddress() + ":" + s.getPort() + " closed, next one.");
				s.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}