package com.tts.techtalentTransitpp.Dtos;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bus {
	
	private String ADHERENCE;
	private String BLOCKID;

	private String BLOCK_ABBR;

	private String DIRECTION;

	private String LATITUDE;

	private String LONGITUDE;

	private String MSGTIME;

	private String ROUTE;

	private String STOPID;
	private String TIMEPOINT;
	private String TRIPID;
	private String VEHICLE;
	public double distance;

	public Bus(Map<String, String> busMap) {
        ADHERENCE = busMap.get("ADHERENCE");
        BLOCKID = busMap.get("BLOCKID");
        BLOCK_ABBR = busMap.get("BLOCK_ABBR");
        DIRECTION = busMap.get("DIRECTION");
        LATITUDE =  busMap.get("LATITUDE");
        LONGITUDE = busMap.get("LONGITUDE");
        MSGTIME = busMap.get("MSGTIME");
        ROUTE = busMap.get("ROUTE");
        STOPID = busMap.get("STOPID");
        TIMEPOINT = busMap.get("TIMEPOINT");
        TRIPID = busMap.get("TRIPID");
        VEHICLE = busMap.get("VEHICLE");
    }

}
