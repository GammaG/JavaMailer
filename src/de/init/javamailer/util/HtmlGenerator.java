package de.init.javamailer.util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class HtmlGenerator {

	private final String cid = "radio_image";
	private final String subject = "]init[RADIO kommt!";

	private String generateHTMLBodyContent() {
		return "<html><head>" + "<title>This is not usually displayed</title>" + "</head>\n"
				+ "<body><div><strong>Hi there!</strong></div>"
				+ "<div>Sending HTML in email is so <em>cool!</em> </div>\n"
				+ "<div>And here's an image: <img src=\"cid:" + cid + "\" /></div>n"
				+ "<div>I hope you like it!</div></body></html>";
	}

	public String getCid() {
		return cid;
	}

	public MimeBodyPart generateHTMLBodyPart() throws MessagingException {
		final MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(generateHTMLBodyContent(), "US-ASCII", "html");
		return messageBodyPart;
	}

	public MimeBodyPart generateHTMLImagePart() throws IOException, MessagingException {
		final MimeBodyPart imagePart = new MimeBodyPart();
		imagePart.attachFile("files/image.png");
		imagePart.setContentID("<" + cid + ">");
		imagePart.setDisposition(MimeBodyPart.INLINE);
		return imagePart;
	}

	public String getSubject() {
		return subject;
	}

}