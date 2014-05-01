package org.larinde.epay.proc.application;


import org.larinde.epay.proc.domain.model.CapturePaymentFlowRequest;
import org.larinde.epay.proc.domain.model.CapturePaymentFlowResponse;
import org.larinde.epay.proc.domain.model.PaymentServiceException;
import org.larinde.epay.proc.domain.service.PaymentFlowService;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class CapturePaymentFlowService implements PaymentFlowService<CapturePaymentFlowResponse, CapturePaymentFlowRequest> {

	@Override
	public CapturePaymentFlowResponse execute(CapturePaymentFlowRequest request) throws PaymentServiceException {
		// TODO Auto-generated method stub
		return null;
	}


}
