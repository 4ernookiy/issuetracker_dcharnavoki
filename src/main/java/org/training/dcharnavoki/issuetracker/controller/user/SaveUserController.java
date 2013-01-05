package org.training.dcharnavoki.issuetracker.controller.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.Role;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.controller.AbstractBaseController;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class AssignedController.
 */
public class SaveUserController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SaveUserController.class);
	private static final String FIRST_NAME = "newFirstName";
	private static final String LAST_NAME = "newLastName";
	private static final String EMAIL = "newEmail";
	private static final String ROLE = "newRole";

	/** The Constant serialVersionUID. */
	private static final int MAX = 32;
	/** The max_lenght. */
	private int maxLenght;

	/** The min lenght. */
	private int minLenght;
	private Pattern patternEmail;

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
			User user = (User) request.getSession().getAttribute(Keys.USER.getKey());
			if (null == user) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			String firstName = request.getParameter(FIRST_NAME);
			String lastName = request.getParameter(LAST_NAME);
			String email = request.getParameter(EMAIL);
			Role role = Role.valueOf(request.getParameter(ROLE));

			// Validation
			Message4Jsp message = null;

			if (firstName.length() < minLenght || lastName.length() < minLenght) {
				message = new Message4Jsp(Message4Jsp.WARNING, "field.min.lenght");
				message.addParam("" + minLenght);
			}

			if (firstName.length() > maxLenght || lastName.length() > maxLenght) {
				message = new Message4Jsp(Message4Jsp.WARNING, "field.max.lenght");
				message.addParam("" + maxLenght);
			}

			Matcher matcherMail = patternEmail.matcher(email);
			if (!matcherMail.matches()) {
				message = new Message4Jsp(Message4Jsp.WARNING, "edit-user.email.must-match");
				message.addParam(DaoFactory.getConfigAplication().get(ConfKeys.PATTERN_EMAIL));
			}

			if (null != message) {
				request.setAttribute(Constant.MESSAGE, message);
				request.setAttribute(FIRST_NAME, firstName);
				request.setAttribute(LAST_NAME, lastName);
				request.setAttribute(EMAIL, email);
				request.setAttribute(ROLE, role);
				jump(Constant.JUMP_JSP_EDIT_USER, request, response);
				return;
			}

			//do  something
			DaoFactory factory = DaoFactory.getFactory();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setRole(role);
			factory.getUserDAO().update(user);
			request.getSession().setAttribute(Keys.USER.getKey(), user);
			request.getSession().setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.SUCCESS, "success.update-user"));
			redirect(Constant.REDIRECT_JSP_EDIT_USER, request, response);
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
			patternEmail = Pattern.compile(DaoFactory.getConfigAplication().get(
					ConfKeys.PATTERN_EMAIL));
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
