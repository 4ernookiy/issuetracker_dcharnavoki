package org.training.dcharnavoki.issuetracker.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Message4Jsp;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstJsp;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.controller.AbstractBaseController;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.util.HashUtil;

/**
 * The Class LoginController.
 */
public class LoginController extends AbstractBaseController {

	/** The Constant EVENT_LOG. */
	private static final Logger EVENT_LOG = Logger
			.getLogger(Constant.LOG_EVENTS + LoginController.class);
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LoginController.class);
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.controller.AbstractBaseController
	 * #performTask(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String login = request.getParameter(Keys.LOGIN.getKey());
			String password = request.getParameter(Keys.PASSWORD.getKey());
			IUserDAO userDAO = DaoFactory.getFactory().getUserDAO();

			if (null == login || null == password || login.isEmpty()) {
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.WARNING, "login.empty"));
				redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
				return;
			}
			login = login.trim();
			User user;
			user = userDAO.getUser(login);
			String hashPassword = HashUtil.getMD5(password);

			if (user != null && user.getPassword().equals(hashPassword)) {
				HttpSession session = request.getSession(true);
				session.setAttribute(Keys.USER.getKey(), user);
				redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
				EVENT_LOG.info("Sign In :" + user);

			} else {
				request.getSession().setAttribute(Constant.MESSAGE,
						new Message4Jsp(Message4Jsp.WARNING, "login.bad"));
				request.getSession().setAttribute(Keys.LOGIN.getKey(), login);
				redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
				return;
			}

		} catch (DaoException e) {
			LOG.error(e);
			request.setAttribute(Constant.MESSAGE, new Message4Jsp(
					Message4Jsp.ERROR, e.getLocalizedMessage()));
			jump(ConstJsp.URL_ERROR_JSP, request, response);
		}
	}

}
