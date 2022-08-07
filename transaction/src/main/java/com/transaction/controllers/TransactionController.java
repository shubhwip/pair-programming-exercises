package com.transaction.controllers;

import java.time.Instant;
import java.util.List;
import java.util.NavigableMap;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.transactions.model.Transaction;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class TransactionController extends AbstractController {

	private static final Long MILLISECONDS_IN_ONE_SEC = 60 * 1000L;

	@RequestMapping(path = "transactions", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<HttpStatus> doTransactions(@Valid @RequestBody final Transaction transaction) {
		final Long currentTimeInMills = Instant.now().toEpochMilli(); // Current moment in UTC.
		log.debug("The Current Time is {}", currentTimeInMills);
		if (transaction.getTimestamp() > (currentTimeInMills - MILLISECONDS_IN_ONE_SEC)) {
			log.info("Saving Transaction to In Memory Database: {}", transaction);
			transactionService.saveTransaction(transaction);
			if (transactionService.getTransactions() != null
					&& transactionService.getTransactions().containsKey(transaction.getTimestamp())) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} else {
			log.error("Transaction Failed : Timestamp {} was lesser than one second", transaction.getTimestamp());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(path = "alltransactions", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public NavigableMap<Long, List<Double>> getAllTransactions() {
		return transactionService.getTransactions();
	}

}
