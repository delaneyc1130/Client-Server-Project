import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private static final int PORT = 8020;
	
	public static ArrayList<Integer> Prime_List(ArrayList<Integer> p) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i: p) {
			boolean val = true;
			for (int j = 2; j<i; j++) {
				if (i%j == 0) {
					val = false;
				}
				
			}
			if (val == true) {
				primes.add(i);
			}
		}
	return primes;
	}
	
	public static void main(String[] args) {		
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;		
		try {
			serverSocket = new ServerSocket(PORT);
			//InetAddress address = InetAddress.getLocalHost();
			//System.out.println(address.getHostName());
			//serverSocket.bind(new InetSocketAddress(address, PORT));
			
			socket = serverSocket.accept();
			
			
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();
			
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			try {
				ArrayList<Integer> p = (ArrayList<Integer>) inputStream.readObject();
				ArrayList<Integer> primes = Prime_List(p);
				outputStream.writeObject(primes);
				outputStream.flush();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {

			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
