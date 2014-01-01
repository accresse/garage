package org.cresse.garage.alert.mail;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;

public class GmailSessionFactory implements FactoryBean<Session>{
	
	private String username;
	private String password;
	
	@Required
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Required
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Session getObject() throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		return session;
	}

	@Override
	public Class<?> getObjectType() {
		return Session.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
