package com.capstone.eLearning.exception;


public class DaoException extends RuntimeException {
  /**
	 * Deepthi
=======
public class DaoException extends RuntimeException {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  public static int         ROW_NOT_FOUND    = 1;
  int                       code             = 0;
  
  public DaoException(Exception e)
  {
    super(e);
  }
  
  public DaoException(String message)
  {
    super(message);
  }
  
  public DaoException(String message, int _code)
  {
    super(message);
    code = _code;
  }
  
  public DaoException(String message, Throwable throwable, int _code)
  {
    super(message, throwable);
    code = _code;
  }
  
  public DaoException(String message, Throwable throwable)
  {
   super(ExceptionHandler.initMessage(message, throwable), throwable);

  }
  
  public int getCode() {
    return code;
  }
}
