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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((merchantId == null) ? 0 : merchantId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + (vaild ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MerchantCredential other = (MerchantCredential) obj;
		if (merchantId == null) {
			if (other.merchantId != null)
				return false;
		} else if (!merchantId.equals(other.merchantId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (vaild != other.vaild)
			return false;
		return true;
	}

}
