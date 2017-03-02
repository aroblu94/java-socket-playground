import java.lang.InterruptedException;

public class SharedRes {
	private int counter;

	public SharedRes() {
		counter = 0;
	}

	public synchronized void increment() {
		counter++;
	}

	public int get() {
		return counter;
	}

	public synchronized void waitForOther(int id) {
		System.out.println("[T" + id + "] Let's go to sleep...");
		notify();
		try {
			wait();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}