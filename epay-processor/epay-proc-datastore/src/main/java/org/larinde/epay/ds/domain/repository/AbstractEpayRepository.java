package org.larinde.epay.ds.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public abstract class AbstractEpayRepository {

	@PersistenceContext
	protected EntityManager em;

}
