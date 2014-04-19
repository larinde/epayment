package org.larinde.epay.proc.domain.model;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class MerchantCredential {

	private final String username;
	private final String password;
	private final String merchantId;
	private final boolean vaild;

	public MerchantCredential(final String username, final String password, final String merchantId, final boolean vaild) {
		super();
		this.username = username;
		this.password = password;
		this.merchantId = merchantId;
		this.vaild = vaild;
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

	public boolean isVaild() {
		return vaild;
	}

}
