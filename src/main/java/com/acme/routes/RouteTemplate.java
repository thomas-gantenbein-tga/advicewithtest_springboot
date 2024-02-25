package com.acme.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.routetemplate.CamelRouteTemplateAutoConfiguration;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */

@Component("myTemplate")
public class RouteTemplate extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        routeTemplate("myRouteTemplate")
                .templateParameter("message")
                .from("timer:atimer")
                .log("{{message}}");

    }

}
