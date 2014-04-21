package org.larinde.epay.proc.application;

import org.larinde.epay.ds.domain.Merchant;
import org.larinde.epay.ds.domain.repository.MerchantRepository;
import org.larinde.epay.proc.domain.model.MerchantCredential;
import org.larinde.epay.proc.domain.service.MerchantValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Service
public class MerchantValidationServiceImpl implements MerchantValidationService {
	
	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public boolean validMerchant(MerchantCredential credential) {
		Merchant merchant = merchantRepository.findByMerchantId(credential.getUsername());
		if (merchant.isActive()&(merchant.getPassword().equals(credential.getPassword()))) {
			return true;
		}
		return false;
	}

}
