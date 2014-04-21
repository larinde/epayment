package org.larinde.epay.ds.domain.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.larinde.epay.ds.AbstractIntegrationTest;
import org.larinde.epay.ds.domain.Merchant;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class MerchantRepositoryImplTest extends AbstractIntegrationTest {

	@Autowired
	private MerchantRepository merchantRepository;

	
	@Test
	public void correctlyWired() {
		assertNotNull(merchantRepository);
	}

	@Test
	public void shouldSaveNewMerchant() {
		Merchant merchant = new Merchant("MER77", "testmerchant", "testmerchant", true);
		assertThat(merchant.getId(), is(nullValue()));
		merchantRepository.save(merchant);
		assertThat(merchant.getId(), is(notNullValue()));
	}

	@Test
	public void shouldFindMerchantByMerchantId() {
		String merchantId = "MER03";
		String expectedUsername = "amazon";
		Merchant merchant = merchantRepository.findByMerchantId(merchantId);
		assertThat(merchant, is(notNullValue()));
		assertThat(merchant.getUsername(), is(equalToIgnoringCase(expectedUsername)));
	}
	
	@Test
	public void shouldDeactivateMerchant() {
		boolean inactiveState = false;
		String merchantId = "MER01";
		Merchant merchant = merchantRepository.findByMerchantId(merchantId);
		assertThat(merchant.isActive(), is(not(inactiveState)));
		
		merchant.setActive(inactiveState);
		merchantRepository.update(merchant);
		assertThat(merchant.isActive(), is(equalTo(inactiveState)));
	}

}
