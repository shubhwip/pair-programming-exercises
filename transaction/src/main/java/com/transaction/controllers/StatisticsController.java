package com.transaction.controllers;

import java.time.Instant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.dto.StatisticsDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class StatisticsController extends AbstractController {

	@RequestMapping(path = "statistics", method = RequestMethod.GET)
	@ResponseBody
	public StatisticsDTO getStatistics() {
		Long currentTimeInMills = Instant.now().toEpochMilli(); // Current moment in UTC.
		log.info("Getting transaction summary of last 60 seconds");
		return transactionService.getStats(currentTimeInMills);
	}

}
