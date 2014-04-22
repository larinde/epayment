package org.larinde.epay.proc.infrastructure.configuration;

import org.dozer.loader.api.BeanMappingBuilder;
import org.larinde.epay.domain.ws.soap.AuthorizeRequest;
import org.larinde.epay.proc.domain.model.AuthorizePaymentFlowRequest;
import org.springframework.context.annotation.Configuration;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Configuration 
public class EpayDozerConfig {
	
	static BeanMappingBuilder beanMappingBuilder = new BeanMappingBuilder() {
		
		@Override
		protected void configure() {
			mapping(AuthorizeRequest.class, AuthorizePaymentFlowRequest.Builder.class)
			.fields(field("baseMessage.version").setMethod("setVersion"), field("version").setMethod("version"))
			//.fields(field("baseMessage.requestType").setMethod("setRequestType"), field("version").setMethod("version"))
			.fields(field("baseMessage.requestId").setMethod("setRequestId"), field("requestId").setMethod("requestId"))
			.fields(field("baseMessage.username").setMethod("setUsername"), field("requestId").setMethod("requestId"))
			.fields(field("baseMessage.password").setMethod("setPassword"), field("password").setMethod("password"))
			.fields(field("baseMessage.communicationDate").setMethod("setCommunicationDate"), field("communicationDate").setMethod("communicationDate"))
			.fields(field("baseMessage.merchantId").setMethod("setMerchantId"), field("merchantId").setMethod("merchantId"))
			.fields(field("amount").setMethod("setAmount"), field("amount").setMethod("amount"))
			.fields(field("currency").setMethod("setCurrency"), field("currency").setMethod("currency"))
			.fields(field("description").setMethod("setDescription"), field("description").setMethod("description"));
			
		}
	};
	

}
