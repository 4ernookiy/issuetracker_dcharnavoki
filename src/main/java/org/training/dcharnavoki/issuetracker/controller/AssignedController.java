package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;

/**
 * The Class AssignedController.
 */
public class AssignedController extends AbstractBaseController {
	private static final Logger LOG = Logger.getLogger(AssignedController.class);

	/**
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.controller.AbstractBaseController#
	 * performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = (User) request.getSession().getAttribute(Keys.USER.getKey());

			if (null == user) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			DaoFactory factory = DaoFactory.getFactory();
			List<Issue> issues = factory.getIssueDAO().getIssuesForUser(user);
			request.setAttribute(Keys.ISSUES.getKey(), issues);
			jump(ConstJsp.URL_ASSIGNED_JSP, request, response);
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}

	}

}
