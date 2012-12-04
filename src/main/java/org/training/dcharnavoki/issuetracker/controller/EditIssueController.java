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
import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;

/**
 * The Class EditIssueController.
 */
public class EditIssueController extends AbstractBaseController {

	/**
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(EditIssueController.class);
	private static final String TYPES = "types";
	private static final String PRIORITIES = "priorities";
	private static final String PROJECTS = "projects";
	private static final String USERS = "users";
	private static final String STATUSES = "statuses";
	private static final String RESOLUTION = "resolutions";

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.controller.AbstractBaseController
	 * #performTask(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoFactory factory = DaoFactory.getFactory();
			String id = request.getParameter(Constant.ISSUE_ID);
			int issueId = Integer.parseInt(id);
			Issue issue = factory.getIssueDAO().getIssue(issueId);
			if (null == issue) {
				throw new DaoException("issue from id(" + issueId + ") not found"); // ?????????????
			}
			List<Project> projects = factory.getProjectDAO().getProjects();
			List<Priority> priorities = factory.getConfDAO().getPriorities();
			List<Type> types = factory.getConfDAO().getTypes();
			List<User> users = factory.getUserDAO().getAllUsers();
			List<Status> statuses = factory.getConfDAO().getStatuses();
			List<Resolution> resolutions = factory.getConfDAO().getResolutions();

			request.setAttribute(PROJECTS, projects);
			request.setAttribute(PRIORITIES, priorities);
			request.setAttribute(TYPES, types);
			request.setAttribute(USERS, users);
			request.setAttribute(STATUSES, statuses);
			request.setAttribute(RESOLUTION, resolutions);

			List<Comment> comments = factory.getCommentDAO().getCommentsForIssue(issueId);
			request.setAttribute(Keys.ISSUE.getKey(), issue);
			request.setAttribute(Keys.COMMENTS.getKey(), comments);
			jump(ConstJsp.URL_EDIT_ISSUE_JSP, request, response);
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
