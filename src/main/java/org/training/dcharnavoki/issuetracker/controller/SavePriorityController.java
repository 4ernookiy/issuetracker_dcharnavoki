package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;

/**
 * The Class SavePriorityController.
 */
public class SavePriorityController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final int MAX = 32;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SavePriorityController.class);
	/** The Constant DESCRIPTION. */
	private static final String NEW_PRIORITY = "newPriority";

	/** The Constant EDIT_RESOLUTION. */
	private static final String EDIT_PRIORITY = "editPriority";

	/** The Constant REQUEST_ACTION. */
	private static final String REQUEST_ACTION = "action";

	/** The max_lenght. */
	private int maxLenght;

	/** The min lenght. */
	private int minLenght;

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoFactory factory = DaoFactory.getFactory();
			String newDescription = request.getParameter(NEW_PRIORITY);
			String editItem = request.getParameter(EDIT_PRIORITY);
			String actionStr = request.getParameter(REQUEST_ACTION);
			boolean isUpdate = false;
			if (null == newDescription || null == actionStr) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "request.bad");
				return;
			}

			if ("update".equalsIgnoreCase(actionStr)) {
				isUpdate = true;
			}

			Priority priority = null;
			if (editItem != null && isUpdate) {
				int id = Integer.parseInt(editItem);
				priority = factory.getConfDAO().getPriority(id);
			}
			// Validation
			Message4Jsp message = null;

			if (isUpdate && priority == null) {
				message = new Message4Jsp(Message4Jsp.WARNING, "edit.resolution.selecting");
			}

			if (newDescription.length() < minLenght) {
				message = new Message4Jsp(Message4Jsp.WARNING,
						"create.project.fill-all-fields");
				message.addParam("" + minLenght);
			}

			if (newDescription.length() > maxLenght) {
				message = new Message4Jsp(Message4Jsp.WARNING, "field.description.max.lenght");
				message.addParam("" + maxLenght);
			}

			if (null != message) {
				request.setAttribute(Constant.MESSAGE, message);
				request.setAttribute(NEW_PRIORITY, newDescription);
				request.setAttribute(EDIT_PRIORITY, priority);
				jump(Constant.JUMP_CONTROL_EDIT_PRIORITY, request, response);
				return;
			}

			if (!isUpdate) {
				Priority newItem = new Priority(0);
				newItem.setDescription(newDescription);
				factory.getConfDAO().addPriority(newItem);
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.SUCCESS, "success.add-priority"));
			} else {
				priority.setDescription(newDescription);
				factory.getConfDAO().updatePriority(priority);
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.SUCCESS, "success.update-priority"));
			}
			redirect(Constant.REDIRECT_CONTROL_EDIT_PRIORITY, request, response);
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
