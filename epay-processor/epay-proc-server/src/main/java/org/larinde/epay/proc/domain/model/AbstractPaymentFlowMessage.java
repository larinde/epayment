package org.larinde.epay.proc.domain.model;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

public class AbstractPaymentFlowMessage {

	protected final PaymentFlow paymentFlow;
	protected final String version;
	protected final String requestId;
	protected final DateTime communicationDate;

	public AbstractPaymentFlowMessage(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate) {
		super();
		this.paymentFlow = paymentFlow;
		this.version = version;
		this.requestId = requestId;
		this.communicationDate = communicationDate;
	}

	public PaymentFlow getPaymentFlow() {
		return paymentFlow;
	}

	public String getVersion() {
		return version;
	}

	public String getRequestId() {
		return requestId;
	}

	public DateTime getCommunicationDate() {
		return communicationDate;
	}

}