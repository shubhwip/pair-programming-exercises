package com.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatisticsDTO {

	private final double sum;
	private final double avg;
	private final double max;
	private final double min;
	private final long count;

	public StatisticsDTO(double sum, double avg2, double max2, double min2, long count) {
		this.sum = sum;
		this.avg = avg2;
		this.max = max2;
		this.min = min2;
		this.count = count;
	}

}
