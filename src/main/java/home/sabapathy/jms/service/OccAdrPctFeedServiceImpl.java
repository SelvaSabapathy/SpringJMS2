package home.sabapathy.jms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import home.sabapathy.jms.model.AvgDailyRatePercentRule;

@Component
public class OccAdrPctFeedServiceImpl implements OccAdrPctFeedService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OccAdrPctFeedServiceImpl.class);

    @Transactional
    @Override
    public void process(AvgDailyRatePercentRule avgDailyRatePercentRule)
    {
        System.out.println("MSG: " + avgDailyRatePercentRule);
    }
}
