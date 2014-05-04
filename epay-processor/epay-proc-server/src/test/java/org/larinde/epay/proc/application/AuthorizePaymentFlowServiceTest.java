package org.larinde.epay.proc.application;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.larinde.epay.ds.domain.Consumer;
import org.larinde.epay.ds.domain.PaymentFlow;
import org.larinde.epay.ds.domain.repository.ConsumerRepository;
import org.larinde.epay.ds.domain.repository.PaymentRepository;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowRequest;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowResponse;
import org.larinde.epay.proc.domain.model.MerchantCredential;
import org.larinde.epay.proc.domain.model.PaymentServiceException;
import org.larinde.epay.proc.domain.service.AuthenticationService;
import org.larinde.epay.proc.domain.service.MerchantValidationService;
import org.larinde.epay.proc.domain.service.PaymentIdService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorizePaymentFlowServiceTest {

	@InjectMocks
	private AuthorizePaymentFlowService authorizePaymentFlowService;
	@Mock
	private PaymentIdService paymentIdService;
	@Mock
	private MerchantValidationService merchantValidationService;
	@Mock
	private PaymentRepository paymentRepository;
	@Mock
	private ConsumerRepository consumerRepository;
	@Mock
	private AuthenticationService authenticationTokenService;
	private static final String VALID_CONSUMER_EMAIL = "valid_consumer@test.com";
	private static final String VALID_CONSUMER_MSISDN = "07417383713";
	private static BigDecimal SUFFICIENT_BALANCE = new BigDecimal("27.30");
	private static final boolean ACTIVE_CONSUMER = true;
	private static final String VALID_USERNAME = "validuser";
	private static final String VALID_PASSWORD = "validpass";
	private static final String VALID_MERCHANTID = "MER-01";
	private static final String INVALID_MERCHANTID = "invalid";
	private static final String INVALID_PASSWORD = "invalid";
	private static final String INVALID_USERNAME = "invalid";
	private Consumer validConsumer;
	private MerchantCredential validMerchantCredential;
	private MerchantCredential invalidMerchantCredential;

	@Before
	public void setUp() throws Exception {
		authenticationTokenService = new AuthenticationTokenServiceImpl();
		
		validConsumer = new Consumer(VALID_CONSUMER_EMAIL, VALID_CONSUMER_EMAIL, SUFFICIENT_BALANCE, ACTIVE_CONSUMER);
		when(consumerRepository.findByEmail(VALID_CONSUMER_EMAIL)).thenReturn(validConsumer);
		
		validMerchantCredential = new MerchantCredential(VALID_USERNAME, VALID_PASSWORD, VALID_MERCHANTID, false);
		when(merchantValidationService.validMerchant(validMerchantCredential)).thenReturn(true);
		
		invalidMerchantCredential = new MerchantCredential(INVALID_USERNAME, INVALID_PASSWORD, INVALID_MERCHANTID, false);
		when(merchantValidationService.validMerchant(invalidMerchantCredential)).thenReturn(false);

	}

	@Test
	public void shouldSuccessfullyExecuteAnAuthorizationRequest() throws PaymentServiceException {
		PaymentFlow paymentFlow = PaymentFlow.AUTHORIZE;
		String version = "1.00";
		String requestId = "3333333";
		DateTime communicationDate = DateTime.now();
		String currency = "GBP";
		String description = "Raspberry Pi";
		AuthorizePaymentFlowRequest request = new AuthorizePaymentFlowRequest(paymentFlow, version, requestId, communicationDate, VALID_USERNAME, VALID_PASSWORD, VALID_MERCHANTID, SUFFICIENT_BALANCE, currency, description, VALID_CONSUMER_EMAIL, VALID_CONSUMER_MSISDN);
		
		AuthorizePaymentFlowResponse response = authorizePaymentFlowService.execute(request);
		
		assertThat(response, is(notNullValue()));
		assertThat(response.getRequestId(), is(equalTo(requestId)));
		assertThat(response.getVersion(), is(equalTo(version)));
	}

	@Test(expected=PaymentServiceException.class)
	public void shouldFailForInvalidMerchantAuthorizationRequest() throws PaymentServiceException {
		PaymentFlow paymentFlow = PaymentFlow.AUTHORIZE;
		String version = "1.00";
		String requestId = "3333333";
		DateTime communicationDate = DateTime.now();
		String currency = "GBP";
		String description = "Raspberry Pi";
		AuthorizePaymentFlowRequest request = new AuthorizePaymentFlowRequest(paymentFlow, version, requestId, communicationDate, INVALID_USERNAME, INVALID_PASSWORD, INVALID_MERCHANTID, SUFFICIENT_BALANCE, currency, description, VALID_CONSUMER_EMAIL, VALID_CONSUMER_MSISDN);
		
		authorizePaymentFlowService.execute(request);
	}
	
}
