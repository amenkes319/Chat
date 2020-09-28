package chat.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.threads.ReceiveThread;
import chat.threads.SendThread;

public class Client
{
	private final Client INSTANCE = this;
	private static Socket socket;
	
	private SendThread sendThread;
	private ReceiveThread receiveThread;
	
	private static String name;
	
	public Client()
	{
		try
		{
			String ip = getIP();
			int port = getPort();
			
			socket = new Socket(ip, port);

			this.sendThread = new SendThread(INSTANCE);
			this.receiveThread = new ReceiveThread(INSTANCE);
			
			this.sendThread.start();
			this.receiveThread.start();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static String getIP() throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		do
		{
			System.out.print("Enter your name (no commas): ");
			name = input.readLine();
		} while (name.contains(","));
		
		System.out.print("Enter the IP to connect to: ");
		return input.readLine();
	}
	
	private static int getPort() throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		boolean bPort = true;
		
		do
		{
			try
			{
				System.out.print("Enter the port to connect to: ");
				return Integer.parseInt(input.readLine());
			}
			catch (NumberFormatException e)
			{
				bPort = false;
			}
		} while (!bPort);
		
		return -1;
	}

	public static Socket getSocket()
	{
		return socket;
	}

	public static String getName()
	{
		return name;
	}
	
	public static void main(String[] args)
	{
		new Client();
	}
}
