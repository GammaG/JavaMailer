package de.init.javamailer.mail;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import de.init.javamailer.util.HtmlGenerator;
import de.init.javamailer.util.PropertiesLoader;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 */
	public static void sendEmail(final Session session, final String toEmail) {
		try {
			final MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(PropertiesLoader.getSenderMail(), "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse(PropertiesLoader.getSenderMail(), false));

			final HtmlGenerator htmlGenerator = new HtmlGenerator();

			msg.setSubject(htmlGenerator.getSubject(), "UTF-8");

			// Create a multipart message for inline image
			final MimeMultipart content = new MimeMultipart("related");

			content.addBodyPart(htmlGenerator.generateHTMLBodyPart());
			content.addBodyPart(htmlGenerator.generateHTMLImagePart());

			// Set the multipart message to the email message
			msg.setContent(content);

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			Transport.send(msg);

			System.out.println("EMail to " + toEmail + " was send.");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}