package org.larinde.epay.proc.application;

import org.larinde.epay.proc.domain.model.MerchantCredential;
import org.larinde.epay.proc.domain.service.MerchantValidationService;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class MerchantValidationServiceImpl implements MerchantValidationService {

	@Override
	public boolean validMerchant(MerchantCredential credential) {
		return true;
	}

}
