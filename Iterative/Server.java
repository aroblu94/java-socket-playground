import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			// Create server socket
			ServerSocket ss = new ServerSocket(0); // Port chosen by OS
			System.out.println("Server running on " + ss.getInetAddress() + ":" + ss.getLocalPort());

			// Accept connection(s)
			while(true) {
				Socket s = ss.accept();
				System.out.println("Now talking with " + s.getInetAddress() + ":" + s.getPort());
				// IO
				BufferedReader fromCl = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter toCl = new PrintWriter(s.getOutputStream(),true);
				String str = "";

				// Talk
				do {
					str = fromCl.readLine();
					System.out.println(" > " + str);
					toCl.println("Received '" + str + "'");
				} while(!str.equals("."));

				// Close the connection with current served client
				s.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}