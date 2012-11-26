package org.training.dcharnavoki.issuetracker.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Message.
 */
public class Message4Jsp {
	// type of messages
	/** The Constant INFO. */
	public static final int INFO = 0;
	/** The Constant SUCCESS. */
	public static final int SUCCESS = 2;
	/** The Constant WARNING. */
	public static final int WARNING = 4;
	/** The Constant ERROR. */
	public static final int ERROR = 6;

	/** The type. */
	private int type;
	/** The text. */
	private String text;

	/** The params. */
	private List<String> params = new ArrayList<String>();
	/**
	 * Instantiates a new message.
	 */
	public Message4Jsp() {
		super();
	}

	/**
	 * Instantiates a new message.
	 * @param messageType
	 *            the message type
	 * @param text
	 *            the text
	 */
	public Message4Jsp(int messageType, String text) {
		super();
		this.type = messageType;
		this.text = text;
	}

	/**
	 * Gets the type.
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type
	 *            the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets the text.
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Adds the.
	 *
	 * @param arg the arg
	 * @return true, if successful
	 */
	public boolean addParam(String arg) {
		return params.add(arg);
	}

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public List<String> getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "Message [" + "text=" + text + ", params=" + params + "]";
	}
}
