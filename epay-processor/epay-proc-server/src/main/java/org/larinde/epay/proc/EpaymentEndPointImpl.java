package org.larinde.epay.proc;

import org.larinde.epay.domain.ws.soap.AcknowledgeRequest;
import org.larinde.epay.domain.ws.soap.AcknowledgeResponse;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.domain.ws.soap.AuthorizeResponse;
import org.larinde.epay.domain.ws.soap.CaptureRequest;
import org.larinde.epay.domain.ws.soap.CaptureResponse;
import org.larinde.epay.proc.domain.service.PaymentService;
import org.larinde.epay.ws.v1.EPaymentEndPoint;
import org.larinde.epay.ws.v1.EpayFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class EpaymentEndPointImpl implements EPaymentEndPoint {
	
	private PaymentService paymentService;
	private static final Logger LOGGER = LoggerFactory.getLogger(EpaymentEndPointImpl.class);
	
	public void setPaymentIdService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}


	@Override
	public AcknowledgeResponse acknowledge(AcknowledgeRequest response) throws EpayFault {
		LOGGER.info("receiving acknowledge request");
		return null;
	}

	@Override
	public AuthorizeResponse authorize(AuthorizeRequest request) throws EpayFault {
		
		return null;
	}


	@Override
	public CaptureResponse capture(CaptureRequest response) throws EpayFault {
		LOGGER.info("receiving capture request");
		return null;
	}

}
