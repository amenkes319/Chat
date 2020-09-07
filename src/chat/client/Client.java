package chat.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.threads.ReceiveThread;
import chat.threads.SendThread;

public class Client
{
	public static final Client INSTACE = new Client("127.0.0.1", 5000);
	
	private Socket socket;

	private DataInputStream input; 
	private DataOutputStream output;
	
	private SendThread sendThread;
	private ReceiveThread receiveThread;
	
	public Client(String ip, int port)
	{
		try
		{
//			this.socket = new Socket(ip, port);
			this.input = new DataInputStream(System.in);
//			this.output = new DataOutputStream(socket.getOutputStream());
			
			this.sendThread = new SendThread();
			this.receiveThread = new ReceiveThread();
			
			this.sendThread.start();
			this.receiveThread.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		catch (UnknownHostException e)
//		{
//			e.printStackTrace();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
	}

	public Socket getSocket()
	{
		return socket;
	}
	
	public static void main(String[] args)
	{

	}
}
