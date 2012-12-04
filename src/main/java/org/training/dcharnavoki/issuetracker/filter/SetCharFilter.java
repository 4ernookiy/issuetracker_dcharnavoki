package org.training.dcharnavoki.issuetracker.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class SetCharFilter.
 */
public class SetCharFilter implements Filter {
//	private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private String encoding = "UTF-8";

	/**
	 * Default constructor.
	 */
	public SetCharFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Destroy.
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * Do filter.
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param chain
	 *            the chain
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
//		String contentType = request.getContentType();
//		if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
//			request.setCharacterEncoding(encoding);
//		}
		 String requestEncoding = request.getCharacterEncoding();
		 if (!encoding.equalsIgnoreCase(requestEncoding)) {
		 request.setCharacterEncoding(encoding);
		 }

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * Inits the.
	 * @param fConfig
	 *            the f config
	 * @throws ServletException
	 *             the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String encodingParam = fConfig.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
		}
	}

}
