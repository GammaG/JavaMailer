package de.init.javamailer.mail;

import java.util.ArrayList;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import de.init.javamailer.util.PropertiesLoader;

public class TLSEmail {

	private final String username;
	private final String password;
	private final int delay;

	public TLSEmail(final String username, final String password, final int delay) {
		this.username = username;
		this.password = password;
		this.delay = delay;
	}

	@SuppressWarnings("unused")
	public void sendmails(final ArrayList<String> recipients) {

		// create Authenticator object to pass in Session.getInstance argument
		final Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		final Session session = Session.getInstance(PropertiesLoader.getProperties(), auth);
		final int i = 1;
		for (final String recipient : recipients) {
			try {
				EmailUtil.sendEmail(session, recipient);
				if (delay > 0) {
					Thread.sleep(delay);
				}
				if (i % 10 == 0) {
					System.out.println(i + "/" + recipient.length());
				}
			} catch (final Exception e) {
				System.out.println("Mail sending failed for: " + recipient);
				e.printStackTrace();
			}
		}
	}
}