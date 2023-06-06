package rs.tim14.xml.service;

import java.io.File;
import java.util.Objects;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;
import rs.tim14.xml.model.autorska_prava.ResenjeZahteva;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	private final Environment env;

	@Async
	public void sendMailWithAttachment(String emailPodnosioca, ResenjeZahteva resenjeZahteva, byte[] bajt) throws MailException, MessagingException {
		try {

			MimeMessage mailMessage = new MimeMessage(Session.getDefaultInstance(System.getProperties()));
			mailMessage.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
			mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailPodnosioca));
			mailMessage.setSubject("Obradjen zahtev za autorsko pravo broj " + resenjeZahteva.getBrojPrijave());
			MimeBodyPart attachmentPart = new MimeBodyPart();
			DataSource source = new ByteArrayDataSource(bajt, "application/octet-stream");
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName("Resenje o zahtevu.pdf");
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(getEmailBody(resenjeZahteva.isOdbijen()));
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);
			mailMessage.setContent(multipart);
			javaMailSender.send(mailMessage);
		} catch (MessagingException e) {
			throw new MessagingException();
		}
	}

	private String getEmailBody(boolean odbijen) {
		if (odbijen) {
			return """
                    Poštovani,


                    Obaveštavamo Vas da je Vaš zahtev za unošenje u evidenciju i deponovanje autorskih dela ODBIJEN. Razlog odbijanja možete videti u prilogu koji Vam šaljemo.
                    
                    Takodje, ukoliko imate neke nedoumice, budite slobodni da se obradite službeniku koji je obradio zahtev (mejl se nalazi u prilogu).

                    Srdačan pozdrav,
                    Echo""";
		}
		return """
                Poštovani,

                 Obaveštavamo Vas da je Vaš zahtev za unošenje u evidenciju i deponovanje autorskih dela ODOBREN. Šaljemo Vam prilog gde možete dobiti detaljnije informacije.
                    
                 Takodje, ukoliko imate neke nedoumice, budite slobodni da se obradite službeniku koji je obradio zahtev (mejl se nalazi u prilogu).

                Srdačan pozdrav,
                Echo""";
	}
}
