package org.larinde.epay.ds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Entity
public class Merchant extends AbstractEntity {

	private static final long serialVersionUID = 2914388374322334696L;
	@Column(nullable = false, unique = true)
	private String merchantId;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Boolean active;

	public Merchant() {
		super();
	}

	public Merchant(String merchantId, String username, String password, Boolean active) {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
