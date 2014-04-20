package org.larinde.epay.ds.repository;


import org.larinde.epay.ds.domain.Consumer;
import org.springframework.data.repository.Repository;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public interface ConsumerRepository extends Repository<Consumer, Long> {
	public Consumer save(Consumer consumer);
	
	public void update(Consumer consumer);
	
	public Consumer findByEmail(String email);

	public Consumer findByMsisdn(String msisdn);
	
	public Consumer findById(Long id);

}
