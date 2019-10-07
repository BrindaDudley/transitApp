package com.tts.techtalentTransitpp.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.techtalentTransitpp.Dtos.DistanceMatrix;
import com.tts.techtalentTransitpp.Dtos.GeocodeResponse;
import com.tts.techtalentTransitpp.Dtos.Location;



@Service
public class MapService implements IMapServices {
	
	@Value("${geocode_url}")
	private String geocode;
	
	@Value("${api_key}")
	private String apiKey;
	
	@Value("${distance_matrix}")
	private String distanceMatrix;
	
	public Location getCoordinates(String address) {
		RestTemplate restTemplate=new RestTemplate();
		String url= geocode + address +"+GA"+"&key="+apiKey;
		GeocodeResponse geocodeResponse=restTemplate.getForObject(url,GeocodeResponse.class);
		if(geocodeResponse!=null && geocodeResponse.getResults().size()>0 && geocodeResponse.getResults()!=null)
		{
	     Location location= geocodeResponse.getResults().get(0).getGeometry().getLocation();
		
		
		return location;
		}
	
		return null;
	}
	
	public double getDistace(Location origin,Location destination) {
		String url=distanceMatrix+origin.getLat()+","+origin.getLng()+"&destinations="+destination.getLat()+","+destination.getLng()+"&key="+apiKey;
		RestTemplate restTemplate=new RestTemplate();
		DistanceMatrix distanceMatrix=restTemplate.getForObject(url,DistanceMatrix.class);
		if(distanceMatrix!=null) {
			Long distance=distanceMatrix.getRows().get(0).getElemnets().get(0).getDistance().getValue();
			return distance*0.0006211371;
			
		}
		return (Double) null;
	}

}
