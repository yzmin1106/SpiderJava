package part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
	public static void main(String []  args) throws IOException
	{
		String hostname;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\n");
		System.out.print("host name: ");
		hostname = input.readLine();
		try{
			InetAddress ipAddress = InetAddress.getByName(hostname);
			System.out.println("IP address:"+ ipAddress.getHostAddress());
			
		}catch (UnknownHostException e) {
			System.out.println("Could not find IP address for:" + hostname);
		}
	}

}
