package org.larinde.epay.proc.infrastructure.configuration;

import org.larinde.epay.ds.infrastructure.configuraton.JpaDataStoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Configuration
@Import(JpaDataStoreConfig.class)
@ImportResource("classpath:epay-processor-context.xml")
public class EpayProcessorServerConfig {

}
