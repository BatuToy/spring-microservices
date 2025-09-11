package com.dev.batu.gateway.config.routes;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        URI uri = URI.create("http://localhost:9002");
//        return builder.routes()
//                .route("get-dummy", predicateSpec -> predicateSpec
//                        .path("/dummy")
//                        .filters(
//                                gatewayFilterSpec -> gatewayFilterSpec
//                                        .setPath("dummy/v1/get")
//                                        .setStatus(HttpStatus.ACCEPTED)
//                                        .circuitBreaker(config -> config
//                                                .setName("myCircuitBreaker")
//                                                .setFallbackUri("forward:/fallback")))
//                        .uri(uri))
//                .build();
//    }

}
