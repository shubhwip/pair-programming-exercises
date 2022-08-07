package com.transaction.controllers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.transaction.service.TransactionService;

//https://stackoverflow.com/questions/48432652/can-webmvctest-have-two-controllers?noredirect=1&lq=1
@RunWith(SpringRunner.class)
@WebMvcTest({ StatisticsController.class, TransactionController.class })
public abstract class AbstractTest {

	@MockBean
	public TransactionService transactionService;

}
