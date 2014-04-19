package org.larinde.epay.proc.application;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.proc.domain.model.PaymentRequestDTO;
import org.larinde.epay.proc.domain.model.PaymentResponseDTO;
import org.larinde.epay.proc.domain.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class AuthorizeProcessor implements Processor {

	private PaymentService paymentService;
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeProcessor.class);

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("delegating authorize request to payment service");
		AuthorizeRequest request = exchange.getIn().getBody(AuthorizeRequest.class);
		PaymentRequestDTO requestDTO = new PaymentRequestDTO(request);
		PaymentResponseDTO responseDTO = paymentService.process(requestDTO);
		exchange.getOut().setBody(responseDTO.getAuthorizeResponse());
	}

}
