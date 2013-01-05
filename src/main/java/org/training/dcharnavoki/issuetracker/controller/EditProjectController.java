package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;

/**
 * The Class EditProjectController.
 */
public class EditProjectController extends AbstractBaseController {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(EditPriorityController.class);

	/** The Constant RESOLUTIONS. */
	private static final String PROJECT_ID = "projectId";
	private static final String PROJECT = "project";
	private static final String USERS = "users";

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
			String idStr = request.getParameter(PROJECT_ID);
			int id = Integer.parseInt(idStr);
			Project project = factory.getProjectDAO().findByID(id);
			List<User> users = factory.getUserDAO().findAll();

			if (null == project) {
				throw new DaoException("project from id(" + id + ") not found"); // ?????????????
			}
			request.setAttribute(PROJECT, project);
			request.setAttribute(USERS, users);
			jump(Constant.JUMP_JSP_EDIT_PROJECT, request, response);
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}
	}

}
