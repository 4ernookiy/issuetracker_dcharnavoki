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
public class ChangePasswordController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ChangePasswordController.class);
	private static final String PASSWORD_OLD = "oldPassword";
	private static final String PASSWORD_NEW = "newPassword";
	private static final String PASSWORD_NEW_CONFIRM = "confirmPassword";

	private Pattern patternPassword;

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
			String passwordOld = request.getParameter(PASSWORD_OLD);
			String passwordNew = request.getParameter(PASSWORD_NEW);
			String passwordNewConfirm = request.getParameter(PASSWORD_NEW_CONFIRM);

			// Validation
			Message4Jsp message = null;

			if (patternPassword != null) { // be switched off via config
				Matcher matcherPassword = patternPassword.matcher(passwordNew);
				if (!matcherPassword.matches()) {
					message = new Message4Jsp(Message4Jsp.WARNING, "edit-user.password.must-match");
				}
			}

			if (!passwordNew.equals(passwordNewConfirm)) {
				message = new Message4Jsp(Message4Jsp.WARNING, "edit-user.password.new.not-match");
			}

			String hashPassword = HashUtil.getMD5(passwordOld);
			if (!user.getPassword().equals(hashPassword)) {
				message = new Message4Jsp(Message4Jsp.WARNING, "edit-user.password.old.not-match");
			}

			if (null != message) {
				request.setAttribute(Constant.MESSAGE, message);
				jump(Constant.JUMP_JSP_CHANGE_PASSWORD, request, response);
				return;
			}

			// do something
			DaoFactory factory = DaoFactory.getFactory();
			hashPassword = HashUtil.getMD5(passwordNew);
			user.setPassword(hashPassword);
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
		try {
			String pattern = DaoFactory.getConfigAplication().get(
					ConfKeys.PATTERN_PASSWORD);
			if (null != pattern) {
				patternPassword = Pattern.compile(pattern);
			}
		} catch (DaoException e) {
			LOG.error(e);
		}
	}

}
