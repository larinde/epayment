package org.larinde.epay.proc.application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.larinde.epay.proc.domain.model.AuthTokenGenerationFailureException;
import org.larinde.epay.proc.domain.model.AuthTokenVerificationFailureException;
import org.larinde.epay.proc.domain.service.AuthenticationService;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class AuthenticationTokenServiceImpl implements AuthenticationService {

	private static final String ENCRYPTION_ALGORITHM = "SHA-256";
	private static final String SALT = "3aNcäf#+QSC12r4Yzq&%7";
	private static final String DEFAULT_CONSUMER_AUTHENTICATION_URL = "http://localhost:8080/epay-auth";

	@Override
	public byte[] generateToken(String identifier) throws AuthTokenGenerationFailureException {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
			digest.reset();
			digest.update((SALT+identifier).getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new AuthTokenGenerationFailureException();
		}
		return digest.digest();
	}

	@Override
	public boolean validToken(byte[] receivedToken, String identifier) throws AuthTokenVerificationFailureException {
		byte[] verificationToken = null;
		try {
			verificationToken = generateToken(identifier);
		} catch (AuthTokenGenerationFailureException e) {
			throw new AuthTokenVerificationFailureException();
		}
		if (Arrays.equals(verificationToken, receivedToken)) {
			return true;
		}
		return false;
	}

	@Override
	public String getConsumerAuthenticationUrl() {
		return DEFAULT_CONSUMER_AUTHENTICATION_URL;
	}

}
