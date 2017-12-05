package de.init.javamailer.mail;

import java.util.ArrayList;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import de.init.javamailer.util.ContentHolder;
import de.init.javamailer.util.EmailUtil;
import de.init.javamailer.util.PropertiesLoader;

public class TLSEmail {

	private final String username;
	private final String password;
	private final int delay;
	private ContentHolder contentHolder;

	public TLSEmail(final String username, final String password, final int delay) {
		this.username = username;
		this.password = password;
		this.delay = delay;
	}

	public void setContent(final ContentHolder contentHolder) {
		this.contentHolder = contentHolder;
	}

	public void sendmails(final ArrayList<String> receipients) {

		// create Authenticator object to pass in Session.getInstance argument
		final Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		final Session session = Session.getInstance(PropertiesLoader.getProperties(), auth);
		for (final String receipient : receipients) {
			try {
				EmailUtil.sendEmail(session, receipient, contentHolder.getSubject(), contentHolder.getBody());
				if (delay > 0) {
					Thread.sleep(delay);
				}
			} catch (final Exception e) {
				System.out.println("Mail sending failed for: " + receipient);
				e.printStackTrace();
			}
		}
	}
}