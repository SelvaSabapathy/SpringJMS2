package home.sabapathy.jms.validator;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import home.sabapathy.jms.mock.Hotel;
import home.sabapathy.jms.model.AvgDailyRatePercentRule;
import home.sabapathy.jms.model.AvgDailyRatePercentRuleOccupancy;
import home.sabapathy.jms.utils.OccAdrPctHotelHelper;

public class OccAdrPctFeedValidator // implements Validator<AvgDailyRatePercentRule>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OccAdrPctFeedValidator.class);

    @Autowired
    OccAdrPctHotelHelper ocrAdrPctHotelHelper;

//    @Override
    public boolean isValid(AvgDailyRatePercentRule avgDailyRatePercentRule)
    {
        Assert.notNull(avgDailyRatePercentRule, "ADR Occupancy feed should not be null");
        Assert.notNull(avgDailyRatePercentRule.getRanges(), "ADR Occupancy feedd ranges should not be empty");

        String hotelCode = avgDailyRatePercentRule.getHotelCode();
        try
        {
            // 1. Hotel Code must exist in Growth.Hotel
            validateHotelCode(hotelCode);

            // 2. Effective Date must be after today
//            validtateEffectiveDate(avgDailyRatePercentRule.getEffectiveDate());

            // 3. Category must be 
            validateCategory(avgDailyRatePercentRule.getCategory());

            // 4. Floor should be between 1 and 100 (both inclusive)
            validateFloor(avgDailyRatePercentRule.getFloor());

            // 5. The Set<AvgDailyRatePercentRuleOccupancy> can't be null or empty
            validateOccupancyRange(avgDailyRatePercentRule.getRanges());
        }
        catch (Exception e)
        {
            LOGGER.warn("Dropping OccAdrPctFeed for Hotel Code: " + hotelCode);
            LOGGER.warn("Dropping OccAdrPctFeed for failed validation: " + e.getMessage());
        }

        return true;
    }

    private Hotel validateHotelCode(String hotelCode) throws Exception
    {
        return Optional.ofNullable(hotelCode)
                .filter(StringUtils::isNotBlank)
                .map(ocrAdrPctHotelHelper::getHotelByCode)
                .orElseThrow(() -> new Exception("Hotel Code is null or blank"));
    }

    private void validtateEffectiveDate(LocalDate localDate) throws Exception
    {
        if (localDate.isBefore(now()))
        {
            throw new Exception("Effective date is in the past");
        }
    }

    private void validateCategory(String category) throws Exception
    {
        LOGGER.debug("Placeholder - No validation currently, as the names are in flux");
    }

    private void validateFloor(Integer floor) throws Exception
    {
        if (floor <= 0 || floor > 100)
        {
            throw new Exception("Floor is out of range");
        }
    }

    private void validateOccupancyRange(Set<AvgDailyRatePercentRuleOccupancy> avgDailyRatePercentRuleOccupancies)
            throws Exception
    {
        Assert.notNull(avgDailyRatePercentRuleOccupancies, "No OCC ranges were included");

        boolean isValid = avgDailyRatePercentRuleOccupancies.stream().allMatch(range ->
                range.getOccLowPercent() >= 0
                        && range.getOccLowPercent() <= 100
                        && range.getOccHighPercent() >= 0
                        && range.getOccHighPercent() <= 100
                        && range.getAdrPercent() >= 0
                        && range.getAdrPercent() <= 100
        );

        LOGGER.warn("TODO: Test overlapping ranges");

        if (!isValid)
        {
            throw new Exception("OCC ranges are outside expected value range");
        }
    }
}
