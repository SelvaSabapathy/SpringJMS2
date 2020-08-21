package home.sabapathy.jms.actors;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher
{
    @Autowired
    ConnectionFactory connectionFactory;

    @Value("${activemq.queue_name: JMS/CRM.ANALYTICS.OCC_ADR.REQ}")
    private String amqQueueName;

    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init()
    {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public void publishMessage()
    {
        System.out.println("Sending a message....");

        jmsTemplate.convertAndSend(amqQueueName, "hello, world!");
    }
}
