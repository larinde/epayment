package org.larinde.epay.proc.application;

import org.larinde.epay.proc.domain.model.AbstractPaymentFlowMessage;
import org.larinde.epay.proc.domain.model.AbstractPaymentFlowRequest;
import org.larinde.epay.proc.domain.service.PaymentFlowService;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public class AuthorizePaymentFlowService implements PaymentFlowService {

	@Override
	public AbstractPaymentFlowMessage execute(AbstractPaymentFlowRequest request) {
		return null;
	}

}
