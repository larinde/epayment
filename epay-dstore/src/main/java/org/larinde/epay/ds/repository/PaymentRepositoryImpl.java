package org.larinde.epay.ds.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.larinde.epay.ds.domain.Payment;
import org.larinde.epay.ds.domain.PaymentFlow;
import org.larinde.epay.ds.domain.PaymentStatus;
import org.springframework.stereotype.Repository;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Repository
public class PaymentRepositoryImpl extends AbstractEpayRepository implements PaymentRepository {

	@Override
	public Payment save(Payment payment) {
		if (payment.getId() == null) {
			em.persist(payment);
		} else {
			em.merge(payment);
		}
		return payment;
	}

	@Override
	public void update(Payment payment) {
		em.merge(payment);
	}

	@Override
	public List<Payment> findByPaymentFlow(PaymentFlow paymentFlow) {
		TypedQuery<Payment> query = em.createNamedQuery(Payment.FIND_PAYMENT_BY_FLOW, Payment.class);
		query.setParameter(Payment.PAYMENT_FLOW_PARAM, paymentFlow);
		return query.getResultList();
	}

	@Override
	public List<Payment> findByStatus(PaymentStatus status) {
		TypedQuery<Payment> query = em.createNamedQuery(Payment.FIND_PAYMENT_BY_STATUS, Payment.class);
		query.setParameter(Payment.PAYMENT_STATUS_PARAM, status);
		return query.getResultList();
	}

	@Override
	public List<Payment> findByDate(Date from, Date to, Integer currentPage, Integer pageSize) {
		TypedQuery<Payment> query = em.createNamedQuery(Payment.FIND_PAYMENT_BY_DATE_RANGE, Payment.class);
		query.setParameter(Payment.FROM_DATE_PARAM, from);
		query.setParameter(Payment.TO_DATE_PARAM, to);
		query.setFirstResult(currentPage);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Payment findById(Long id) {
		return em.find(Payment.class, id);
	}

}
