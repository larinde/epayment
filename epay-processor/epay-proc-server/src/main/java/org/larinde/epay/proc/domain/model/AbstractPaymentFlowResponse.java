package org.larinde.epay.proc.domain.model;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public abstract class AbstractPaymentFlowResponse extends AbstractPaymentFlowMessage {

	protected String statusCode;
	protected String statusText;

	public AbstractPaymentFlowResponse(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate, String statusCode, String statusText) {
		super(paymentFlow, version, requestId, communicationDate);
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

}
