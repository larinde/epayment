package org.larinde.epay.proc.domain.service;

import org.larinde.epay.proc.domain.model.MerchantCredential;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public interface MerchantValidationService {
	
	public boolean validMerchant(MerchantCredential credential);

}
