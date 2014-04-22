package org.larinde.epay.proc.domain.model;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.PaymentFlow;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public final class AuthorizePaymentFlowRequest extends AbstractPaymentFlowRequest {

	protected final BigDecimal amount;

	protected final String currency;

	protected final String description;

	protected final String email;

	protected final String msisdn;

	public AuthorizePaymentFlowRequest(PaymentFlow paymentFlow, String version, String requestId, DateTime communicationDate, String username, String password, String merchantId, BigDecimal amount, String currency, String description, String email, String msisdn) {
		super(paymentFlow, version, requestId, communicationDate, username, password, merchantId);
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.email = email;
		this.msisdn = msisdn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public static class Builder {

		private PaymentFlow paymentFlow;
		private String version;
		private String requestId;
		private DateTime communicationDate;
		private String username;
		private String password;
		private String merchantId;
		private BigDecimal amount;
		private String currency;
		private String description;
		private String email;
		private String msisdn;

		public Builder paymentFlow(PaymentFlow paymentFlow) {
			this.paymentFlow = paymentFlow;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder requestId(String requestId) {
			this.requestId = requestId;
			return this;
		}

		public Builder communicationDate(DateTime communicationDate) {
			this.communicationDate = communicationDate;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder merchantId(String merchantId) {
			this.merchantId = merchantId;
			return this;
		}

		public Builder amount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public Builder currency(String currency) {
			this.currency = currency;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder msisdn(String msisdn) {
			this.msisdn = msisdn;
			return this;
		}

		public AuthorizePaymentFlowRequest build() {
			return new AuthorizePaymentFlowRequest(paymentFlow, version, requestId, communicationDate, username, password, merchantId, amount, currency, description, email, msisdn);
		}

	}

}
