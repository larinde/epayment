package org.larinde.epay.ds.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.larinde.epay.ds.domain.Consumer;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public abstract class AbstractEpayRepository {

	@PersistenceContext
	protected EntityManager em;

}
