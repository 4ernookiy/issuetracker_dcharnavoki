package org.training.dcharnavoki.issuetracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.controller.AbstractBaseController;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;

/**
 */
public class StartServlet extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Issur tracker</title>");
		out.println("</head>");
		out.println("<body>");

		IIssueDAO issueDao = (IIssueDAO) FactoryDAO
				.getImplementation(Choice.ISSUE);

		out.println("<h1>Issues</h1><br>");
		out.println("<table>");
		out.println("<tr>" + "<th>Id</th>" + "<th>Project</th>"
				+ "<th>Build</th>" + "<th>date create bug</th>"
				+ "<th>Summary</th>" + "<th>last update</th>" + "</tr>");

		DateFormat dateFormat = DateFormat.getDateInstance();
		for (Issue issue : issueDao.getAllIssues()) {
			out.println("<tr>");
			out.println("<td>" + issue.getId() + "</td>");
			out.println("<td>" + issue.getProject().getName() + "</td>");
			out.println("<td>" + issue.getBuild().getName() + "</td>");
			out.println("<td>" + dateFormat.format(issue.getCreateDate()) + "</td>");
			out.println("<td>" + issue.getSummary() + "</td>");
			out.println("<td>" + dateFormat.format(issue.getModifyDate()) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");

		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}