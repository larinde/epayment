package org.larinde.epay.proc.domain.service;

import org.larinde.epay.proc.domain.model.AbstractPaymentFlowMessage;
import org.larinde.epay.proc.domain.model.AbstractPaymentFlowRequest;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public interface PaymentFlowService {
	
	public AbstractPaymentFlowMessage execute(AbstractPaymentFlowRequest request);

}
