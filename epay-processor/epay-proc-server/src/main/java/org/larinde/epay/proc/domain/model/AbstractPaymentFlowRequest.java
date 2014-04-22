package org.larinde.epay.proc.domain.model;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public abstract class AbstractPaymentFlowRequest extends AbstractPaymentFlowMessage {

	protected final String username;
	protected final String password;
	protected final String merchantId;

	public AbstractPaymentFlowRequest(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate, String username, String password, String merchantId) {
		super(paymentFlow, version, requestId, communicationDate);
		this.username = username;
		this.password = password;
		this.merchantId = merchantId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMerchantId() {
		return merchantId;
	}

}
