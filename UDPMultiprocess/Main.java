import java.net.*;

public class Main {
	public static void main(String[] args) {
		try {
			// New datagram socket on "well known" port
			DatagramSocket s = new DatagramSocket(12345);
			System.out.println("Server running on " + s.getLocalAddress() + ":" + s.getLocalPort());

				byte[] buffer = new byte[2048];
			// Receive packet(s)
			while(true) {
				// Datagram packet
				DatagramPacket received = new DatagramPacket(buffer, 2048);
				s.receive(received);

				// Start a new thread
				Server srv = new Server(s, received);
				srv.start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}