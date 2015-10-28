package com.capstone.eLearning.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.capstone.eLearning.services.MailService;
import com.capstone.eLearning.services.UserService;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	PrintWriter p;
	private Logger logger = Logger.getLogger(ForgotPasswordServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
    }

    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
          config.getServletContext());
      }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String email="";
		try{
		email = request.getParameter("email");
	    boolean flag = userService.forgotpassword(email);
        if(flag == true){
        	
  		  p=response.getWriter();
  		  p.print("<html>");
  		  p.print("<body>");
  		  p.print(" A link is sent to your email to reset password for your account");
  		  p.print("</body>");
  		  p.print("</html>");
  		  p.close();
        }}catch(Exception e){
        	
        logger.error("Forgot password servlet:::");

        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
