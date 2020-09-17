package chat.threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import chat.client.Client;

public class SendThread implements Runnable
{
	private Thread thread;

	private BufferedReader input; 
	private BufferedWriter output;
	
	public SendThread()
	{
		this.thread = new Thread(this);
		this.input = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			Client i = Client.INSTANCE;
			Socket s = i.getSocket();
			OutputStream o = s.getOutputStream();
			this.output = new BufferedWriter(new OutputStreamWriter(o));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			e.
		}
	}
	
	private String enterMessage()
	{
		System.out.print(">> ");
		String message = "";
		try
		{
			message = input.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return message;
	}
	
	private void sendMessage(String message)
	{
		try
		{
			output.write(Client.INSTANCE.getName() + "," + message);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			sendMessage(enterMessage());
		}
	}

	public synchronized void start()
	{
		thread.start();
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
