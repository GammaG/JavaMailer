package de.init.javamailer.util;

import java.util.Properties;

public class PropertiesLoader {

	private static String senderMail = "";

	public static Properties getProperties() {
		final Properties props = new Properties();
		props.put("mail.smtp.host", "smtp-mail.outlook.com"); // SMTP Host
		props.put("mail.smtp.port", "25"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
		return props;
	}

	public static String getSenderMail() {
		return senderMail;
	}

	public static void setSenderMail(final String senderMail) {
		PropertiesLoader.senderMail = senderMail;
	}

}
