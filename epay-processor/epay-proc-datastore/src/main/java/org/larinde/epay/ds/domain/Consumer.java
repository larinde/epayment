package org.larinde.epay.ds.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = Consumer.FIND_CONSUMER_BY_EMAIL, query = "SELECT cn FROM Consumer cn WHERE cn.email = :email"), 
	@NamedQuery(name = Consumer.FIND_CONSUMER_BY_MSISDN, query = "SELECT cn FROM Consumer cn WHERE cn.msisdn = :msisdn") 
})
public class Consumer extends AbstractEntity {
	private static final long serialVersionUID = -8419720277381288177L;
	public static final String FIND_CONSUMER_BY_EMAIL = "findConsumerByEmail";
	public static final String CONSUMER_EMAIL_PARAM = "email";
	public static final String FIND_CONSUMER_BY_MSISDN = "findConsumerByMsisdn";
	public static final String CONSUMER_MSISDN_PARAM = "msisdn";

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String msisdn;
	@Column(nullable = false)
	private BigDecimal balance;

	public Consumer() {
		super();
	}

	public Consumer(String email, String msisdn, BigDecimal balance) {
		super();
		this.email = email;
		this.msisdn = msisdn;
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
