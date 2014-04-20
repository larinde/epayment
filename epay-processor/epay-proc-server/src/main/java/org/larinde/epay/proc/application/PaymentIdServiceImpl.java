package org.larinde.epay.proc.application;

import java.util.concurrent.atomic.AtomicLong;

import org.larinde.epay.proc.domain.service.PaymentIdService;
import org.springframework.stereotype.Service;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Service
public class PaymentIdServiceImpl implements PaymentIdService {
	
	private final AtomicLong identifier = new AtomicLong(10000);

	@Override
	public String generateSessionId() {
		return SESSION_ID_PREFIX + String.valueOf(identifier.getAndIncrement());
	}

	@Override
	public String generateTransactionId() {
		return TRANSACTION_ID_PREFIX  + String.valueOf(identifier.getAndIncrement());
	}

}
