package com.transaction.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URISyntaxException;
import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactions.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerSyncTest {

	@Autowired
	private MockMvc mockMvc;

	private static final Long MILLISECONDS_IN_ONE_SEC = 60 * 1000L;

	@Test
	public void testDoTransactionInSixtySeconds() throws URISyntaxException, Exception {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		Transaction transaction = new Transaction(currentTimeStampInMills, 15.0);
		String transactionString = asJsonString(transaction);
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json").content(transactionString))
				.andExpect(status().isCreated()).andExpect(content().bytes(new byte[0]));
	}

	@Test
	public void testDoTransactionBeforeSixtySeconds() throws URISyntaxException, Exception {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		Long sixtySecondsAgoTimStampInMills = currentTimeStampInMills - MILLISECONDS_IN_ONE_SEC;
		Transaction transaction = new Transaction(sixtySecondsAgoTimStampInMills, 15.0);
		String transactionString = asJsonString(transaction);
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json").content(transactionString))
				.andExpect(status().isNoContent());

	}

	public static String asJsonString(final Transaction transaction) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(transaction);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
