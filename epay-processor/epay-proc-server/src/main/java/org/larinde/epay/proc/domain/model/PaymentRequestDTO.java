package org.larinde.epay.proc.domain.model;

import org.larinde.epay.domain.ws.soap.AcknowledgeRequest;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.domain.ws.soap.CaptureRequest;

/**
 * 
 * @author olarinde.ajai@gmail.com
 * 
 */
public class PaymentRequestDTO {

	private AuthorizeRequest authorizeRequest;
	private AcknowledgeRequest acknowledgeRequest;
	private CaptureRequest captureRequest;

	public PaymentRequestDTO(AuthorizeRequest authorizeRequest) {
		super();
		this.authorizeRequest = authorizeRequest;
	}

	public PaymentRequestDTO(AcknowledgeRequest acknowledgeRequest) {
		super();
		this.acknowledgeRequest = acknowledgeRequest;
	}

	public PaymentRequestDTO(CaptureRequest captureRequest) {
		super();
		this.captureRequest = captureRequest;
	}

	public AuthorizeRequest getAuthorizeRequest() {
		return authorizeRequest;
	}

	public AcknowledgeRequest getAcknowledgeRequest() {
		return acknowledgeRequest;
	}

	public CaptureRequest getCaptureRequest() {
		return captureRequest;
	}

}
