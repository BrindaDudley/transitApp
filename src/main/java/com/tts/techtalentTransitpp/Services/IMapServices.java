package com.tts.techtalentTransitpp.Services;

import com.tts.techtalentTransitpp.Dtos.Location;

public interface IMapServices {
	
	public Location getCoordinates(String address);
	public double getDistace(Location origin,Location destination);

}
