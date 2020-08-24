package home.sabapathy.jms.service;

import home.sabapathy.jms.model.AvgDailyRatePercentRule;

public interface OccAdrPctFeedService
{
    void process(AvgDailyRatePercentRule avgDailyRatePercentRule);
}
