package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public abstract class AbstractBaseController extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    performTask(request, response);
		}  	
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    performTask(request, response);
		}   	  	    
		
		abstract protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	    
	    protected void jump(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		    rd.forward(request, response);
//	    	request.getRequestDispatcher(url).include(request, response); ??????
		  }

}