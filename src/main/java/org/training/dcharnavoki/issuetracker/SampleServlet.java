package org.training.dcharnavoki.issuetracker;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Sample Servlet interface implementation.
 */
public class SampleServlet implements Servlet {

	private ServletConfig servletConfig;

	@Override
	public void init(ServletConfig config) throws ServletException {
		servletConfig = config;
	}

	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "Sample Servlet interface implementation";
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body><b>Hello world!</b></body>");
		out.println("</html>");
		out.close();
	}
}