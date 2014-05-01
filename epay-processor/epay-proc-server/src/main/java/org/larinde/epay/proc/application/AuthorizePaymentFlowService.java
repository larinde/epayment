package org.larinde.epay.proc.application;

import org.larinde.epay.ds.domain.repository.ConsumerRepository;
import org.larinde.epay.ds.domain.repository.PaymentRepository;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowRequest;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowResponse;
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
		return null;
	}

}
