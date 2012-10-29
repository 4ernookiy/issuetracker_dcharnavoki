package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;

/**
 */
public class MainController extends AbstractBaseController {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 */
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		try{
			IIssueDAO issueDao = DaoFactory.getFactory().getIssueDAO();
			List<Issue> issues = issueDao.getAllIssues();
			ListIterator<Issue> iterator = issues.listIterator(issues.size());
			List<Issue> smallList = new ArrayList<Issue>();
			int count = 1;
			while (iterator.hasPrevious() && count++ <= Constant.MAX_ROWS_FOR_VIEW) {
				smallList.add(iterator.previous());
			}
			request.setAttribute(Keys.ISSUES.getKey(), smallList);
			jump(Constant.MAIN_JSP, request, response);
//		}
//		catch (DaoException e) {
//			jump("/main.jsp", request, response);
//		}

	}

}