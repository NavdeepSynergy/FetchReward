package com.coding.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.coding.domain.Payer;

@Service
public class FetchRewardService {

	Comparator<Payer> compareRecord = new Comparator<Payer>() {
		@Override
		public int compare(Payer o1, Payer o2) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dateTime1 = LocalDateTime.parse(o1.getTransactionDate(), formatter);
			LocalDateTime dateTime2 = LocalDateTime.parse(o2.getTransactionDate(), formatter);

			if (dateTime1.isBefore(dateTime2)) {
				return -1;
			} else {
				return 1;
			}
		}
	};

	// PriorityQueue to maintain the order FIFO
	PriorityQueue<Payer> recordQueue = new PriorityQueue<>(compareRecord);

	// Map to maintain the points for every payer
	Map<String, Integer> map = new HashMap<>();

	public void addPoints(Payer payer) {
		recordQueue.add(payer);

		if (!map.containsKey(payer.getPayerName())) {
			map.put(payer.getPayerName(), payer.getPoints());
		} else {
			map.put(payer.getPayerName(), map.get(payer.getPayerName()) + payer.getPoints());
		}
	}

	public List<Payer> deduct(int points) {

		int pointsCount = points;

		// List of records to be returned from which points are deducted
		List<Payer> list = new ArrayList<>();

		while (pointsCount != 0) {

			Payer p = recordQueue.peek();

			// if points is in negative then
			if (p.getPoints() > 0) {

				if (p.getPoints() <= pointsCount) {

					Payer addToList = new Payer();
					addToList.setPayerName(p.getPayerName());
					addToList.setPoints(p.getPoints());
					addToList.setTransactionDate("now");
					list.add(addToList);

					// update the map for given payer
					map.put(p.getPayerName(), 0);

					// update the points
					pointsCount = pointsCount - p.getPoints();

					// remove the record from the queue
					recordQueue.poll();
				} else {

					// add to the list to be returned
					Payer addToList = new Payer();
					addToList.setPayerName(p.getPayerName());
					addToList.setPoints(-pointsCount);
					addToList.setTransactionDate("now");
					list.add(addToList);

					// update the remaining points in the record
					int updatePoints = p.getPoints() - pointsCount;
					pointsCount = 0;
					p.setPoints(updatePoints);

					map.put(p.getPayerName(), updatePoints);
				}
			} else {
				recordQueue.poll();

			}
		}
		return list;
	}

	public Map<String, Integer> getPoints() {
		return map;
	}

	public PriorityQueue<Payer> getAll() {
		return recordQueue;
	}

}

