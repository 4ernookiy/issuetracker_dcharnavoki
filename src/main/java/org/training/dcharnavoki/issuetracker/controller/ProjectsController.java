package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;

/**
 * The Class ProjectsController.
 */
public class ProjectsController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(MainController.class);

	/** The Constant PROJECTS. */
	private static final String PROJECTS = "projects";

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Project> projects = DaoFactory.getFactory().getProjectDAO().findAll();
			request.setAttribute(PROJECTS, projects);
			jump(ConstJsp.URL_PROJECTS_JSP, request, response);
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}
	}

}
