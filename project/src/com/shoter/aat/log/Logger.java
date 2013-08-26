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
	static Priority current_priority = Priority.INFO;
	
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
				Logger.e("LOGGER", "Setting logger file failed");
				e.printStackTrace();
			}
		}
	}
	
	static void setDefaultLoggerFile()
	{
		setLoggerFile(new File("log.txt"));
	}
	
	boolean canWrite(Priority priority)
	{
		return current_priority.type <= priority.type;
	}
	
	
	static  void log(String tag, String message, boolean writeToFile, Priority priority)
	{
		if(debugFile == null)
			setDefaultLoggerFile();
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		
		String time = dateFormat.format(date);
		
		message =  priority.toString() + "|["+time+"]{" + tag + "} - " + message;
		
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
					Logger.e("LOGGER", "Writing log exception!");
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
		log(tag, message, false, Priority.DEBUG);
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
		log(tag, message, writeToFile, Priority.DEBUG);
	}
	
	/**
	 * Writes info message on the console
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 */
	public static void i(String tag, String message)
	{
		log(tag, message, false, Priority.DEBUG);
	}
	
	/**
	 * Writes info message
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 * @param writeToFile control whether to write to file or not
	 */
	public static void i(String tag, String message, boolean writeToFile)
	{
		log(tag, message, writeToFile, Priority.DEBUG);
	}
	
	/**
	 * Writes error on the console
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 */
	public static void e(String tag, String message)
	{
		log(tag, message, false, Priority.DEBUG);
	}
	
	/**
	 * Writes error message
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 * @param writeToFile control whether to write to file or not
	 */
	public static void e(String tag, String message, boolean writeToFile)
	{
		log(tag, message, writeToFile, Priority.DEBUG);
	}
	
	/**
	 * Writes warning on the console
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 */
	public static void w(String tag, String message)
	{
		log(tag, message, false, Priority.DEBUG);
	}
	
	/**
	 * Writes warning message
	 * 
	 * @param tag tag of the message
	 * @param message message to write
	 * @param writeToFile control whether to write to file or not
	 */
	public static void w(String tag, String message, boolean writeToFile)
	{
		log(tag, message, writeToFile, Priority.DEBUG);
	}
}
