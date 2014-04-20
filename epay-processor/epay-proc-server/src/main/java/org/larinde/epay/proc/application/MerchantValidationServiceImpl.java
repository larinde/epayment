package org.larinde.epay.proc.application;

import org.larinde.epay.proc.domain.model.MerchantCredential;
import org.larinde.epay.proc.domain.service.MerchantValidationService;
import org.springframework.stereotype.Service;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Service
public class MerchantValidationServiceImpl implements MerchantValidationService {

	@Override
	public boolean validMerchant(MerchantCredential credential) {
		return true;
	}

}
