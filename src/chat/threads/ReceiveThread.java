package chat.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chat.client.Client;

public class ReceiveThread implements Runnable
{
	private Thread thread;
	
	private BufferedReader input;
	
	public ReceiveThread()
	{
		this.thread = new Thread(this);
		
		try
		{
			input = new BufferedReader(new InputStreamReader(Client.INSTACE.getSocket().getInputStream()));
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
			try
			{
				System.out.println(input.readLine());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
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
