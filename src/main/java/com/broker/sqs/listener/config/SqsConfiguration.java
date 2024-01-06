package com.broker.sqs.listener.config;

import io.awspring.cloud.autoconfigure.sqs.SqsProperties;
import io.awspring.cloud.sqs.config.SqsBootstrapConfiguration;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.support.converter.SqsMessagingMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Import(SqsBootstrapConfiguration.class)
@Configuration
public class SqsConfiguration {
    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory() {
        return SqsMessageListenerContainerFactory
                .builder()
                .sqsAsyncClient(sqsAsyncClient())
                .configure(c -> c.messageConverter(messageConverter()))
                .build();
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder().region(Region.US_EAST_1).endpointOverride(URI.create("http://localhost:4566")).build();
    }

    @Bean
    public SqsProperties.Listener listener() {
        return new SqsProperties.Listener();
    }

    private SqsMessagingMessageConverter messageConverter() {
        SqsMessagingMessageConverter messageConverter = new SqsMessagingMessageConverter();
        MappingJackson2MessageConverter payloadConverter = new MappingJackson2MessageConverter();
        payloadConverter.setPrettyPrint(true);
        messageConverter.setPayloadMessageConverter(payloadConverter);
        return messageConverter;
    }
}
