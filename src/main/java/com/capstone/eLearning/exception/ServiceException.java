package com.capstone.eLearning.exception;


public class ServiceException extends RuntimeException {
  
  /**
   * Deepthi
   */
  private static final long serialVersionUID = 1L;
  
  public ServiceException(String message)
  {
    super(message);
  }
  
  public ServiceException(String message, Throwable throwable)
  {
    super(ExceptionHandler.initMessage(message, throwable), throwable);
  }
  
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append(this.getClass().getName());
    buffer.append(" : ");
    buffer.append(this.getMessage());
    
    return buffer.toString();
  }
}
