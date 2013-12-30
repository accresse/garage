package org.cresse.garage;

public class Garage {
	
	private DoorStatus status;
	
	public Garage(DoorStatus status) {
		this.status = status;
	}
	
	public DoorStatus getStatus()  {
		return status;
	}
	
	public void setStatus(DoorStatus status) {
		this.status = status;
	}

}
