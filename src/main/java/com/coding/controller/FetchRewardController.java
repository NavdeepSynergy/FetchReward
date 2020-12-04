package com.coding.controller;


import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.domain.Payer;
import com.coding.service.FetchRewardService;

@RestController
public class FetchRewardController {

	@Autowired
	FetchRewardService fetchRewardService;

	@PostMapping("/addPoints")
	public ResponseEntity<String> addPoints(@RequestBody Payer payer) {
		fetchRewardService.addPoints(payer);
		return new ResponseEntity<String>("Added", HttpStatus.OK);
	}

	@PostMapping("/deductPoints")
	public ResponseEntity<List<Payer>> deduct(@RequestParam int points) {

		List<Payer> list = fetchRewardService.deduct(points);
		return new ResponseEntity<List<Payer>>(list, HttpStatus.OK);
	}

	@GetMapping("/getPoints")
	public ResponseEntity<Map<String, Integer>> getPoints() {
		Map<String, Integer> map = fetchRewardService.getPoints();
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<PriorityQueue<Payer>> getAll() {
		PriorityQueue<Payer> queue = fetchRewardService.getAll();
		return new ResponseEntity<PriorityQueue<Payer>>(queue, HttpStatus.OK);
	}

}
