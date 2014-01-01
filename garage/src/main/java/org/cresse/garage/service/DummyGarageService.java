package org.cresse.garage.service;

import org.cresse.garage.vo.DoorStatus;

public class DummyGarageService implements GarageService {
	
	private DoorStatus lastStatus = DoorStatus.UNKNOWN;
	
	@Override
	public void toggleGarageDoor() {
		if(lastStatus == DoorStatus.CLOSED) {
			lastStatus = DoorStatus.OPEN;
		} else {
			lastStatus = DoorStatus.CLOSED;
		}
	}

	@Override
	public DoorStatus getDoorStatus() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return lastStatus;
	}

}
