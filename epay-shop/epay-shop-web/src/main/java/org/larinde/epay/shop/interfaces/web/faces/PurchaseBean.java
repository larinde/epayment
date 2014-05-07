package org.larinde.epay.shop.interfaces.web.faces;

import java.io.Serializable;

import org.larinde.epay.shop.domain.service.EpayClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author olarinde.ajai@gmail.com
 * 
 */
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class PurchaseBean implements Serializable{

	private static final long serialVersionUID = 1478150529428426612L;
	private static final Logger LOG = LoggerFactory.getLogger(PurchaseBean.class);
	@Autowired
	EpayClientService epayClientService;

}
