import java.io.*;
import java.net.*;

public class Server extends Thread {
	private Socket socket;
	private SharedRes res;
	private int id;

	public Server(Socket s, SharedRes res, int id) {
		socket = s;
		this.res = res;
		this.id = id;
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

			// If not the first thread, go to sleep
			if(id > 0)
				res.waitForOther(id);
			
			// Talking with client
			do {
				str = fromCl.readLine();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] > " + str);
				toCl.println("Received '" + str + "'");

				// Write on shared res
				res.increment();


				// Access to shared res
				System.out.println("Shared res current value: " + res.get());

				// Pass the baton to other thread
				res.waitForOther(id);
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