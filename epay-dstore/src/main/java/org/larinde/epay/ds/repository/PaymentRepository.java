package org.larinde.epay.ds.repository;

import java.util.Date;
import java.util.List;

import org.larinde.epay.ds.domain.Payment;
import org.larinde.epay.ds.domain.PaymentFlow;
import org.larinde.epay.ds.domain.PaymentStatus;
import org.springframework.data.repository.Repository;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public interface PaymentRepository extends Repository<Payment, Long> {

	public Payment save(Payment payment);
	
	public void update(Payment payment);
	
	public List<Payment> findByPaymentFlow(PaymentFlow paymentFlow);

	public List<Payment> findByStatus(PaymentStatus status);

	public List<Payment> findByDate(Date from, Date to, Integer currentPage, Integer pageSize);
	
	public Payment findById(Long id);
}
