package org.training.dcharnavoki.issuetracker.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.constant.ConstErr;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;

/**
 * The Class Identification.
 */
public class Identification implements Filter {

	/** The Constant ALLOWED_LIST. */
	private static final List<String> ALLOWED_LIST = new ArrayList<String>();
	static {
		ALLOWED_LIST.add("/issuetracker/");
		ALLOWED_LIST.add("/issuetracker/login");
		ALLOWED_LIST.add("/issuetracker/logout");
	}
	/**
	 * Default constructor.
	 */
	public Identification() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter
	 * (javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    String url = req.getRequestURI();
//	    System.out.println("url=" + url);

	    if (!ALLOWED_LIST.contains(url)) {
	    	HttpSession session = (HttpSession) req.getSession(false);
	    	if (null == session) {
				jump(Constant.FORWARD_CONTROL_MAIN, req, res);
	            return;
	    	}
			User user = (User) session.getAttribute(Keys.USER.getKey());
	        if (null == user) {
	            session.setAttribute(Keys.ERROR_MESSAGE.getKey(), ConstErr.MESSAGE_MISSING_LINK);
				jump(Constant.FORWARD_CONTROL_MAIN, req, res);
	            return;
	        }
	    }
	    chain.doFilter(request, response);
	}

	/**
	 * Inits the.
	 *
	 * @param fConfig the f config
	 * @throws ServletException the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	/**
	 * Jump.
	 *
	 * @param url the url
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void jump(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}


}
