package org.cresse.garage.alert.mail;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public abstract class EmailEnabledComponent {

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;
	
	@Required
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Required
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}
	
	public SimpleMailMessage getTemplateMessage() {
		return new SimpleMailMessage(templateMessage);
	}

	public void sendMail(SimpleMailMessage message) {
		mailSender.send(message);
	}
	
}
