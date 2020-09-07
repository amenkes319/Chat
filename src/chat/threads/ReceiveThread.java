package chat.threads;

public class ReceiveThread implements Runnable
{
	private Thread thread;
	
	public ReceiveThread()
	{
		this.thread = new Thread(this);
	}
	
	@Override
	public void run()
	{
		while (true)
		{

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
