package org.larinde.epay.proc.domain.service;

import org.larinde.epay.proc.domain.model.PaymentRequestDTO;
import org.larinde.epay.proc.domain.model.PaymentResponseDTO;
import org.larinde.epay.proc.domain.model.PaymentServiceException;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public interface PaymentService {

	PaymentResponseDTO process(PaymentRequestDTO request) throws PaymentServiceException;

}
