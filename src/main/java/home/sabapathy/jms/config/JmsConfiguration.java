package home.sabapathy.jms.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import home.sabapathy.jms.actors.Subscriber;

@Configuration
public class JmsConfiguration
{
    @Value("${activemq.broker.url: failover:(tcp://localhost:61616)?timeout=15000&randomize=false&jms.prefetchPolicy.all=1}")
    private String amqBrokerUrl;

    @Value("${activemq.queue_name: JMS/CRM.ANALYTICS.OCC_ADR.REQ}")
    private String amqQueueName;
    
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(amqBrokerUrl);
        return connectionFactory;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestinationName(amqQueueName);
        container.setMessageListener(new Subscriber());
        return container;
    }
}
