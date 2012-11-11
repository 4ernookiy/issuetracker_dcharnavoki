package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.io.InputStream;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class DefaultParser.
 */
public class DefaultParser extends DefaultHandler {
	/** The Constant LOGGER. */
	private final Logger log = Logger.getLogger(getClass());
	/** The complete. */
	private boolean complete = false;

	/** The file name. */
	private InputStream file;

	/**
	 * Instantiates a new default parser.
	 * @param fileName
	 *            the file name the dao exception
	 * @throws DaoException
	 *             the dao exception
	 */
	public DefaultParser(final String fileName) throws DaoException {
		super();
		file = DefaultParser.class.getResourceAsStream(fileName);
		if (file == null) {
			throw new DaoException("file not found:" + fileName);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		complete = true;
	}

	/**
	 * Wait compete.
	 * @throws DaoException
	 *             the dao exception
	 */
	protected void waitCompete() throws DaoException {
		if (!complete) {
			parseDocument();
		}
	}

	/**
	 * Gets the date from string.
	 * @param dateStr
	 *            the date str
	 * @return the date from string
	 */
	protected java.util.Date getDateFromString(String dateStr) {
		Calendar calendar = DatatypeConverter.parseDateTime(dateStr);
		return calendar.getTime();
	}

	/**
	 * Parses the document.
	 * @throws DaoException
	 *             the dao exception
	 */
	void parseDocument() throws DaoException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = null;
			parser = factory.newSAXParser();
			if (file != null) {
				parser.parse(file, this);
			}
		} catch (Exception e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(e);
			}
			throw new DaoException(e);
		}
	}
}
