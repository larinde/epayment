package org.larinde.epay.ds.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = Payment.FIND_PAYMENT_BY_DATE_RANGE, query = "SELECT p FROM Payment p WHERE p.date BETWEEN :fromDate AND :toDate"), @NamedQuery(name = Payment.FIND_PAYMENT_BY_STATUS, query = "SELECT p FROM Payment p WHERE p.status = :status"), @NamedQuery(name = Payment.FIND_PAYMENT_BY_FLOW, query = "SELECT p FROM Payment p WHERE p.paymentFlow = :paymentFlow") })
public class Payment extends AbstractEntity {

	private static final long serialVersionUID = 5747593650555733394L;

	public static final String FIND_PAYMENT_BY_DATE_RANGE = "findPaymentByDateRange";
	public static final String FROM_DATE_PARAM = "fromDate";
	public static final String TO_DATE_PARAM = "toDate";
	public static final String FIND_PAYMENT_BY_STATUS = "findPaymentByStatus";
	public static final String PAYMENT_STATUS_PARAM = "status";
	public static final String FIND_PAYMENT_BY_FLOW = "findPaymentByFlow";
	public static final String PAYMENT_FLOW_PARAM = "paymentFlow";
	@Column(nullable = false, unique = true)
	private String transactionId;
	@Column(nullable = false, unique = false)
	private String sessionId;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private String merchantId;
	private String serviceType;
	@Enumerated(EnumType.STRING)
	private PaymentFlow paymentFlow;
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private String currency;
	private String description;

	protected Payment() {
		super();
	}

	public Payment(String sessionId, String transactionId, PaymentStatus status, Date date, String merchantId, String serviceType, PaymentFlow paymentFlow, BigDecimal amount, String currency, String description) {
		super();
		this.sessionId = sessionId;
		this.transactionId = transactionId;
		this.status = status;
		this.date = date;
		this.merchantId = merchantId;
		this.serviceType = serviceType;
		this.paymentFlow = paymentFlow;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public PaymentFlow getPaymentFlow() {
		return paymentFlow;
	}

	public void setPaymentFlow(PaymentFlow paymentFlow) {
		this.paymentFlow = paymentFlow;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
