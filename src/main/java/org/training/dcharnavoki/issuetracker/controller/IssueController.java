package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;


/**
 * Servlet implementation class IssueController.
 */
public class IssueController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.controller.AbstractBaseController
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("issueId");
		int issueId = Integer.parseInt(id);
		IIssueDAO issueDao = (IIssueDAO) FactoryDAO.getImplementation(Choice.ISSUE);
		Issue issue = issueDao.getIssue(issueId);
		PrintWriter out = response.getWriter();
		out.println("<h1>Issue description</h1><br><table>");
		out.println("<tr>" + "<th>Id</th>" + "<th>Project</th>"
				+ "<th>Build</th>" + "<th>date create bug</th>"
				+ "<th>Summary</th>" + "<th>last update</th>" + "</tr>");
//		DateFormat dateFormat = DateFormat.getDateInstance();

			out.println("<tr><td>");
			out.println("" + issue.getId());
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
		out.println("</table><br><br><br>");
		ICommentDAO commentDao = (ICommentDAO) FactoryDAO.getImplementation(Choice.COMMENT);
		List<Comment> comments = commentDao.getCommentsForIssue(issueId);
		if (comments != null) {
			printComments(out, comments);
		}
	}

	/**
	 * Prints the comments.
	 *
	 * @param out the out
	 * @param comments the comments
	 */
	private void printComments(PrintWriter out,  List<Comment> comments) {
		out.println("<h1>comments:</h1><br>");
		DateFormat dateFormat = DateFormat.getDateInstance();
		for (Comment comment: comments) {
			out.println("<table><tr><td>");
			out.println("" + comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
			out.println(" add: " + dateFormat.format(comment.getDate()) + "</td></tr>");
			out.println("<tr><td>" + comment.getText() + "</td></tr>");
			out.println("</table><br><br>");
		}
	}

}
