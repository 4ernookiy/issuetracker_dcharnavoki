package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;

/**
 * The Class SaveResolutionController.
 */
public class SaveResolutionController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final int MAX = 32;
	private static final long serialVersionUID = 1L;
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SaveResolutionController.class);
	/** The Constant DESCRIPTION. */
	private static final String NEW_RESOLUTION = "newResolition";

	/** The Constant EDIT_RESOLUTION. */
	private static final String EDIT_RESOLUTION = "editResolition";

	private static final String REQUEST_ACTION = "action";

	private int maxLenght;
	private int minLenght;

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoFactory factory = DaoFactory.getFactory();
			String newDescription = request.getParameter(NEW_RESOLUTION);
			String editResolution = request.getParameter(EDIT_RESOLUTION);
			String actionStr = request.getParameter(REQUEST_ACTION);
			boolean isUpdate = false;
			if (null == newDescription || null == actionStr) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "request.bad");
				return;
			}

			if ("update".equalsIgnoreCase(actionStr)) {
				isUpdate = true;
			}

			Resolution resolution = null;
			if (editResolution != null && isUpdate) {
				int id = Integer.parseInt(editResolution);
				resolution = factory.getResolutionDAO().findByID(id);
			}
			// Validation
			Message4Jsp message = null;

			if (isUpdate && resolution == null) {
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
				request.setAttribute(NEW_RESOLUTION, newDescription);
				request.setAttribute(EDIT_RESOLUTION, resolution);
					jump(Constant.JUMP_CONTROL_EDIT_RESOLUTION, request, response);
					return;
			}

			if (!isUpdate) {
				Resolution res = new Resolution(0);
				res.setDescription(newDescription);
				factory.getResolutionDAO().save(res);
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.SUCCESS, "success.add-resolution"));
			} else {
				resolution.setDescription(newDescription);
				factory.getResolutionDAO().update(resolution);
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.SUCCESS, "success.update-resolution"));
			}
			redirect(Constant.REDIRECT_CONTROL_EDIT_RESOLUTION, request, response);
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
