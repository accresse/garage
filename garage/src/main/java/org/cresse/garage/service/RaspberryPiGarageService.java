package org.cresse.garage.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cresse.garage.vo.DoorStatus;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;

public class RaspberryPiGarageService implements GarageService {
	
	private enum RelayAction {
		UP(0), DOWN(1), PRESS(2);
		
		private int value;
		
		private RelayAction(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
			
	private static final Log log = LogFactory.getLog(RaspberryPiGarageService.class);

	private GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalInput doorSensor;
	private Serial serial = SerialFactory.createInstance();
	private String comPort = "/dev/ttyACM0";
	private int relayPin = 8;
	
	public RaspberryPiGarageService() {
		doorSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);
	}
	
	public void setComPort(String comPort) {
		this.comPort = comPort;
	}

	public void setRelayPin(int relayPin) {
		this.relayPin = relayPin;
	}

	@Override
	public DoorStatus getDoorStatus() {
		return mapStatus(doorSensor.getState());
	}

	@Override
	public void toggleGarageDoor() {
		log.debug("Toggling garage door...");
		if(!serial.isOpen()) {
			initSerialPort();
		}
		serial.write(getCommand());
		char result = serial.read();
		log.debug("Result: "+result);
	}
	
	private void initSerialPort() {
		serial.open(comPort, 9600);
	}

	private String getCommand() {
		return relayPin+","+RelayAction.PRESS.getValue()+"\n";
	}
	
	private DoorStatus mapStatus(PinState state) {
		switch(state) {
			case HIGH: return DoorStatus.OPEN;
			case LOW: return DoorStatus.CLOSED;
			default: return DoorStatus.UNKNOWN;
		}
	}

}
