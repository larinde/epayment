package org.larinde.epay.ds.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.larinde.epay.ds.domain.Consumer;
import org.larinde.epay.ds.domain.Payment;
import org.springframework.stereotype.Repository;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Repository
public class ConsumerRepositoryImpl extends AbstractEpayRepository implements ConsumerRepository {

	@Override
	public Consumer save(Consumer consumer) {
		if (consumer.getId() == null) {
			em.persist(consumer);
		} else {
			em.merge(consumer);
		}
		return consumer;
	}

	@Override
	public void update(Consumer consumer) {
		em.merge(consumer);
	}

	@Override
	public Consumer findByEmail(String email) {
		TypedQuery<Consumer> query = em.createNamedQuery(Consumer.FIND_CONSUMER_BY_EMAIL, Consumer.class);
		query.setParameter(Consumer.CONSUMER_EMAIL_PARAM, email);
		return query.getSingleResult();
	}

	@Override
	public Consumer findByMsisdn(String msisdn) {
		TypedQuery<Consumer> query = em.createNamedQuery(Consumer.FIND_CONSUMER_BY_MSISDN, Consumer.class);
		query.setParameter(Consumer.CONSUMER_MSISDN_PARAM, msisdn);
		return query.getSingleResult();
	}
	
	@Override
	public Consumer findById(Long id) {
		return em.find(Consumer.class, id);
	}

}
