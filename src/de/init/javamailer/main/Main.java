package de.init.javamailer.main;

import java.io.IOException;
import java.util.ArrayList;

import de.init.javamailer.loader.FileReader;
import de.init.javamailer.mail.TLSEmail;
import de.init.javamailer.util.PropertiesLoader;

public class Main {

	private static String path = "files/recipients.txt";

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
			PropertiesLoader.setSenderMail(args[3]);
		} else {
			PropertiesLoader.setSenderMail(username);
		}

		try {

			final TLSEmail tlsEmail = new TLSEmail(username, password, delay);

			final ArrayList<String> recipients = new FileReader().readFile(path);
			System.out.println(recipients.size() + " recipient(s) have been loaded.");

			tlsEmail.sendmails(recipients);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static void showErrorMessageAndExit() {
		System.out.println(
				"Please give in valid server information for the smtp connection and delay in ms between requests.\nFormat should be like: username password delay");
		System.exit(1);
	}

}
