package de.init.javamailer.main;

import java.util.ArrayList;

import de.init.javamailer.mail.TLSEmail;
import de.init.javamailer.util.ContentHolder;
import de.init.javamailer.util.PropertiesLoader;

public class Main {

	public static void main(final String[] args) {
		String username = "";
		String password = "";
		int delay = 0;

		if (args.length == 0 || args.length < 3) {
			showErrorMessageAndExit();
		} else {
			username = args[0];
			password = args[1];
			delay = new Integer(args[2]);
		}

		// set custom address to send from
		if (args.length >= 4) {
			PropertiesLoader.setSenderMail(args[4]);
		} else {
			PropertiesLoader.setSenderMail(username);
		}

		final TLSEmail tlsEmail = new TLSEmail(username, password, delay);

		// Tests
		final ContentHolder contentHolder = createDummyContent();
		final ArrayList<String> recipients = createDummyRecipients();

		tlsEmail.setContent(contentHolder);
		tlsEmail.sendmails(recipients);

	}

	private static void showErrorMessageAndExit() {
		System.out.println(
				"Please give in valid server information for the smtp connection and delay in ms between requests.\nFormat should be like: username password delay");
		System.exit(1);
	}

	private static ArrayList<String> createDummyRecipients() {
		final ArrayList<String> receipients = new ArrayList<>();
		receipients.add("marco.seidler@init.de");
		return receipients;
	}

	private static ContentHolder createDummyContent() {
		final ContentHolder contentHolder = new ContentHolder();
		contentHolder.setSubject("testlauf");
		contentHolder.setBody("testlauf");
		return contentHolder;
	}

}
