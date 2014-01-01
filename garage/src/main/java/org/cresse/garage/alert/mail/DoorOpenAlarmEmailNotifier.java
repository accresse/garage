package org.cresse.garage.alert.mail;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cresse.garage.alert.GarageStatusListener;
import org.cresse.garage.vo.DoorStatus;
import org.springframework.mail.SimpleMailMessage;

public class DoorOpenAlarmEmailNotifier extends EmailEnabledComponent implements GarageStatusListener {

	private Log log = LogFactory.getLog(DoorOpenAlarmEmailNotifier.class);
	
	private long alarmThreshhold = 15 * 60 * 1000; //15 Minutes
	private Timer timer = new Timer(this.getClass().getName()+" - Timer");
	private TimerTask alarmTask = null;
	
	public void setAlarmThreshhold(long alarmThreshhold) {
		this.alarmThreshhold = alarmThreshhold;
	}

	@Override
	public void onStatusChange(DoorStatus lastStatus, DoorStatus currentStatus) {
		if(currentStatus==DoorStatus.CLOSED) {
			stopTimer();
		} else {
			startTimer();
		}
	}

	private void startTimer() {
		log.debug("Starting alarm countdown...");
		alarmTask = new TimerTask() {
			
			@Override
			public void run() {
				log.info("Sending door open alarm");
				SimpleMailMessage message = getTemplateMessage();
				message.setText("Garage has been open too long!");
				sendMail(message);
			}
		};
		
		timer.schedule(alarmTask, alarmThreshhold);
	}

	private void stopTimer() {
		if(alarmTask != null) {
			alarmTask.cancel();
			log.debug("Stopped alarm countdown");
		}
	}
	
}
