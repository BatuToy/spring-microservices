package com.dev.batu.gateway.config.restclient;


import com.dev.batu.gateway.config.interceptors.DummyInterceptor;
import com.dev.batu.gateway.rest.DummyClient;
import com.dev.batu.gateway.rest.TodosClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author batu
 */

@Configuration
public class RestClientConfig {

    private static final Log log = LogFactory.getLog(RestClientConfig.class);

    @Bean
    public RestClient.Builder restClient() {
        return RestClient.builder();
    }

    // Interceptor example...
    @Bean
    public TodosClient todosClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/todos")
                .requestFactory(new JdkClientHttpRequestFactory())
                .requestInterceptor(((request, body, execution) -> {
                    log.info(request.getURI().toString());
                    request.getHeaders().add("X-Request-ID", "123");
                    return execution.execute(request, body);
                }))
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(restClient)
        ).build();

        return factory.createClient(TodosClient.class);
    }

    @Bean
    public DummyClient dummyClient(DummyInterceptor dummyInterceptor){
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:9002/get")
                .requestFactory(new JdkClientHttpRequestFactory())
                .requestInterceptor(dummyInterceptor)
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                RestClientAdapter.create(restClient)
        ).build();
        return factory.createClient(DummyClient.class);
    }
}
