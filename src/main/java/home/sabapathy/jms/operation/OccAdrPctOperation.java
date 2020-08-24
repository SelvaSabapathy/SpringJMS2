package home.sabapathy.jms.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import home.sabapathy.jms.model.AvgDailyRatePercentRule;

public class OccAdrPctOperation
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OccAdrPctOperation.class);

    @Autowired
    //    private CatalogDao catalogDao;
    
    public void saveFeed(AvgDailyRatePercentRule avgDailyRatePercentRule)
    {
        LOGGER.debug("Saving OCC ADR PCT saveFeed");
    }
}
