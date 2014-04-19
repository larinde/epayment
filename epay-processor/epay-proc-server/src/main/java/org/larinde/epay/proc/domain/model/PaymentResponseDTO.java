package org.larinde.epay.proc.domain.model;

import org.larinde.epay.domain.ws.soap.AcknowledgeResponse;
import org.larinde.epay.domain.ws.soap.AuthorizeResponse;
import org.larinde.epay.domain.ws.soap.CaptureResponse;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class PaymentResponseDTO {

	private AuthorizeResponse authorizeResponse;
	private AcknowledgeResponse acknowledgeResponse;
	private CaptureResponse captureResponse;

	public PaymentResponseDTO(AuthorizeResponse authorizeResponse) {
		super();
		this.authorizeResponse = authorizeResponse;
	}

	public PaymentResponseDTO(AcknowledgeResponse acknowledgeResponse) {
		super();
		this.acknowledgeResponse = acknowledgeResponse;
	}

	public PaymentResponseDTO(CaptureResponse captureResponse) {
		super();
		this.captureResponse = captureResponse;
	}

	public AuthorizeResponse getAuthorizeResponse() {
		return authorizeResponse;
	}

	public AcknowledgeResponse getAcknowledgeResponse() {
		return acknowledgeResponse;
	}

	public CaptureResponse getCaptureResponse() {
		return captureResponse;
	}

}
