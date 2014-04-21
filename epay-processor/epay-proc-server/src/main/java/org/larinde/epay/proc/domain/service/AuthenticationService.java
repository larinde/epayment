package org.larinde.epay.proc.domain.service;

import org.larinde.epay.proc.domain.exception.AuthTokenGenerationFailureException;
import org.larinde.epay.proc.domain.exception.AuthTokenVerificationFailureException;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
public interface AuthenticationService {
	
	public byte[] generateToken(String identifier) throws AuthTokenGenerationFailureException;
	
	public boolean validToken(byte[] token, String identifier) throws AuthTokenVerificationFailureException;
	
	public String getConsumerAuthenticationUrl();

}
