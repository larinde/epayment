package org.larinde.epay.ds.domain.repository;

import javax.persistence.TypedQuery;

import org.larinde.epay.ds.domain.Merchant;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class MerchantRepositoryImpl extends AbstractEpayRepository implements MerchantRepository {

	@Override
	public Merchant save(Merchant merchant) {
		if (merchant.getId() == null) {
			em.persist(merchant);
		} else {
			em.merge(merchant);
		}
		return merchant;
	}

	@Override
	public void update(Merchant merchant) {
		em.merge(merchant);
	}

	@Override
	public Merchant findByMerchantId(String merchantId) {
		TypedQuery<Merchant> query = em.createNamedQuery(Merchant.FIND_MERCHANT_BY_ID, Merchant.class);
		query.setParameter(Merchant.MERCHANTID_PARAM, merchantId);
		return query.getSingleResult();
	}

	@Override
	public Merchant findById(Long id) {
		return em.find(Merchant.class, id);
	}

}
