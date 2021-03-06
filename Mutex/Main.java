import java.net.*;

public class Main {
	public static void main(String[] args) {
		try {
			// Create shared resource
			SharedRes resource = new SharedRes();

			// Threads counter
			int counter = 0;

			// Create server socket
			ServerSocket ss = new ServerSocket(0); // Port chosen by OS
			System.out.println("Main server running on " + ss.getInetAddress() + ":" + ss.getLocalPort());

			// Accept connection(s)
			while(true) {
				Socket s = ss.accept();

				// Run server on another thread
				Server srv = new Server(s,resource,counter);
				srv.start();

				// Increment threads counter
				counter++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}