package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;

/**
 * Servlet implementation class LogoutController.
 */
public class LogoutController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private static final Logger EVENT_LOG = Logger.getLogger(Constant.LOG_EVENTS + LoginController.class);

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.controller.AbstractBaseController
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (EVENT_LOG.isInfoEnabled()) {
			User user = (User) request.getSession().getAttribute(Keys.USER.getKey());
			if (null != user) {
				EVENT_LOG.info("Sign Out :" + user);
			}
		}
		request.getSession().invalidate();
		redirect(Constant.REDIRECT_CONTROL_MAIN, request, response);
	}
}
