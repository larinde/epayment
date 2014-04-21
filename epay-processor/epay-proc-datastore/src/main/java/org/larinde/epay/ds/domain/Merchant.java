package org.larinde.epay.ds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = Merchant.FIND_MERCHANT_BY_ID, query = "SELECT m FROM Merchant m WHERE m.merchantId = :merchantId") })
public class Merchant extends AbstractEntity {

	private static final long serialVersionUID = 2914388374322334696L;
	@Column(nullable = false, unique = true)
	private String merchantId;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private boolean active;

	public static final String FIND_MERCHANT_BY_ID = "findMerchantById";
	public static final String MERCHANTID_PARAM = "merchantId";

	public Merchant() {
		super();
	}

	public Merchant(String merchantId, String username, String password, boolean active) {
		super();
		this.merchantId = merchantId;
		this.username = username;
		this.password = password;
		this.active = active;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
