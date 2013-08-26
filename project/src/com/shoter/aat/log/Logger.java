package com.shoter.aat.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
{
	
	static File debugFile;
	static PrintWriter writer;
	
	public static void setLoggerFile(File file)
	{
		debugFile = file;
		if(!debugFile.exists())
		{
			debugFile.getAbsoluteFile().getParentFile().mkdirs();
			try
			{
				debugFile.createNewFile();
			} catch (IOException e)
			{
				d("LOGGER", "Setting logger file failed");
				e.printStackTrace();
			}
		}
	}
	
	static void setDefaultLoggerFile()
	{
		setLoggerFile(new File("log.txt"));
	}
	
	/**
	 * Writes debug message
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 * @param writeToFile control whether to write to file or not
	 */
	public static void d(String tag, String message, boolean writeToFile)
	{
		if(debugFile == null)
			setDefaultLoggerFile();
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		
		String time = dateFormat.format(date);
		
		message = "["+time+"]{" + tag + "} - " + message;
		
		System.out.println(message);
		if(writeToFile)
		{
				try
				{
					writer = new PrintWriter(new FileWriter(debugFile.getAbsolutePath(), true));
					writer.println(message);
					writer.close();
				} catch (IOException e)
				{
					d("LOGGER", "Writing log exception!");
					e.printStackTrace();
				}

			
			
		}
	}
	
	/**
	 * Writes debug message on the console
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 */
	public static void d(String tag, String message)
	{
		d(tag, message, false);
	}
}
