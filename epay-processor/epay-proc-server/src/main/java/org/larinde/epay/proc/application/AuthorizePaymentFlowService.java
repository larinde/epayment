package org.larinde.epay.proc.application;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.larinde.epay.ds.domain.Consumer;
import org.larinde.epay.ds.domain.Payment;
import org.larinde.epay.ds.domain.PaymentFlow;
import org.larinde.epay.ds.domain.PaymentStatus;
import org.larinde.epay.ds.domain.repository.ConsumerRepository;
import org.larinde.epay.ds.domain.repository.PaymentRepository;
import org.larinde.epay.proc.domain.exception.AuthTokenGenerationFailureException;
import org.larinde.epay.proc.domain.exception.ConsumerHasInsufficientFundsException;
import org.larinde.epay.proc.domain.exception.InvalidMerchantCredentialsException;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowRequest;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowResponse;
import org.larinde.epay.proc.domain.model.MerchantCredential;
import org.larinde.epay.proc.domain.model.PaymentServiceException;
import org.larinde.epay.proc.domain.service.AuthenticationService;
import org.larinde.epay.proc.domain.service.MerchantValidationService;
import org.larinde.epay.proc.domain.service.PaymentFlowService;
import org.larinde.epay.proc.domain.service.PaymentIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Service
public class AuthorizePaymentFlowService implements PaymentFlowService<AuthorizePaymentFlowResponse, AuthorizePaymentFlowRequest> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizePaymentFlowService.class);

	@Autowired
	private AuthenticationService authenticationTokenService;
	@Autowired
	private PaymentIdService paymentIdService;
	@Autowired
	private MerchantValidationService merchantValidationService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ConsumerRepository consumerRepository;

	@Override
	public AuthorizePaymentFlowResponse execute(AuthorizePaymentFlowRequest request) throws PaymentServiceException {
		LOGGER.info("processing authorize request");

		AuthorizePaymentFlowResponse response = null;
		try {
			if (validMerchant(request)) {
				final Consumer consumer = getConsumer(request);
				if (consumerHasEnoughFund(consumer, request.getAmount())) {
					final String transId = paymentIdService.generateTransactionId();
					final String sessionId = paymentIdService.generateSessionId();
					final byte[] authToken = authenticationTokenService.generateToken(transId);

					Payment payment = new Payment(sessionId, transId, PaymentStatus.PENDING, DateTime.now().toDate(), request.getMerchantId(), PaymentFlow.AUTHORIZE, request.getAmount(), request.getCurrency(), request.getDescription(), consumer);
					paymentRepository.save(payment);
					LOGGER.info("saved payment: {}", ToStringBuilder.reflectionToString(payment, ToStringStyle.DEFAULT_STYLE));
					response = populateResponse(request, response, transId, sessionId, authToken);
				}
			}
		} catch (AuthTokenGenerationFailureException e) {
			throw new PaymentServiceException();
		} catch (ConsumerHasInsufficientFundsException chie) {
			throw new PaymentServiceException();
		} catch (InvalidMerchantCredentialsException imc) {
			throw new PaymentServiceException();
		}

		return response;
	}

	private AuthorizePaymentFlowResponse populateResponse(AuthorizePaymentFlowRequest request, AuthorizePaymentFlowResponse response, String transId, String sessionId, byte[] authToken) {
		return new AuthorizePaymentFlowResponse(PaymentFlow.AUTHORIZE, request.getVersion(), request.getRequestId(), DateTime.now(), "OK", "", transId, sessionId, authToken);
	}

	private boolean validMerchant(AuthorizePaymentFlowRequest request) throws InvalidMerchantCredentialsException {
		LOGGER.debug("validating merchant with id {}", request.getMerchantId());
		MerchantCredential credential = new MerchantCredential(request.getUsername(), request.getPassword(), request.getMerchantId(), false);
		if (!merchantValidationService.validMerchant(credential)) {
			throw new InvalidMerchantCredentialsException();
		}
		return true;
	}

	private Consumer getConsumer(AuthorizePaymentFlowRequest request) throws PaymentServiceException {
		try {
			if (request.getEmail() != null) {
				return consumerRepository.findByEmail(request.getEmail());
			} else {
				return consumerRepository.findByMsisdn(request.getMsisdn());
			}
		} catch (Exception e) {
			throw new PaymentServiceException();
		}
	}

	private boolean consumerHasEnoughFund(Consumer consumer, BigDecimal amount) throws ConsumerHasInsufficientFundsException {
		if (consumer.getBalance().compareTo(amount) < 0) {
			throw new ConsumerHasInsufficientFundsException();
		}
		return true;
	}


}
