package org.cresse.garage;

public class DummyGarageService implements GarageService {
	
	private Garage garage = new Garage(DoorStatus.CLOSED);

	@Override
	public Garage getGarage() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return garage;
	}

	@Override
	public void toggleGarageDoor() {
		if(garage.getStatus()==DoorStatus.CLOSED) {
			garage.setStatus(DoorStatus.OPEN);
		} else {
			garage.setStatus(DoorStatus.CLOSED);
		}
	}

}
