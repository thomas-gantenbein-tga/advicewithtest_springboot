package com.acme;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@CamelSpringBootTest
public class NoAdviceWithAnnotationTest {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private ProducerTemplate producerTemplate;

	@Test
	@DirtiesContext
	public void givenContextStartedAfterAdvice_fail() throws Exception {
		AdviceWith.adviceWith(camelContext, "route1",
				r -> {
					r.replaceFromWith("direct:start");
				}
		);
		camelContext.stop();
		camelContext.start();
		producerTemplate.sendBody("direct:start", "hello");
	}

	@Test
	@DirtiesContext
	public void givenContextStartedBeforeAdvice_success() throws Exception {
		AdviceWith.adviceWith(camelContext, "route1",
				r -> {
					r.replaceFromWith("direct:start");
				}
		);
		producerTemplate.sendBody("direct:start", "hello");
	}
}
