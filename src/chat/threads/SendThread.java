package chat.threads;

import java.util.Scanner;

public class SendThread implements Runnable
{
	private Thread thread;
	
	public SendThread()
	{
		this.thread = new Thread(this);
	}
	
	private String enterMessage()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print(">> ");
		String message = scanner.nextLine();
		return message;
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			System.out.println(enterMessage());
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
