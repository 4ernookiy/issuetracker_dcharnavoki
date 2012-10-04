package org.training.dcharnavoki.issuetracker;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.dcharnavoki.issuetracker.constant.ConstErr;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.controller.AbstractBaseController;
import org.training.dcharnavoki.issuetracker.dao.FactoryDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.LoadConfig;

/**
 * Sample Servlet interface implementation.
 */


public class StartServlet extends AbstractBaseController {

	@Override
	public void init() throws ServletException {
		try {
			ConfigApp configApp = LoadConfig.getConfig(Constant.CONFIG_PROPERTY_FILE);
			FactoryDAO.setImplementation(configApp.getImplStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<b>What this ?!</b>");
		out.println(FactoryDAO.getImplementation().getClass().getCanonicalName());
		out.println("</body>");
		out.println("</html>");
		out.close();		
	}
}