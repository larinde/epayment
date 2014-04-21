package org.larinde.epay.ds.domain.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.larinde.epay.ds.AbstractIntegrationTest;
import org.larinde.epay.ds.domain.Consumer;
import org.larinde.epay.ds.domain.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class ConsumerRepositoryImplTest extends AbstractIntegrationTest{

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Test
	public void shouldBeCorrectlyWired() {
		assertNotNull(consumerRepository);
	}
	
	@Test 
	public void shouldSavaNewConsumer() {
		BigDecimal balance = new BigDecimal(3000.83).setScale(2, BigDecimal.ROUND_UP);
		String msisdn = "43123456";
		String email = "testconsumer@epay.com";
		Consumer consumer = new Consumer(email, msisdn, balance, true);
		assertThat(consumer.getId(), is(nullValue()));
		consumerRepository.save(consumer);
		assertThat(consumer.getId(), is(notNullValue()));
		assertThat(consumer.getId(), is(greaterThan(Long.valueOf(2))));
	}
	
	@Test
	public void shouldFindConsumerByEmail() {
		String email = "larinde.java@gmail.com";
		BigDecimal expectedBalance = new BigDecimal(70000.37).setScale(2, BigDecimal.ROUND_UP);
		Consumer consumer = consumerRepository.findByEmail(email);
		assertThat(consumer, is(notNullValue()));
		assertThat(consumer.getEmail(), is(equalTo(email)));
		assertThat(consumer.getBalance(), is(equalTo(expectedBalance)));
	}
	
	@Test
	public void shouldFindConsumerByMsisdn() {
		String msisdn = "44497620973131";
		String expectedEmail = "larinde.soa@gmail.com";
		Consumer consumer = consumerRepository.findByMsisdn(msisdn);
		assertThat(consumer, is(notNullValue()));
		assertThat(consumer.getEmail(), is(equalTo(expectedEmail)));
	}
	
	@Test
	public void shouldUpdateExistingConsumer() {
		BigDecimal amountToDeduct = new BigDecimal(13.50).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal expectedBalance = new BigDecimal(30.15).setScale(2, BigDecimal.ROUND_UP);
		String expectedEmail = "larinde.soa@gmail.com";
		Consumer consumer = consumerRepository.findById(2L);
		assertThat(consumer, is(notNullValue()));
		assertThat(consumer.getEmail(), is(equalTo(expectedEmail)));
		assertThat(consumer.getBalance(), is(equalTo(expectedBalance)));
		
		consumer.setBalance(expectedBalance.subtract(amountToDeduct));
		consumerRepository.update(consumer);
		assertThat(consumer.getBalance(), is(equalTo(expectedBalance.subtract(amountToDeduct))));
	}
	
	

	
}
