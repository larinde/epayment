package org.larinde.epay.proc.domain.service;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public interface PaymentIdService {

	public static final String TRANSACTION_ID_PREFIX = "EPAY_TRN_";
	public static final String SESSION_ID_PREFIX = "EPAY_SID_";

	public String generateSessionId();

	public String generateTransactionId();
}
