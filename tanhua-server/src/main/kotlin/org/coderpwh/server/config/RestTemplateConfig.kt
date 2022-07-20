package org.coderpwh.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset

/**
 * @author coderpwh
 * @date 2022-07-19 17:16
 * @version 1.0.0 v
 */
@Configuration
class RestTemplateConfig {
    @Bean
    fun restTemplate(factory: ClientHttpRequestFactory):RestTemplate {
        var restTemplate = RestTemplate(factory)
        restTemplate.messageConverters.set(1,StringHttpMessageConverter(Charset.forName("UTF-8")))
        return restTemplate
    }

    @Bean
    fun simpleClientHttpRequestFactory():ClientHttpRequestFactory {
        var factory = SimpleClientHttpRequestFactory()
        factory.setReadTimeout(5000)
        factory.setConnectTimeout(5000)
        return factory
    }
}