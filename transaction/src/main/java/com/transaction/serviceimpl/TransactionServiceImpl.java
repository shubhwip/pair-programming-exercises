package com.transaction.serviceimpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.transaction.dto.StatisticsDTO;
import com.transaction.service.TransactionService;
import com.transactions.model.Transaction;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableScheduling
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private static final Long MILLISECONDS_IN_ONE_SEC = 60 * 1000L;

	// create MultiMap to store time stamp and amount at that timestamp
	NavigableMap<Long, List<Double>> transactionsNav = new TreeMap<>();

	private Map<Long, StatisticsDTO> statistics = new HashMap<>();

	@Override
	public void saveTransaction(Transaction transaction) {
		log.info("Saving Transaction");
		if (transactionsNav.get(transaction.getTimestamp()) != null) {
			transactionsNav.get(transaction.getTimestamp()).add(transaction.getAmount());
		} else {
			List<Double> amounts = new ArrayList<>();
			amounts.add(transaction.getAmount());
			transactionsNav.put(transaction.getTimestamp(), amounts);
		}
		log.info("Current Multimap is {}", transactionsNav);
	}

	@Override
	public NavigableMap<Long, List<Double>> getTransactions() {
		log.info("Multimap of all transactions {}", transactionsNav);
		return transactionsNav;
	}

	@Synchronized
	@Scheduled(fixedRate = 1L)
	void calculateStatsEachMilliSecond() {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		addNewTransactionEachMilliSecond(currentTimeStampInMills);
		removeTransactionsOlderThanSixtySeconds(currentTimeStampInMills);
	}

	@Override
	public StatisticsDTO getStats(Long currentTimeInMills) {
		log.info("The current timestamp is {}", currentTimeInMills);
		log.info("Current statistic is {}", statistics.get(currentTimeInMills));
		return this.statistics.get(currentTimeInMills);
	}

	private void removeTransactionsOlderThanSixtySeconds(Long currentTimeStampInMills) {
		Long sixtySecondsAgoTimStampInMills = currentTimeStampInMills - MILLISECONDS_IN_ONE_SEC;
			this.transactionsNav.headMap(sixtySecondsAgoTimStampInMills, false)
					.forEach((k, v) -> transactionsNav.remove(k));
		log.debug("Executing remove method at TimeStamp {}", currentTimeStampInMills);
	}

	private void addNewTransactionEachMilliSecond(Long currentTimeStampInMills) {
		// https://stackoverflow.com/questions/31559046/use-stream-to-sum-all-values-from-array-stored-in-map
		Long sixtySecondsAgoTimStampInMills = currentTimeStampInMills - MILLISECONDS_IN_ONE_SEC;
		Map<Long, List<Double>> transacts = this.transactionsNav.subMap(sixtySecondsAgoTimStampInMills, true,
				currentTimeStampInMills, false);
		log.debug("Executing add method at TimeStamp {}", currentTimeStampInMills);
		DoubleSummaryStatistics stats = transacts.values().stream()
				.flatMapToDouble(arr -> arr.stream().mapToDouble(Double::valueOf)).summaryStatistics();
		statistics.put(currentTimeStampInMills, new StatisticsDTO(stats.getSum(), stats.getAverage(), stats.getMax(),
				stats.getMin(), stats.getCount()));
	}

}
