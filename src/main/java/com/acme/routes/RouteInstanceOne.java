package com.acme.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class RouteInstanceOne extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        templatedRoute("myRouteTemplate")
                .routeId("route1")
                .parameter("message", "hello from route 1");
    }

}
