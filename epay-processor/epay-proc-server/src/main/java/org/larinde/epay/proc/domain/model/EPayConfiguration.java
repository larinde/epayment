package org.larinde.epay.proc.domain.model;

import org.springframework.stereotype.Component;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Component
public class EPayConfiguration {
	private static final int DEFAULT_TPS = 10;
	private static final long DEFAULT_TRANSACTION_PERIOD_IN_MILLISECOND = 1000;
	private Integer slaTransactionsPerSecond;

	public int getSLATransactionsPerSecond() {
		if (slaTransactionsPerSecond == null) {
			return DEFAULT_TPS;
		}
		return slaTransactionsPerSecond;
	}

	public long getSLATransactionPeriodMillis() {
		return DEFAULT_TRANSACTION_PERIOD_IN_MILLISECOND;
	}

}
