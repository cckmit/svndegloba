package com.degloba.person.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author degloba
 * 
 * @category configuraciĆ³ Spring per Axon utilitzant Rabbit
 * 
 * AMQP = Advanced Message Queuing Protocol
 * Rabbit
 *
 */
@Configuration
@Slf4j
public class AxonConfig {

    private static final String AXON_QUEUE = "personQueue";

    // Queue

    @Bean
    @Qualifier("axonAmqp")
    public Queue queue() {
        return QueueBuilder.durable(AXON_QUEUE).build();
    }

    // Listen to RabbitMQ messages

    @Bean
    public SpringAMQPMessageSource axonQueueMessageSource(AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {

            @RabbitListener(queues = AXON_QUEUE)
            @Transactional
            @Override
            public void onMessage(Message message, Channel channel) {
                log.debug("[AMQP] Processing message: {}, on channel: {}", message, channel);
                super.onMessage(message, channel);
            }
        };
    }
}
