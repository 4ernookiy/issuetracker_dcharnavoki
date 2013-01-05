package org.training.dcharnavoki.issuetracker.controller.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;
import org.training.dcharnavoki.issuetracker.util.HashUtil;

/**
 * The Class AssignedController.
 */
public class AddUserController extends AbstractBaseController {
	private static final Logger LOG = Logger.getLogger(AddUserController.class);
	private static final String FIRST_NAME = "newFirstName";
	private static final String LAST_NAME = "newLastName";
	private static final String EMAIL = "newEmail";
	private static final String ROLE = "role";
	private static final String PASSWORD = "password";
	private static final String CONFIRM_PASS = "confirm";

	private static final long serialVersionUID = 1L;
	private Pattern patternMail;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.controller.AbstractBaseController#
	 * performTask(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = (User) request.getSession().getAttribute(
					Keys.USER.getKey());
			if (null == user || user.getRole() != Role.ADMIN) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			String firstName = request.getParameter(FIRST_NAME);
			String lastName = request.getParameter(LAST_NAME);
			String email = request.getParameter(EMAIL);
			Role role = Role.valueOf(request.getParameter(ROLE));
			String password = request.getParameter(PASSWORD);
			String passwordConfirm = request.getParameter(CONFIRM_PASS);

			DaoFactory factory = DaoFactory.getFactory();
			User isUser = factory.getUserDAO().getUser(email);
			String validateData = validate(firstName, lastName, email,
					password, passwordConfirm, isUser);
			if (validateData != null) {
				request.setAttribute(Constant.MESSAGE, new Message4Jsp(
						Message4Jsp.WARNING, validateData));
				request.setAttribute(FIRST_NAME, firstName);
				request.setAttribute(LAST_NAME, lastName);
				request.setAttribute(EMAIL, email);
				request.setAttribute(ROLE, role);
				jump(ConstJsp.URL_ADD_USER_JSP, request, response);
				return;
			}
			User newUser = new User(0);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setEmail(email);
			newUser.setRole(role);
			newUser.setPassword(HashUtil.getMD5(password));
			factory.getUserDAO().save(newUser);
			request.getSession().setAttribute(Constant.MESSAGE,
					new Message4Jsp(Message4Jsp.SUCCESS, "success.add-user"));
			redirect(Constant.REDIRECT_REGISTER_JSP, request, response);
		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE, new Message4Jsp(
					Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}

	}

	private String validate(String firstName, String lastName, String email,
			String password, String passwordConfirm, User isUser)
			throws DaoException {

		if (firstName.trim().isEmpty() || lastName.trim().isEmpty()
				|| email.trim().isEmpty() || password.trim().isEmpty()
				|| passwordConfirm.trim().isEmpty()) {
			return "register.messages.data.not-empty";
		}
		if (null != isUser) {
			return "register.messages.user-exist";
		}
		patternMail = Pattern.compile(DaoFactory.getConfigAplication().get(
				ConfKeys.PATTERN_EMAIL));
		Matcher matcherMail = patternMail.matcher(email);
		if (email.trim().isEmpty() || !matcherMail.matches()) {
			return "register.messages.email-bad";
		}
		if (!password.equals(passwordConfirm)) {
			return "register.messages.password-bad";
		}
		return null;
	}
}
