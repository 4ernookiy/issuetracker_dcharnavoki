package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstErr;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;

/**
 * The Class LoginController.
 */
public class LoginController extends AbstractBaseController {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 *
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

			// if (login == null || password == null) {
			// jump(Constant.CONTROL_MAIN, request, response);
			// return;
			// }

			login = login.trim();
			if (Constant.EMPTY_VALUE.equals(login)) {
				request.getSession().setAttribute(Keys.ERROR_MESSAGE.getKey(),
						ConstErr.MESSAGE_LOGIN_EMPTY);
//				jump(Constant.FORWARD_CONTROL_MAIN, request, response);
				redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
				return;
			}

			IUserDAO userDAO = (IUserDAO) FactoryDAO
					.getImplementation(Choice.USER);
			User user;
			user = userDAO.getUser(login, password);

			if (user == null) {
				request.getSession().setAttribute(Keys.ERROR_MESSAGE.getKey(),
						ConstErr.MESSAGE_ERROR_PASSWORD);
				request.getSession().setAttribute(Keys.LOGIN.getKey(), login);
				redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
//				jump(Constant.FORWARD_CONTROL_MAIN, request, response);
				return;
			}

			HttpSession session = request.getSession(true);
			session.setAttribute(Keys.USER.getKey(), user);
			redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
//			jump(Constant.FORWARD_CONTROL_MAIN, request, response);

		} catch (Exception e) {
			request.setAttribute(Keys.ERROR_MESSAGE.getKey(), e.getMessage());
			redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
//			jump(Constant.FORWARD_CONTROL_MAIN, request, response);
			// jumpErrorPage(e.getMessage(), request, response);
			System.out.println(e.getMessage());
		}
	}

}
