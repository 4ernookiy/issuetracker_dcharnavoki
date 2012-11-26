package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;


/**
 * Servlet implementation class IssueController.
 */
public class IssueController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(IssueController.class);

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.controller.AbstractBaseController
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter(Constant.ISSUE_ID);
			int issueId = Integer.parseInt(id);
			IIssueDAO issueDao = DaoFactory.getFactory().getIssueDAO();
			Issue issue = issueDao.getIssue(issueId);
			if (null == issue) {
				throw new DaoException("issue from id(" + issueId + ") not found"); // ?????????????
			}
			ICommentDAO commentDao = DaoFactory.getFactory().getCommentDAO();
			List<Comment> comments = commentDao.getCommentsForIssue(issueId);
			request.setAttribute(Keys.ISSUE.getKey(), issue);
			request.setAttribute(Keys.COMMENTS.getKey(), comments);
			jump(ConstJsp.URL_ISSUE_JSP, request, response);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
			LOG.error(e);
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}

	}

}
