package org.cresse.garage.service;

import org.cresse.garage.vo.DoorStatus;


public interface GarageService {
		
	public DoorStatus getDoorStatus();
	
	public void toggleGarageDoor();

}
