package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;

/**
 * The Class AssignedController.
 */
public class CreateIssueController extends AbstractBaseController {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(CreateIssueController.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant TYPES. */
	private static final String TYPES = "types";
	/** The Constant PRIORITIES. */
	private static final String PRIORITIES = "priorities";
	/** The Constant PROJECTS. */
	private static final String PROJECTS = "projects";
	/** The Constant USERS. */
	private static final String USERS = "users";
	/** The Constant STATUSES. */
	private static final String STATUSES = "statuses";

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.controller.AbstractBaseController#
	 * performTask(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			DaoFactory factory = DaoFactory.getFactory();
			List<Project> projects = factory.getProjectDAO().getProjects();
			List<Priority> priorities = factory.getConfDAO().getPriorities();
			List<Type> types = factory.getConfDAO().getTypes();
			List<User> users = factory.getUserDAO().getAllUsers();
			List<Status> statuses = factory.getConfDAO().getStatuses();

			request.setAttribute(PROJECTS, projects);
			request.setAttribute(PRIORITIES, priorities);
			request.setAttribute(TYPES, types);
			request.setAttribute(USERS, users);
			request.setAttribute(STATUSES, statuses);

			jump(ConstJsp.URL_CREATE_ISSUE_JSP, request, response);
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}
	}
}
