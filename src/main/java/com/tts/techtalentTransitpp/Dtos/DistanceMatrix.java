package com.tts.techtalentTransitpp.Dtos;


import java.util.List;

import lombok.Data;

@Data
public class DistanceMatrix {
	
	private List<String> destination_addresses;
	private List<String> origin_addresses;
	private List<Row> rows;
	

}
