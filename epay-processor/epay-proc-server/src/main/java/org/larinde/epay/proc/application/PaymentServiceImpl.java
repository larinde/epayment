package org.larinde.epay.proc.application;

import org.joda.time.DateTime;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.domain.ws.soap.AuthorizeResponse;
import org.larinde.epay.domain.ws.soap.BaseRequestType;
import org.larinde.epay.domain.ws.soap.BaseResponseType;
import org.larinde.epay.domain.ws.soap.TransactionStatusType;
import org.larinde.epay.proc.domain.model.AuthTokenGenerationFailureException;
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

	@Override
	public PaymentResponseDTO process(final PaymentRequestDTO request) throws PaymentServiceException {
		LOGGER.info("processing authorize request");
		AuthorizeResponse response = null;
		try {
			if (validMerchant(request.getAuthorizeRequest())) {
				response = new AuthorizeResponse();
				response.setBaseMessage(popuateBaseResponse(request.getAuthorizeRequest().getBaseMessage(), APPLICATION_VERSION, MessageStatus.OK));
				populateAuthorizeResponse(response);
			} else {
				throw new PaymentServiceException();
			}
		} catch (AuthTokenGenerationFailureException e) {
			throw new PaymentServiceException();
		}
		return new PaymentResponseDTO(response);
	}

	private void populateAuthorizeResponse(AuthorizeResponse response) throws AuthTokenGenerationFailureException {
		response.setTransactionStatus(TransactionStatusType.IN_PROCESS);
		response.setReferralURL(authenticationTokenService.getConsumerAuthenticationUrl());
		response.setSessionId(paymentIdService.generateSessionId());
		final String transId = paymentIdService.generateTransactionId();
		response.setTransactionId(transId);
		response.setAuthToken(authenticationTokenService.generateToken(transId));
	}

	private boolean validMerchant(AuthorizeRequest request) {
		MerchantCredential credential = new MerchantCredential(request.getBaseMessage().getUsername(), request.getBaseMessage().getPassword(), request.getBaseMessage().getMerchantId(), false);
		return merchantValidationService.validMerchant(credential);
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
