package com.transaction.service;

import java.util.List;
import java.util.NavigableMap;

import com.transaction.dto.StatisticsDTO;
import com.transactions.model.Transaction;

public interface TransactionService {

	/**
	 * This method used to save transaction in memory database
	 * 
	 * @param transaction
	 */
	void saveTransaction(Transaction transaction);

	NavigableMap<Long, List<Double>> getTransactions();
	
	/**
	 * This method is used for statistics
	 * */
	StatisticsDTO getStats(Long currentTimeInMills);

}
