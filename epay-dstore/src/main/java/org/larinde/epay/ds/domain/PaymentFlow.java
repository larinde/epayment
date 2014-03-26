package org.larinde.epay.ds.domain;

/**
 * The flow (phase) of a payment transaction
 * 
 * @author olarinde.ajai@gmail.com
 * 
 */
public enum PaymentFlow {
	AUTHORIZE, ACKNOWLEDGE, CAPTURE;
}
