package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.dcharnavoki.issuetracker.constant.Constant;

/**
 * Servlet implementation class LogoutController.
 */
public class LogoutController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.controller.AbstractBaseController
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		jump(Constant.CONTROL_MAIN, request, response);
	}
}
