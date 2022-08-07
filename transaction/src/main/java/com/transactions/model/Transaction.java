package com.transactions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

	private Double amount;

	private Long timestamp;

	@JsonCreator
	public Transaction(@JsonProperty("timestamp") Long timestamp, @JsonProperty("amount") Double amount) {
		this.timestamp = timestamp;
		this.amount = amount;
	}

}
