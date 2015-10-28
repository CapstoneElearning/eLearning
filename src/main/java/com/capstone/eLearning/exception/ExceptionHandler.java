package com.capstone.eLearning.exception;



import java.io.PrintWriter;
import java.io.StringWriter;



public class ExceptionHandler {
	  /**
	   * Deepthi
	   */


	private static String getFormattedErrorMessage(Exception e)
	{
		return e.getMessage();
	}

	public static String getPrintStackTrace(Exception e)
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	public static String initMessage(String message, Throwable throwable)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(message);
		buffer.append("\n");
		buffer.append(throwable.getMessage());
		return buffer.toString();
	}
}
