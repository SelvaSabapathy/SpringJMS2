package home.sabapathy.jms.config;

import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpoint;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import home.sabapathy.jms.converter.OccAdrPctFeedConverter;
import home.sabapathy.jms.model.AvgDailyRatePercentRule;
import home.sabapathy.jms.service.OccAdrPctFeedService;

@Configuration
public class OccAdrPctJmsConfiguration implements JmsListenerConfigurer
{
    @Autowired
    private ObjectFactory<OccAdrPctFeedService> occAdrPctFeedServiceFactory;

//    @Autowired
//    private JavaTimeModule javaTimeModule;

    @Value("${occ.adr.pct.activemq.broker.url}")
    private String occAdrPctActiveMqBrokerUrl;

    @Value("${occ.adr.pct.feed.queue.name}")
    private String occAdrPctQueue;

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar)
    {
        registrar.setContainerFactory(defaultJmsListenerContainerFactory());
        registrar.registerEndpoint(occAdrPctFeedEndpoint());
    }

    @Bean
    public JmsListenerContainerFactory<?> defaultJmsListenerContainerFactory()
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory());
        factory.setDestinationResolver(destinationResolver());
        factory.setSessionTransacted(true);
        return factory;
    }

    @Bean
    public ConnectionFactory cachingConnectionFactory()
    {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setTargetConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory()
    {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(occAdrPctActiveMqBrokerUrl);
        factory.setRedeliveryPolicy(redeliveryPolicy());
        return factory;
    }

    @Bean
    public RedeliveryPolicy redeliveryPolicy()
    {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        return redeliveryPolicy;
    }

    @Bean
    public DestinationResolver destinationResolver()
    {
        return new DynamicDestinationResolver();
    }

    @Bean
    public JmsListenerEndpoint occAdrPctFeedEndpoint()
    {
        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        endpoint.setId("occAdrPctFeedEndpoint");
        endpoint.setMessageListener(occAdrPctFeedListenerAdapter());
        endpoint.setDestination(occAdrPctQueue);
        return endpoint;
    }

    @Bean
    public MessageListener occAdrPctFeedListenerAdapter()
    {
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDelegate(occAdrPctFeedServiceFactory.getObject());
        adapter.setDefaultListenerMethod("process");
        adapter.setMessageConverter(jsonMessageConverter());
        return adapter;
    }

    @Bean
    public OccAdrPctFeedConverter jsonMessageConverter()
    {
        ObjectMapper objectMapper = jmsJsonMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructType(AvgDailyRatePercentRule.class);
        return new OccAdrPctFeedConverter(objectMapper, javaType);
    }

    @Bean
    public ObjectMapper jmsJsonMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
