package org.cresse.garage.alert;

import org.cresse.garage.vo.DoorStatus;

public interface GarageStatusListener {

	void onStatusChange(DoorStatus lastStatus, DoorStatus currentStatus);

}
