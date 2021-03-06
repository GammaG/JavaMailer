package de.init.javamailer.mail;

import java.util.ArrayList;
import java.util.Random;

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
		int i = 1;
		final Random random = new Random();
		for (final String recipient : recipients) {
			try {
				EmailUtil.sendEmail(session, recipient);
				if (delay > 0) {
					Thread.sleep(delay + getNewRandom(random, 100, 2000));
				}
				if (++i % 10 == 0) {
					System.out.println(i + "/" + recipient.length());
				}
			} catch (final Exception e) {
				System.out.println("Mail sending failed for: " + recipient);
				e.printStackTrace();
			}
		}
	}

	private int getNewRandom(final Random random, final int low, final int high) {
		return (low >= high) ? 0 : random.nextInt(high - low) + low;
	}
}
