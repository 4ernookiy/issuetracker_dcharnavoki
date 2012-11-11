package org.training.dcharnavoki.issuetracker.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * The Class HashUtil.
 */
public final class HashUtil {
	private static final Logger LOG = Logger.getLogger(HashUtil.class);

	/**
	 * Instantiates a new hash util.
	 */
	private HashUtil() {
		super();
	}
	/**
	 * Gets the m d5.
	 *
	 * @param data the data
	 * @return the m d5
	 */
	public static String getMD5(final String data) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(data.getBytes());
			BigInteger bigInt = new BigInteger(1, m.digest());
			final int words = 32;
			final int count = 16;
			String hashtext = bigInt.toString(count);
			while (hashtext.length() < words) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(e);
			}
			return e.getMessage();
		}
	}

}
