import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			// Establish connection
			Socket s = new Socket();
			InetAddress ia = InetAddress.getLocalHost();
			InetSocketAddress isa = new InetSocketAddress(ia, Integer.parseInt(args[0]));
			s.connect(isa);
			System.out.println("Connection established with server " + s.getInetAddress() + ":" + s.getPort());

			// IO
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader fromSrv = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter toSrv = new PrintWriter(s.getOutputStream(),true);
			String str = "";

			// Talking with server
			do {
				str = in.readLine();
				toSrv.println(str);
				System.out.println(" > " + fromSrv.readLine());
			} while(!str.equals("."));

			// Close the connection
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}