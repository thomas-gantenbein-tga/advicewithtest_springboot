package com.acme;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CamelSpringBootTest
@UseAdviceWith
public class WithAdviceWithAnnotationTest {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private ProducerTemplate producerTemplate;

	@Test
	public void givenContextStartedAfterAdvice_fail() throws Exception {
		AdviceWith.adviceWith(camelContext, "route1",
				r -> {
					r.replaceFromWith("direct:start");
				}
		);
		camelContext.start();
		producerTemplate.sendBody("direct:start", "hello");
	}

}
