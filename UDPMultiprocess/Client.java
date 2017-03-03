import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			// UDP socket
			DatagramSocket s = new DatagramSocket();
			InetAddress srvaddr = InetAddress.getByName("localhost");
			int port = 12345; // "Well known" port

			// 2 packets needed
			byte[] buffer = new byte[2048];
			DatagramPacket received = new DatagramPacket(buffer, 2048);
			DatagramPacket tosend;

			// IO
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String str = "";

			// talking with server
			do {
				str = in.readLine();

				// Send message
				tosend = new DatagramPacket(str.getBytes(), str.length(), srvaddr, port);
				s.send(tosend);

				// Receive response
				s.receive(received);
				String rstr = new String(received.getData(), received.getOffset(), received.getLength());
				System.out.println(" > " + rstr);
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