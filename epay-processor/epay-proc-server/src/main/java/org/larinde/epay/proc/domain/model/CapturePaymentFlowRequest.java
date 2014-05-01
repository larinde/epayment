package org.larinde.epay.proc.domain.model;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class CapturePaymentFlowRequest extends AbstractPaymentFlowRequest {

	public CapturePaymentFlowRequest(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate, String username, String password, String merchantId) {
		super(paymentFlow, version, requestId, communicationDate, username, password, merchantId);
		// TODO Auto-generated constructor stub
	}

}
