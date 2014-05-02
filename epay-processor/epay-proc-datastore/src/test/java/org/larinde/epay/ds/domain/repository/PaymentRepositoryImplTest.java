package org.larinde.epay.ds.domain.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.larinde.epay.ds.AbstractIntegrationTest;
import org.larinde.epay.ds.domain.Consumer;
import org.larinde.epay.ds.domain.Payment;
import org.larinde.epay.ds.domain.PaymentFlow;
import org.larinde.epay.ds.domain.PaymentStatus;
import org.larinde.epay.ds.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class PaymentRepositoryImplTest extends AbstractIntegrationTest {
	
	@Autowired
	private PaymentRepository paymentRepository;

	
	@Test
	public void shouldBeCorrectlyWired() {
		assertNotNull(paymentRepository);
	}

	@Test
	public void shouldSaveNewPayment() {
		Consumer consumer = new Consumer("testconsumer@epay.com", "497634872373", new BigDecimal(3000.83).setScale(2, BigDecimal.ROUND_UP), true);
		Payment payment = new Payment("SID_1234567", "TRN_1234567", PaymentStatus.OPEN, new Date(), "ABC123", PaymentFlow.AUTHORIZE, new BigDecimal("13.35"), "USD", "some item", consumer);
		assertThat(payment.getId(), is(nullValue()));
		paymentRepository.save(payment);
		assertThat(payment.getId(), is(notNullValue()));
		assertThat(payment.getId(), is(greaterThan(Long.valueOf(3))));
	}

	@Test
	public void shouldUpdateExistingPayment() {
		Payment payment = paymentRepository.findById(1l);
		assertThat(payment, is(notNullValue()));
		assertThat(payment.getStatus(), is(equalTo(PaymentStatus.OPEN)));

		payment.setStatus(PaymentStatus.PENDING);
		paymentRepository.update(payment);
		assertThat(paymentRepository.findById(1l).getStatus(), is(equalTo(PaymentStatus.PENDING)));
	}

	@Test
	public void shouldFetchPaymentsByFlowType() {
		List<Payment> capturedPayments = paymentRepository.findByPaymentFlow(PaymentFlow.CAPTURE);
		assertEquals("should return 2 captured payments", 2, capturedPayments.size());
	}

	@Test
	public void shouldFetchPaymentsByByStatus() {
		List<Payment> failedPayments = paymentRepository.findByStatus(PaymentStatus.FAILED);
		assertEquals("should return 1 failed payments", 1, failedPayments.size());
	}

	@Test
	public void shouldFindPaymentTransactionsByDate() {
		DateTime fromDate = new DateTime(2001, 1, 30, 13, 57);
		DateTime toDate = new DateTime(2007, 12, 31, 23, 59);
		List<Payment> payments = paymentRepository.findByDate(fromDate.toDate(), toDate.toDate(), 0, 10);
		assertEquals("should return 3 payment transactions", 3, payments.size());
	}

	@Test
	public void shouldFetchPaymentById() {
		Payment payment = paymentRepository.findById(3l);
		assertThat(payment, is(notNullValue()));
		assertThat(payment.getStatus(), is(equalTo(PaymentStatus.DONE)));
		assertThat(payment.getPaymentFlow(), is(equalTo(PaymentFlow.CAPTURE)));
	}

}
