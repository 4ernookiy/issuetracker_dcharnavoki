package org.training.dcharnavoki.issuetracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.constant.Constant.Keys;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
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
			IIssueDAO issueDao = (IIssueDAO) FactoryDAO.getImplementation(Choice.ISSUE);
			List<Issue> pieces = issueDao.getAllIssues();
			request.setAttribute(Keys.ISSUES.getKey(), pieces);
			jump("/main.jsp", request, response);
//		}
//		catch (DaoException e) {
//			jump("/main.jsp", request, response);
//		}

	}

}