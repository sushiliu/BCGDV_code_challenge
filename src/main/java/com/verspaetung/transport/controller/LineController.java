package com.verspaetung.transport.controller;

import java.sql.Time;

import com.verspaetung.transport.domain.Line;
import com.verspaetung.transport.repository.LineRepository;
import com.verspaetung.transport.response.FindDelayResponseBody;
import com.verspaetung.transport.exception.LineNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineController {
	private LineRepository repository;

	public LineController(LineRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "/lines", params = { "time", "posX", "posX" })
	Line findByTimeAndPos(@RequestParam Time time, @RequestParam int posX, @RequestParam int posY) {
		return repository.findByTimeAndPos(time, posX, posY)
				.orElseThrow(() -> new LineNotFoundException("time=" + time + "&posX=" + posX + "&posY=" + posY));
	}

	@GetMapping(value = "/lines", params = "stopId")
	Line findByStop(@RequestParam int stopId) {
		return repository.findByStop(stopId)
				.orElseThrow(() -> new LineNotFoundException("stopId=" + stopId));
	}

	@GetMapping(value = "/lines", params = "lineId")
	FindDelayResponseBody findDelayByLineId(@RequestParam int lineId) {
		Line line = repository.findById(lineId)
						.orElseThrow(()-> new LineNotFoundException( "Line Id: " + lineId));
		int delay = repository.findDelayByLineName(line.getLine_name()).orElse(0);
		return new FindDelayResponseBody(lineId, line.getLine_name(), delay!=0, delay);
	}

	@GetMapping(value = "/lines", params = "lineName")
	FindDelayResponseBody findDelayByLineName(@RequestParam String lineName) {
		Line line = repository.findLineByName(lineName)
						.orElseThrow(()-> new LineNotFoundException( "Line Name: " + lineName));
		int delay = repository.findDelayByLineName(lineName).orElse(0);
		return new FindDelayResponseBody(line.getLine_id(), lineName, delay!=0, delay);
	}

	
}