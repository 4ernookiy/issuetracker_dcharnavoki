package org.training.dcharnavoki.issuetracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.controller.AbstractBaseController;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
/**
 * Sample Servlet interface implementation.
 */
public class StartServlet extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/* (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		Calendar currentCalendar = Calendar.getInstance();
		Date date = currentCalendar.getTime();
		out.println("<b>" + date + "</b><br/>");
		IIssueDAO issueDao = (IIssueDAO) FactoryDAO.getImplementation(Choice.ISSUE);
		final int q = 15;
		for (int i = 0; i < q; i++) {
			Issue issue = issueDao.getIssue(i);
			if (issue != null) {
				out.println(issue.getCreateDate() + "| " + issue.getSummary() + "|" + "<br/>");
			}
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}