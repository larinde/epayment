package org.larinde.epay.proc.domain.model;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public final class AuthorizePaymentFlowResponse extends AbstractPaymentFlowResponse {
	
	protected final String transId;
	protected final  String sessionId;
	protected final  byte[] authToken;

	public AuthorizePaymentFlowResponse(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate, String statusCode, String statusText, String transId, String sessionId, byte[] authToken) {
		super(paymentFlow, version, requestId, communicationDate, statusCode, statusText);
		this.transId = transId;
		this.sessionId = sessionId;
		this.authToken = authToken;
	}

}
