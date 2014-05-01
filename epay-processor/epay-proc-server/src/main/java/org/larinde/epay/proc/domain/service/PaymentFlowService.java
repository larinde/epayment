package org.larinde.epay.proc.domain.service;

import org.larinde.epay.proc.domain.model.AbstractPaymentFlowResponse;
import org.larinde.epay.proc.domain.model.AbstractPaymentFlowRequest;
import org.larinde.epay.proc.domain.model.PaymentServiceException;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public interface PaymentFlowService <Res extends AbstractPaymentFlowResponse, Req extends AbstractPaymentFlowRequest>{
	
	public Res execute(Req request) throws PaymentServiceException;


}
