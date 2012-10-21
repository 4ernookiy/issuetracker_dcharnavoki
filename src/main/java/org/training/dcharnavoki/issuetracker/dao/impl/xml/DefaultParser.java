package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class DefaultParser.
 */
public class DefaultParser extends DefaultHandler {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(DefaultParser.class);
	/** The complete. */
	private boolean complete = false;
	/**
	 * Instantiates a new default parser.
	 *
	 * @param fileName the file name
	 */
	public DefaultParser(final String fileName) {
		super();
		final DefaultParser dp = this;
		new Thread(new Runnable() {
			@Override
			public void run() {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				try {
					SAXParser parser = null;
					try {
						parser = factory.newSAXParser();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
					InputStream file = DefaultParser.class.getResourceAsStream(fileName);
					parser.parse(file, dp);
				} catch (SAXException e) {
					if (LOGGER.isEnabledFor(Level.ERROR)) {
						LOGGER.error(e);
					}
					System.out.println(e.getMessage());
				} catch (IOException e) {
					if (LOGGER.isEnabledFor(Level.ERROR)) {
						LOGGER.error(e);
					}
					System.out.println(e.getMessage());
				}
			}
		}).start();

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		complete = true;
		synchronized (this) {
			notifyAll();
		}
	}
	/**
	 * Wait compete.
	 */
	protected synchronized void waitCompete() {
			while (!complete) {
				try {
					wait();
				} catch (InterruptedException e) {
					if (LOGGER.isEnabledFor(Level.ERROR)) {
						LOGGER.error(e);
					}
				}
			}
	}

	/**
	 * Gets the date from string.
	 *
	 * @param dateStr the date str
	 * @return the date from string
	 */
	protected java.util.Date getDateFromString(String dateStr) {
		Calendar calendar = DatatypeConverter.parseDateTime(dateStr);
		return calendar.getTime();
	}

}
