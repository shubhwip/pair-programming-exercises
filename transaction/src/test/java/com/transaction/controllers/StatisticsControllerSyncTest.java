package com.transaction.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.dto.StatisticsDTO;
import com.transactions.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class StatisticsControllerSyncTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void simpleTestWithTransactionsBoundedToSixtySeconds() throws Exception {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		Transaction transaction1 = new Transaction(currentTimeStampInMills, 20.0);
		Transaction transaction2 = new Transaction(currentTimeStampInMills + 1, 40.0);
		Transaction transaction3 = new Transaction(currentTimeStampInMills + 2, 30.0);
		Transaction transaction4 = new Transaction(currentTimeStampInMills + 3, 10.0);
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction1)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction2)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction3)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction4)));
		StatisticsDTO expectedStatisticsDTO = new StatisticsDTO(100.0, 25.0, 40.0, 10.0, 4);
		this.mockMvc.perform(get("/api/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("sum", is(expectedStatisticsDTO.getSum())))
				.andExpect(jsonPath("avg", is(expectedStatisticsDTO.getAvg())))
				.andExpect(jsonPath("max", is(expectedStatisticsDTO.getMax())))
				.andExpect(jsonPath("min", is(expectedStatisticsDTO.getMin())))
				.andExpect(jsonPath("count", is((int) expectedStatisticsDTO.getCount())));
	}

	@Test
	public void simpleTestWithTransactionsPostedAtSameTimeStampBoundedToSixtySeconds() throws Exception {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		Transaction transaction1 = new Transaction(currentTimeStampInMills, 20.0);
		Transaction transaction2 = new Transaction(currentTimeStampInMills, 40.0);
		Transaction transaction3 = new Transaction(currentTimeStampInMills + 2, 30.0);
		Transaction transaction4 = new Transaction(currentTimeStampInMills + 2, 10.0);
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction1)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction2)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction3)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction4)));
		StatisticsDTO expectedStatisticsDTO = new StatisticsDTO(100.0, 25.0, 40.0, 10.0, 4);
		this.mockMvc.perform(get("/api/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("sum", is(expectedStatisticsDTO.getSum())))
				.andExpect(jsonPath("avg", is(expectedStatisticsDTO.getAvg())))
				.andExpect(jsonPath("max", is(expectedStatisticsDTO.getMax())))
				.andExpect(jsonPath("min", is(expectedStatisticsDTO.getMin())))
				.andExpect(jsonPath("count", is((int) expectedStatisticsDTO.getCount())));
	}

	@Test
	public void simpleTestWithTransactionsWithSameAmountBoundedToSixtySeconds() throws Exception {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		Transaction transaction1 = new Transaction(currentTimeStampInMills, 20.0);
		Transaction transaction2 = new Transaction(currentTimeStampInMills + 1, 20.0);
		Transaction transaction3 = new Transaction(currentTimeStampInMills + 2, 30.0);
		Transaction transaction4 = new Transaction(currentTimeStampInMills + 3, 30.0);
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction1)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction2)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction3)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction4)));
		StatisticsDTO expectedStatisticsDTO = new StatisticsDTO(100.0, 25.0, 30.0, 20.0, 4);
		this.mockMvc.perform(get("/api/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("sum", is(expectedStatisticsDTO.getSum())))
				.andExpect(jsonPath("avg", is(expectedStatisticsDTO.getAvg())))
				.andExpect(jsonPath("max", is(expectedStatisticsDTO.getMax())))
				.andExpect(jsonPath("min", is(expectedStatisticsDTO.getMin())))
				.andExpect(jsonPath("count", is((int) expectedStatisticsDTO.getCount())));
	}

	@Test
	public void simpleTestWithTransactionsOutsideToSixtySeconds() throws Exception {
		Long currentTimeStampInMills = Instant.now().toEpochMilli();
		Transaction transaction1 = new Transaction(currentTimeStampInMills, 20.0);
		Transaction transaction2 = new Transaction(currentTimeStampInMills, 40.0);
		Transaction transaction3 = new Transaction(currentTimeStampInMills, 30.0);
		Transaction transaction4 = new Transaction(currentTimeStampInMills + 60 * 1000, 10.0);
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction1)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction2)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction3)));
		mockMvc.perform(post("/api/transactions").header("Content-Type", "application/json")
				.content(asJsonString(transaction4)));
		TimeUnit.SECONDS.sleep(60); // This Sleep is to pass all 1 seconds old transactions
		StatisticsDTO expectedStatisticsDTO = new StatisticsDTO(10.0, 10.0, 10.0, 10.0, 1);
		this.mockMvc.perform(get("/api/statistics").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("sum", is(expectedStatisticsDTO.getSum())))
				.andExpect(jsonPath("avg", is(expectedStatisticsDTO.getAvg())))
				.andExpect(jsonPath("max", is(expectedStatisticsDTO.getMax())))
				.andExpect(jsonPath("min", is(expectedStatisticsDTO.getMin())))
				.andExpect(jsonPath("count", is((int) expectedStatisticsDTO.getCount())));
	}

	@Test
	public void simpleTestWithTransactionsPostedAtSameTimeStampOutsideToSixtySeconds() throws Exception {
	}

	@Test
	public void simpleTestWithTransactionsWithSameAmountOutsideToSixtySeconds() {

	}

	public static String asJsonString(final Object transaction) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(transaction);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
