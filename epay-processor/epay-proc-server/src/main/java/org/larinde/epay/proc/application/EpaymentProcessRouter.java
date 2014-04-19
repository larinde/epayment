package org.larinde.epay.proc.application;

import org.apache.camel.CamelContext;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.larinde.epay.domain.ws.soap.AcknowledgeRequest;
import org.larinde.epay.domain.ws.soap.AcknowledgeResponse;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.domain.ws.soap.AuthorizeResponse;
import org.larinde.epay.domain.ws.soap.CaptureRequest;
import org.larinde.epay.domain.ws.soap.CaptureResponse;
import org.larinde.epay.proc.domain.model.EPayConfiguration;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
public class EpaymentProcessRouter extends RouteBuilder {

	private JaxbDataFormat authorizeRequestDataFormat;
	private JaxbDataFormat authorizeResponseDataFormat;
	private JaxbDataFormat acknowledgeRequestDataFormat;
	private JaxbDataFormat acknowledgeResponseDataFormat;
	private JaxbDataFormat captureRequestDataFormat;
	private JaxbDataFormat captureResponseDataFormat;
	private AuthorizeProcessor authorizeProcessor;
	private EPayConfiguration epayConfiguration;

	private static final String AUTHORIZE_ROUTE_IDENTIFIER = "spring-ws:rootqname:{http://epay.larinde.org/operation/v1}AuthorizeRequest?endpointMapping=#epayEndPointMapping";
	private static final String ACKNOWLEDGE_ROUTE_IDENTIFIER = "spring-ws:rootqname:{http://epay.larinde.org/operation/v1}AcknowledgeRequest?endpointMapping=#epayEndPointMapping";
	private static final String CAPTURE_ROUTE_IDENTIFIER = "spring-ws:rootqname:{http://epay.larinde.org/operation/v1}CaptureRequest?endpointMapping=#epayEndPointMapping";

	public void setAuthorizeProcessor(AuthorizeProcessor authorizeProcessor) {
		this.authorizeProcessor = authorizeProcessor;
	}

	public void setEpayConfiguration(EPayConfiguration epayConfiguration) {
		this.epayConfiguration = epayConfiguration;
	}

	@Override
	public void configure() throws Exception {
		initializeDataFormat();
		buildAuthorizeRoute();
		buildAcknowledgeeRoute();
		buildCaptureRoute();
	}

	private void buildAuthorizeRoute() {
		long timePeriodMillis = epayConfiguration.getSLATransactionPeriodMillis();
		from(AUTHORIZE_ROUTE_IDENTIFIER).throttle(epayConfiguration.getSLATransactionsPerSecond()).timePeriodMillis(timePeriodMillis).unmarshal(authorizeRequestDataFormat).process(authorizeProcessor).marshal(authorizeResponseDataFormat);
	}

	private void buildCaptureRoute() {
	}

	private void buildAcknowledgeeRoute() {
	}

	private void initializeDataFormat() {
		authorizeRequestDataFormat = new JaxbDataFormat(AuthorizeRequest.class.getPackage().getName());
		authorizeResponseDataFormat = new JaxbDataFormat(AuthorizeResponse.class.getPackage().getName());
		acknowledgeRequestDataFormat = new JaxbDataFormat(AcknowledgeRequest.class.getPackage().getName());
		acknowledgeResponseDataFormat = new JaxbDataFormat(AcknowledgeResponse.class.getPackage().getName());
		captureRequestDataFormat = new JaxbDataFormat(CaptureRequest.class.getPackage().getName());
		captureResponseDataFormat = new JaxbDataFormat(CaptureResponse.class.getPackage().getName());
	}

}
