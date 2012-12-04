package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;

/**
 * Servlet implementation class SaveProjectController.
 */
public class SaveProjectController extends AbstractBaseController {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static final int MAX = 200;
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SaveProjectController.class);
	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "newDescription";
	/** The Constant NAME. */
	private static final String NAME = "newName";
	/** The Constant BUILD. */
	private static final String BUILD = "newBuild";
	/** The Constant ASSIGNED. */
	private static final String ASSIGNED = "newAssigned";
	private static final String ACTION = "action";
	private static final String PROJECT_ID = "projectId";

	/** The max_lenght. */
	private int maxLenght;
	private int minLenght;
	private boolean isUpdate = false;

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoFactory factory = DaoFactory.getFactory();
			String name = request.getParameter(NAME);
			String description = request.getParameter(DESCRIPTION);
			String buildStr = request.getParameter(BUILD);
			String assignedStr = request.getParameter(ASSIGNED);
			String actionStr = request.getParameter(ACTION);

			Project project = null;
			int assignedId = Integer.parseInt(assignedStr);
			User assigned = factory.getUserDAO().getUser(assignedId);

			// Validation
			Message4Jsp message = null;
			if ("update".equals(actionStr)) {
				isUpdate = true;
				String projectIdStr = request.getParameter(PROJECT_ID);
				int projectId = Integer.parseInt(projectIdStr);
				project = factory.getProjectDAO().getProject(projectId);
			}

			if (null == name || null == description || actionStr == null
					|| (isUpdate && project == null)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "request.bad");
				return;
			}
			if (description.length() > maxLenght) {
				message = new Message4Jsp(Message4Jsp.WARNING,
						"create.project.description.lenght.max");
				message.addParam("" + maxLenght);
			}
			if (null == assigned) {
				message = new Message4Jsp(Message4Jsp.WARNING, "create.project.who-assigned");
			}

			if (description.length() < minLenght || name.length() < minLenght
					|| (buildStr.length() < minLenght && !isUpdate)) {
				message = new Message4Jsp(Message4Jsp.WARNING,
						"create.project.fill-all-fields");
				message.addParam("" + minLenght);
			}

			if (null != message) {
				request.setAttribute(Constant.MESSAGE, message);
				request.setAttribute(NAME, name);
				request.setAttribute(DESCRIPTION, description);
				request.setAttribute(ASSIGNED, assigned);
				request.setAttribute(BUILD, buildStr);
				if (!isUpdate) {
					jump(Constant.JUMP_CONTROL_CREATE_PROJECT, request, response);
				} else {
					jump(Constant.JUMP_CONTROL_EDIT_PROJECT, request, response);
				}
				return;
			}

			Build build = new Build(1);
			if (!isUpdate) {
				project = new Project(factory.getProjectDAO().getIdForNewProjects());
				List<Build> builds = new ArrayList<Build>();
				build.setDescription(buildStr);
				builds.add(build);
				project.setName(name);
				project.setDescription(description);
				project.setBuilds(builds);
				project.setManager(assigned);
				factory.getProjectDAO().addProject(project);
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.SUCCESS, "success.add-project"));
			} else {
				project.setName(name);
				project.setDescription(description);
				project.setManager(assigned);
				factory.getProjectDAO().updateProject(project);
				if (!buildStr.isEmpty()) {
					build.setDescription(buildStr);
					factory.getProjectDAO().addBuild(build, project.getId());
				}
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.SUCCESS, "success.update-project"));
			}

			redirect(Constant.REDIRECT_CONTROL_PROJECTS, request, response);
		} catch (NumberFormatException e) {
			LOG.error(e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		String str = config.getInitParameter("description.max.lenght");
		try {
			maxLenght = Integer.parseInt(str);
			minLenght = DaoFactory.getConfigAplication().getInt(
					ConfigApp.ConfKeys.MESSAGE_LENGTH_MIN);
		} catch (DaoException e) {
			minLenght = 1;
			maxLenght = MAX;
			LOG.error(e);
		} catch (NumberFormatException e) {
			minLenght = 1;
			maxLenght = MAX;
			LOG.error(e);
		}
	}

}
