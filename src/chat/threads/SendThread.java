package chat.threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
			this.output = new BufferedWriter(new OutputStreamWriter(Client.INSTACE.getSocket().getOutputStream()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
			output.write(message);
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
