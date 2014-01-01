package org.cresse.garage.alert;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cresse.garage.service.GarageService;
import org.cresse.garage.vo.DoorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class GaragePoller {
	
	@Autowired
	private GarageService garageService;
	
	private Log log = LogFactory.getLog(GaragePoller.class);
	
	private DoorStatus lastStatus = DoorStatus.UNKNOWN;
	
	private List<GarageStatusListener> listeners;
	
	public GaragePoller(List<GarageStatusListener> listeners) {
		this.listeners = listeners;
	}
	
	@Scheduled(fixedDelay=5000)
	public void checkDoorStatus() {
		DoorStatus currentStatus = garageService.getDoorStatus();
		log.debug("Current status: "+currentStatus);
		if(currentStatus != lastStatus) {
			notifyStatusChange(lastStatus, currentStatus);
		}
		lastStatus = currentStatus;
	}

	private void notifyStatusChange(DoorStatus lastStatus, DoorStatus currentStatus) {
		log.info(String.format("Status changed from %s to %s", lastStatus, currentStatus));
		if(listeners != null) {
			for (GarageStatusListener listener : listeners) {
				listener.onStatusChange(lastStatus, currentStatus);
			}
		}
	}
	
}
