package com.transaction.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.transaction.serviceimpl.TransactionServiceImpl;

public abstract class AbstractController {

	@Autowired
	TransactionServiceImpl transactionService;

}
