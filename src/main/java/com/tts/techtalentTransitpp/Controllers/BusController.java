package com.tts.techtalentTransitpp.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalentTransitpp.Dtos.Bus;
import com.tts.techtalentTransitpp.Services.BusRequest;
import com.tts.techtalentTransitpp.Services.IbusService;

@Controller
public class BusController {
	
	@Autowired
	private IbusService ibusService;
	
	@GetMapping("/buses")
	public String getBusesPage(Model model ,BusRequest busRequest){
		model.addAttribute("request",busRequest);
		return "index";
	}
	
	@PostMapping("/buses")
	public String getNearbyBuses(BusRequest request, Model model) {
		List<Bus> buses = ibusService.getNearbyBuses(request);
		model.addAttribute("buses", buses);
		model.addAttribute("request", request);
		return "index";
	}

}
