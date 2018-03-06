/**
 * 
 */
package br.ufpi.loes.oracleTest.common.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Rony
 *
 */
public class EmailUtilConnector {
	private static final String USER = "delfos-oracle@gmail.com";
	private static final String PASSWORD = "Oracle01!";

	public EmailUtilConnector() {

	}

	private static Session autenticate() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASSWORD);
			}
		});

		return session;
	}

	public static void sendEmail(String destinatary, String subject, String text) {
		try {
			Message message = new MimeMessage(autenticate());
			message.setFrom(new InternetAddress(USER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatary));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
