package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;

/**
 */
public class StartServlet extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static final String PATH_TO_HTTP = "http://localhost:8080/issuetracker/issue";

	private void headPrint(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
		out.println("<html><head><title>Issue tracker</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table width='100%'><tr><td width='90%'><table width='100%'>");
		out.println("<tr align='left'></tr><tr></tr><tr>");
		out.println("<td align='center'>");
		out.println("<H2>Issue Tracker</H2>");
		out.println("</td></tr></table></td>");
		out.println("<td align='right'>");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Keys.USER.getKey());
		if (user == null) {
			out.println("<form name='LoginForm' method='POST' ACTION='login'>");
			out.println("<fieldset>");
			out.println("<table align='right' width='280'>");
			out.println("<tr>");
			out.println("<td>Login:</td>");
			String login = (String) request.getSession().getAttribute(Keys.LOGIN.getKey());
			if (login != null) {
				out.println("<td><input type='text' name='key_login' value='"
						+ login + "'></td>");
			} else {
				out.println("<td><input type='text' name='key_login' value=''></td>");
			}
			out.println("</tr><tr><td>Password:</td>");
			out.println("<td><input type='password' name='key_password' value=''></td>");
			out.println("<td><input type='submit' value='Enter'></td>");
			out.println("</tr></table></fieldset></form>");
		} else {
			out.println("<form name='UserForm' method='POST' ACTION='logout'>");
			out.println("<fieldset>");
			out.println("<table align='right' width='280'>");
			out.println("<tr>");
			out.println("<td>Your login:</td>");
			out.println("<td><label>" + user.getFirstName() + " "
					+ user.getLastName() + "</label></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Role:</td>");
			out.println("<td><label>" + user.getRole() + "</label></td>");
			out.println("<td align='right'><input name='submit' type='submit'");
			out.println("value='Exit'></td>");
			out.println("</tr></table></fieldset></form>");
		}
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		headPrint(request, response);
		PrintWriter out = response.getWriter();

		errorsPrint(request, response);
		out.println("</td></tr></table>");
		IIssueDAO issueDao = (IIssueDAO) FactoryDAO
				.getImplementation(Choice.ISSUE);
		out.println("<h1>Issues</h1><br><table>");
		out.println("<tr>" + "<th>Id</th>" + "<th>Project</th>"
				+ "<th>Build</th>" + "<th>date create bug</th>"
				+ "<th>Summary</th>" + "<th>last update</th>" + "</tr>");
//		DateFormat dateFormat = DateFormat.getDateInstance();
		ListIterator<Issue> iterator = issueDao.getAllIssues().listIterator(
				issueDao.getAllIssues().size());

		// for (Issue issue : issues);
		final int maxLinePrint = 5;
		int count = 0;
		Issue issue;
		while (iterator.hasPrevious() && count++ <= maxLinePrint) {
			issue = iterator.previous();
//?command=mycommand
			out.println("<tr><td>");
			out.println("<a href='" + PATH_TO_HTTP + "?issueId=" + issue.getId()
					+ "'>" + issue.getId() + "</a>");
			out.println("</td>");
			out.println("<td>" + issue.getPriority().getDescription() + "</td>");
			out.println("<td>" + issue.getAssigned().getFirstName() + " " + issue.getAssigned().getLastName() + "</td>");
//			out.println("<td>" + dateFormat.format(issue.getCreateDate())
//					+ "</td>");
			out.println("<td>" + issue.getType().getDescription() + "</td>");
			out.println("<td>" + issue.getStatus().getDescription() + "</td>");
			out.println("<td>" + issue.getSummary() + "</td>");
//			out.println("<td>" + dateFormat.format(issue.getModifyDate())
//					+ "</td>");
			out.println("</tr>");
		}
		out.println("</table>");

		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	private void errorsPrint(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String errorMessage = (String) request.getSession().getAttribute(Keys.ERROR_MESSAGE.getKey());
		if (errorMessage != null && !Constant.EMPTY_VALUE.equals(errorMessage)) {
			out.println("<label style='color: red'>" + errorMessage
					+ "</label><br>");
			request.getSession().removeAttribute(Keys.ERROR_MESSAGE.getKey());
		}

	}
}