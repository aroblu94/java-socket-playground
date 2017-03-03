import java.io.*;
import java.net.*;

public class Server extends Thread {
	private DatagramPacket tosend;
	private DatagramPacket received;
	private DatagramSocket s;
	private InetAddress cl_ip;
	private int cl_port;

	public Server(DatagramSocket s, DatagramPacket r) {
		this.s = s;
		this.received = r;
	}

	public void run() {
		try {
			// Get client's "full name"
			cl_ip = received.getAddress();
			cl_port = received.getPort();

			// Print received message
			String str = new String(received.getData(), received.getOffset(), received.getLength());
			System.out.println(" > " + str);

			// Send response
			str = "Received '" + str + "'";
			tosend = new DatagramPacket(str.getBytes(), str.length(), cl_ip, cl_port);
			s.send(tosend);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}