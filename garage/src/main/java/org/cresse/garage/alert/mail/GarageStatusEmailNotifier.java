package org.cresse.garage.alert.mail;

import org.cresse.garage.alert.GarageStatusListener;
import org.cresse.garage.vo.DoorStatus;
import org.springframework.mail.SimpleMailMessage;

public class GarageStatusEmailNotifier extends EmailEnabledComponent implements GarageStatusListener {
	
	@Override
	public void onStatusChange(DoorStatus lastStatus, DoorStatus currentStatus) {
		SimpleMailMessage message = getTemplateMessage();
		message.setText("Garage is now "+currentStatus);
		sendMail(message);
	}

}
