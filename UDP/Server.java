import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			// New UDP socket on a "well known" port
			DatagramSocket s = new DatagramSocket(12345);
			System.out.println("Server running on " + s.getLocalAddress() + ":" + s.getLocalPort());

			// 2 packets needed
			byte[] buffer = new byte[2048];
			DatagramPacket received = new DatagramPacket(buffer, 2048);
			DatagramPacket tosend;

			// Talking whith client
			while(true) {
				// Receive a message
				s.receive(received);
				String str = new String(received.getData(), received.getOffset(), received.getLength());
				System.out.println(" > " + str);

				// Get sender's IP:port
				InetAddress ip = received.getAddress();
				int port = received.getPort();

				// Send response
				str = "Received '" + str + "'";
				tosend = new DatagramPacket(str.getBytes(), str.length(), ip, port);
				s.send(tosend);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}