import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	private static final int PORT = 8020;
	
	public static ArrayList<Integer> Integer_List() {
		ArrayList<Integer> input = new ArrayList<Integer>();
		
		boolean done = false;
		
		Scanner scan = new Scanner(System.in);
		String s;
		while (! done) {
			System.out.println("Enter an integer (\"!\" to send):");
			s = scan.nextLine();
			if (s.equals("!")) {
				done = true;
			}
			else {
				input.add(Integer.parseInt(s));
			}
		}	
		scan.close();
		return input;
	}

	public static void main(String[] args) {
		
		Socket socket = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;		
		try {
			//InetAddress address = InetAddress.getLocalHost();
			//System.out.println(address.getHostName());
			socket = new Socket("localhost", PORT);
			//socket.connect(new InetSocketAddress(address, PORT));
			
			
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();
			
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			ArrayList<Integer> l;
			l = Integer_List();
			outputStream.writeObject(l);
			outputStream.flush();
			System.out.println("Send: " + l);
			
			try {
				l = (ArrayList<Integer>)inputStream.readObject();
				System.out.println("Receive: " + l);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
					
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {

			try {
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