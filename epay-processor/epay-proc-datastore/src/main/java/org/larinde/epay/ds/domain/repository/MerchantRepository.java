package org.larinde.epay.ds.domain.repository;

import org.larinde.epay.ds.domain.Merchant;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public interface MerchantRepository extends Repository<Merchant, Long> {

	public Merchant save(Merchant merchant);

	public void update(Merchant merchant);

	public Merchant findByMerchantId(@Param("merchantId") String merchantId);

	public Merchant findById(Long id);

}
