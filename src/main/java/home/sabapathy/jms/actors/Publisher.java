package home.sabapathy.jms.actors;

import java.time.LocalDate;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import home.sabapathy.jms.model.AvgDailyRatePercentRule;
import home.sabapathy.jms.model.AvgDailyRatePercentRuleOccupancy;

@Component
public class Publisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    ConnectionFactory connectionFactory;

    @Value("${occ.adr.pct.feed.queue.name: JMS/CRM.ANALYTICS.OCC_ADR.REQ}")
    private String amqQueueName;

    private JmsTemplate jmsTemplate;

    private static AvgDailyRatePercentRule newAvgDailyRatePercentRule()
    {
        AvgDailyRatePercentRule avgDailyRatePercentRule = new AvgDailyRatePercentRule();
        avgDailyRatePercentRule.setHotelCode("ATLCP");
        avgDailyRatePercentRule.setEffectiveDate(LocalDate.now().plusDays(5));
        avgDailyRatePercentRule.setCategory("Mainstream");
        avgDailyRatePercentRule.setRewardNightPenetration("<5%");
        avgDailyRatePercentRule.setFloor(60);
        avgDailyRatePercentRule.setRanges(
                new HashSet()
                {{
                    add(new AvgDailyRatePercentRuleOccupancy(1, 10, 4));
                    add(new AvgDailyRatePercentRuleOccupancy(11, 20, 20));
                    add(new AvgDailyRatePercentRuleOccupancy(21, 80, 60));
                }}
        );
        return avgDailyRatePercentRule;
    }

    @PostConstruct
    public void init()
    {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
    }

    public void publishMessage() throws JsonProcessingException
    {
        String payload = new ObjectMapper().writeValueAsString(newAvgDailyRatePercentRule());
        LOGGER.debug("Payload: {}", payload);
        jmsTemplate.convertAndSend(amqQueueName, payload);
    }
}
