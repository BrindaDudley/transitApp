package com.tts.techtalentTransitpp.Services;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.techtalentTransitpp.Dtos.Bus;
import com.tts.techtalentTransitpp.Dtos.Location;



@Service
public class BusService implements IbusService{
	
	@Value("${get_all_bus_url}")
	private String getAllBusURL;
	
	@Autowired
	private IbusService ibusService;
	
	@Autowired
	private IMapServices iMapServices;
	
	
	  public List<Bus> getAllBuses() {
	        RestTemplate restTemplate = new RestTemplate();
	        @SuppressWarnings("unchecked")
	        Map<String, String>[] buses = restTemplate.getForObject(getAllBusURL, Map[].class);
	        return Arrays.asList(buses).stream().map(busMap -> new Bus(busMap)).collect(Collectors.toList());
	    }
	
	  public List<Bus> getNearbyBuses(BusRequest request){
			List<Bus> allBuses = ibusService.getAllBuses();
			Location personLocation = iMapServices.getCoordinates(request.address + " " + request.city);
			List<Bus> nearbyBuses = new ArrayList<>();
			for(Bus bus : allBuses) {
				Location busLocation = new Location();
				busLocation.lat = bus.getLATITUDE();
				busLocation.lng = bus.getLONGITUDE();
				double latDistance = Double.parseDouble(busLocation.lat) - Double.parseDouble(personLocation.lat);
				double lngDistance = Double.parseDouble(busLocation.lng) - Double.parseDouble(personLocation.lng);
				if (Math.abs(latDistance) <= 0.02 && Math.abs(lngDistance) <= 0.02) {
					double distance = iMapServices.getDistace(busLocation, personLocation);
					if (distance <= 1) {
						bus.distance = (double) Math.round(distance * 100) / 100;
						nearbyBuses.add(bus);
					}
				}
			}
			Collections.sort(nearbyBuses, new BusComparator());
			return nearbyBuses;
		}

}
