package com.tts.techtalentTransitpp.Services;

import java.util.List;
import com.tts.techtalentTransitpp.Dtos.Bus;

public interface IbusService {

	public List<Bus> getAllBuses();
	

	 public List<Bus> getNearbyBuses(BusRequest request);

}
