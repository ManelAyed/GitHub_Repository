package com.me.president;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CounterServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	    {
		 
	    }
	 
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	    {
		 try {
	        	System.out.println(req.getParameter("counter"));
	        	if (req.getParameter("counter").equals("pluscounter")) {
	        		Counter.increment("pluscounter");
	        		System.out.println("pluscounter : " + Counter.value("pluscounter"));
	        	} 

	        	if (req.getParameter("counter").equals("minuscounter")) {
	        		Counter.increment("minuscounter");
	        		System.out.println("minuscounter : " + Counter.value("minuscounter"));
	        	} 
	        	
	        	req.setAttribute("pluscounter", Counter.value("pluscounter"));
	        	req.setAttribute("minuscounter", Counter.value("minuscounter"));
	        	
	        	this.getServletContext().getRequestDispatcher("/").forward(req, resp);

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	    }

}
