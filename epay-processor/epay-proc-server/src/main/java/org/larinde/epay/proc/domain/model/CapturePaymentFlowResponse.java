package org.larinde.epay.proc.domain.model;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class CapturePaymentFlowResponse extends AbstractPaymentFlowResponse {

	public CapturePaymentFlowResponse(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate, String statusCode, String statusText) {
		super(paymentFlow, version, requestId, communicationDate, statusCode, statusText);
		// TODO Auto-generated constructor stub
	}

}
