package org.larinde.epay.proc.application;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.domain.ws.soap.AuthorizeResponse;
import org.larinde.epay.domain.ws.soap.BaseRequestType;
import org.larinde.epay.domain.ws.soap.BaseResponseType;
import org.larinde.epay.domain.ws.soap.ConsumerId;
import org.larinde.epay.domain.ws.soap.TransactionStatusType;
import org.larinde.epay.ds.domain.Consumer;
import org.larinde.epay.ds.domain.Payment;
import org.larinde.epay.ds.domain.PaymentFlow;
import org.larinde.epay.ds.domain.PaymentStatus;
import org.larinde.epay.ds.domain.repository.ConsumerRepository;
import org.larinde.epay.ds.domain.repository.PaymentRepository;
import org.larinde.epay.proc.domain.exception.AuthTokenGenerationFailureException;
import org.larinde.epay.proc.domain.exception.ConsumerHasInsufficientFundsException;
import org.larinde.epay.proc.domain.exception.InvalidMerchantCredentialsException;
import org.larinde.epay.proc.domain.model.MerchantCredential;
import org.larinde.epay.proc.domain.model.MessageStatus;
import org.larinde.epay.proc.domain.model.PaymentRequestDTO;
import org.larinde.epay.proc.domain.model.PaymentResponseDTO;
import org.larinde.epay.proc.domain.model.PaymentServiceException;
import org.larinde.epay.proc.domain.service.AuthenticationService;
import org.larinde.epay.proc.domain.service.MerchantValidationService;
import org.larinde.epay.proc.domain.service.PaymentIdService;
import org.larinde.epay.proc.domain.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Service
public class PaymentServiceImpl implements PaymentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	private static final String APPLICATION_VERSION = "1.0.0";
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
	public PaymentResponseDTO process(final PaymentRequestDTO request) throws PaymentServiceException {
		LOGGER.info("processing authorize request");
		//TODO: use rule engine
		AuthorizeResponse response = null;
		try {
			if (validMerchant(request.getAuthorizeRequest())) {
				final Consumer consumer = getConsumer(request.getAuthorizeRequest().getConsumerId());
				if (consumerHasEnoughFund(consumer, request.getAuthorizeRequest().getAmount())) {
					final String transId = paymentIdService.generateTransactionId();
					final String sessionId = paymentIdService.generateSessionId();
					final byte[] authToken = authenticationTokenService.generateToken(transId);

					Payment payment = new Payment(sessionId, transId, PaymentStatus.PENDING, request.getAuthorizeRequest().getBaseMessage().getCommunicationDate().toDate(), request.getAuthorizeRequest().getBaseMessage().getMerchantId(), PaymentFlow.AUTHORIZE, request.getAuthorizeRequest().getAmount(), request.getAuthorizeRequest().getCurrency(), request.getAuthorizeRequest().getDescription(), consumer);
					paymentRepository.save(payment);
					LOGGER.info("saved payment: {}", ToStringBuilder.reflectionToString(payment, ToStringStyle.DEFAULT_STYLE));

					response = new AuthorizeResponse();
					response.setBaseMessage(popuateBaseResponse(request.getAuthorizeRequest().getBaseMessage(), APPLICATION_VERSION, MessageStatus.OK));
					populateAuthorizeResponse(response, transId, sessionId, authToken);
				}
			}
		} catch (AuthTokenGenerationFailureException e) {
			throw new PaymentServiceException();
		} catch (ConsumerHasInsufficientFundsException chie) {
			throw new PaymentServiceException();
		} catch (InvalidMerchantCredentialsException imc) {
			throw new PaymentServiceException();
		}
		return new PaymentResponseDTO(response);
	}

	private boolean consumerHasEnoughFund(Consumer consumer, BigDecimal amount) throws ConsumerHasInsufficientFundsException {
		if (consumer.getBalance().compareTo(amount) < 0) {
			throw new ConsumerHasInsufficientFundsException();
		}
		return true;
	}

	private boolean pendingPaymentsExceedsAvailableFunds(Consumer consumer, BigDecimal amount) {
		BigDecimal pendingAmountReservation = new BigDecimal(0);
		for (Payment payment : consumer.getPayments()) {
			if (payment.getStatus().equals(PaymentStatus.PENDING)) {
				pendingAmountReservation = pendingAmountReservation.add(payment.getAmount());
			}
		}
		if (consumer.getBalance().compareTo(pendingAmountReservation) >= 0) {
			return false;
		}
		return true;
	}

	private Consumer getConsumer(ConsumerId consumerId) throws PaymentServiceException {
		try {
			if (consumerId.getEmail() != null) {
				return consumerRepository.findByEmail(consumerId.getEmail());
			} else {
				return consumerRepository.findByMsisdn(consumerId.getMsisdn().toString());
			}
		} catch (Exception e) {
			throw new PaymentServiceException();
		}
	}

	private void populateAuthorizeResponse(AuthorizeResponse response, String transId, String sessionId, byte[] authToken) throws AuthTokenGenerationFailureException {
		response.setTransactionStatus(TransactionStatusType.IN_PROCESS);
		response.setReferralURL(authenticationTokenService.getConsumerAuthenticationUrl());
		response.setSessionId(sessionId);
		response.setTransactionId(transId);
		response.setAuthToken(authToken);
	}

	private boolean validMerchant(AuthorizeRequest request) throws InvalidMerchantCredentialsException {
		LOGGER.debug("validating merchant with id {}", request.getBaseMessage().getMerchantId());
		MerchantCredential credential = new MerchantCredential(request.getBaseMessage().getUsername(), request.getBaseMessage().getPassword(), request.getBaseMessage().getMerchantId(), false);
		if (!merchantValidationService.validMerchant(credential)) {
			throw new InvalidMerchantCredentialsException();
		}
		return true;
	}

	private BaseResponseType popuateBaseResponse(BaseRequestType baseRequest, String appVersion, MessageStatus messageStatus) {
		BaseResponseType baseResponse = new BaseResponseType();
		baseResponse.setCommunicationDate(new DateTime());
		baseResponse.setRequestId(baseRequest.getRequestId());
		baseResponse.setRequestType(baseRequest.getRequestType());
		baseResponse.setStatusCode(messageStatus.toString());
		baseResponse.setStatusText("");
		baseResponse.setVersion(appVersion);
		return baseResponse;
	}

}
