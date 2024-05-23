package src.metier;

import java.util.concurrent.TimeUnit;

public class Timer implements Runnable
{

	private volatile boolean quit = false;
	private int seconds = 0;
	public void run()
	{
		while (!quit)
		{
			try
			{
				/* 
				System.out.print("\033[H\033[2J");
				System.out.flush()
				;*/
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			seconds++;
		}
	}
	public int getSeconds()
	{
		return seconds;
	}

	public void stop()
	{
		quit = true;
	}
}