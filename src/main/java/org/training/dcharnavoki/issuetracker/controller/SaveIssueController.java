package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;

/**
 * The Class AssignedController.
 */
public class SaveIssueController extends AbstractBaseController {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SaveIssueController.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant SUMMARY. */
	private static final String SUMMARY = "newSummary";
	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "newDescription";
	/** The Constant PRIORITY. */
	private static final String PRIORITY = "newPriority";
	/** The Constant PROJECT_AND_BILD. */
	private static final String PROJECT_AND_BILD = "newProject";
	/** The Constant ASSIGNED. */
	private static final String ASSIGNED = "newAssigned";
	/** The Constant TYPE. */
	private static final String TYPE = "newType";
	/** The Constant STATUS. */
	private static final String STATUS = "newStatus";

	private int minLenght;
	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoFactory factory = DaoFactory.getFactory();
			User user = (User) request.getSession().getAttribute(Keys.USER.getKey());
			if (null == user) { // for not authorized
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			String summary = request.getParameter(SUMMARY);
			String description = request.getParameter(DESCRIPTION);
			String priorityStr = request.getParameter(PRIORITY);
			String projectAndBildStr = request.getParameter(PROJECT_AND_BILD);
			String assignedStr = request.getParameter(ASSIGNED);
			String typeStr = request.getParameter(TYPE);
			String statusStr = request.getParameter(STATUS);

			int priorityId = Integer.parseInt(priorityStr);
			Priority priority = factory.getPriorityDAO().findByID(priorityId);

			int typeId = Integer.parseInt(typeStr);
			Type type = factory.getTypeDAO().findByID(typeId);

			int projectId = Integer.parseInt(projectAndBildStr.split(Constant.DELIMETER)[0]);
			Project project = factory.getProjectDAO().findByID(projectId);
			int buildId = Integer.parseInt(projectAndBildStr.split(Constant.DELIMETER)[1]);
			Build build = factory.getBuildDAO().findByID(buildId);
			int assignedId = Integer.parseInt(assignedStr);
			User assigned = factory.getUserDAO().findByID(assignedId);

			int statusId = Integer.parseInt(statusStr);
			Status status = factory.getStatusDAO().findByID(statusId);

			// Validation
			Message4Jsp message = null;
			if (null == priority || null == project || null == build || null == type
					|| null == summary || null == description) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "request.bad");
				return;
			}
			if (summary.length() <= minLenght || description.length() <= minLenght) {
				message = new Message4Jsp(Message4Jsp.WARNING, "create-issue.min-length-text");
				message.addParam("" + minLenght);
			}
			if (status.getId() == Constant.STATUS_ASSIGNED && null == assigned) {
				message = new Message4Jsp(Message4Jsp.WARNING, "create-issue.who-assigned");
			}
			if (null != message) {
				request.setAttribute(Constant.MESSAGE, message);
				request.setAttribute(SUMMARY, summary);
				request.setAttribute(DESCRIPTION, description);
				request.setAttribute(PRIORITY, priority);
				request.setAttribute(PROJECT_AND_BILD, projectAndBildStr);
				request.setAttribute(TYPE, type);
				request.setAttribute(ASSIGNED, assigned);
				request.setAttribute(STATUS, status);
				jump(Constant.JUMP_CONTROL_CREATE_ISSUE, request, response);
				return;
			}
			Issue issue = new Issue();
			issue.setAssigned(assigned);
			issue.setBuild(build);
			Date date = new Date();
			issue.setCreateDate(date);
			issue.setCreatedBy(user);
			issue.setDescription(description);
			issue.setSummary(summary);
			issue.setModifiedBy(user);
			issue.setModifyDate(date);
			issue.setPriority(priority);
			issue.setProject(project);
			issue.setStatus(status);
			issue.setType(type);

			factory.getIssueDAO().save(issue);

			request.getSession().setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.SUCCESS, "success.add-issue"));
			redirect(Constant.REDIRECT_CONTROL_CREATE_ISSUE, request, response);

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
		try {
			minLenght = DaoFactory.getConfigAplication().getInt(
					ConfigApp.ConfKeys.MESSAGE_LENGTH_MIN);
		} catch (DaoException e) {
			minLenght = 1;
			LOG.error(e);
		}
	}
}
