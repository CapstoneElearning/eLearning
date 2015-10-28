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

import com.capstone.eLearning.resthandler.UserRestHandler;
import com.capstone.eLearning.services.UserService;

/**
 * Servlet implementation class ActivationServlet
 */
public class ActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Autowired
	private UserService userService;
	PrintWriter p;
	private Logger logger = Logger.getLogger(ActivationServlet.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivationServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	email = request.getParameter("userId");
	boolean flag = userService.activateUser(email);
	if(flag == true){
		  p=response.getWriter();
		  p.print("<html>");
		  p.print("<body>");
		  p.print("You have successfully Activated you acount for eLearning");
		  p.print("</body>");
		  p.print("</html>");
		  p.close();
		
	}}
	catch(Exception e){
		logger.error("Failed to activate user with email"+email);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
