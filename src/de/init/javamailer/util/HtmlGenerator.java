package de.init.javamailer.util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

public class HtmlGenerator {

	private final String cid = "radio_image";
	private final String subject = "]init[RADIO kommt!";

	private String generateHTMLBodyContent() {
		return "<html><head>" + "<title>Radio</title>" + "</head>\n"
				+ "<body><div><strong>&hellip; fast ist es soweit!</strong></div><br>"
				+ "<div>Was als Feierabend-Schnapsidee begann, nimmt tats&auml;chlich Realit&auml;t an: ]init[ bekommt sein eigenes Netz-RADIO.</div>\n"
				+ "<div>Das Programm ist von Euch/f&uuml;r Euch und damit f&uuml;r jeden etwas dabei ist, bef&uuml;llt den music_loader. Ihr k&ouml;nnt mp3&acute;s einstellen, ganze spotify-playlisten oder Ihr nennt Alben-Titel oder Filmmusike. Ums Rechtliche k&uuml;mmern wir uns.</div><br>\n"
				+ "<div>..und hier geht`s zum  <a href=\"https://www.google.de\">music_loader</a></div><br>\n"
				+ "<div><img src=\"cid:" + cid + "\" /></div><br>\n" + "<div>Viele Gr&uuml;&szlig;e</div><br>\n"
				+ "<div>Harald&amp;Thoralf</div>\n" + "</body></html>";
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