package home.sabapathy.jms.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import home.sabapathy.jms.model.io.OccAdrPctLocalDateDeserializer;
import home.sabapathy.jms.model.io.OccAdrPctLocalDateSerializer;

public class AvgDailyRatePercentRule
{
    private String hotelCode;

    @JsonDeserialize(using = OccAdrPctLocalDateDeserializer.class)
    @JsonSerialize(using = OccAdrPctLocalDateSerializer.class)
    private LocalDate effectiveDate;
    
    private String category;
    
    private String rewardNightPenetration;
    
    private Integer floor;
    
    private Set<AvgDailyRatePercentRuleOccupancy> ranges;

    public AvgDailyRatePercentRule()
    {
    }

    public AvgDailyRatePercentRule(String hotelCode, LocalDate effectiveDate, String category,
            String rewardNightPenetration, Integer floor, Set<AvgDailyRatePercentRuleOccupancy> ranges)
    {
        this.hotelCode = hotelCode;
        this.effectiveDate = effectiveDate;
        this.category = category;
        this.rewardNightPenetration = rewardNightPenetration;
        this.floor = floor;
        this.ranges = ranges;
    }

    public String getHotelCode()
    {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode)
    {
        this.hotelCode = hotelCode;
    }

    public LocalDate getEffectiveDate()
    {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getRewardNightPenetration()
    {
        return rewardNightPenetration;
    }

    public void setRewardNightPenetration(String rewardNightPenetration)
    {
        this.rewardNightPenetration = rewardNightPenetration;
    }

    public Integer getFloor()
    {
        return floor;
    }

    public void setFloor(Integer floor)
    {
        this.floor = floor;
    }

    public Set<AvgDailyRatePercentRuleOccupancy> getRanges()
    {
        return ranges;
    }

    public void setRanges(
            Set<AvgDailyRatePercentRuleOccupancy> ranges)
    {
        this.ranges = ranges;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof AvgDailyRatePercentRule))
        {
            return false;
        }
        AvgDailyRatePercentRule that = (AvgDailyRatePercentRule) o;
        return hotelCode.equals(that.hotelCode) &&
                effectiveDate.equals(that.effectiveDate) &&
                category.equals(that.category) &&
                rewardNightPenetration.equals(that.rewardNightPenetration) &&
                floor.equals(that.floor) &&
                ranges.equals(that.ranges);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(hotelCode, effectiveDate, category, rewardNightPenetration, floor, ranges);
    }

    @Override
    public String toString()
    {
        return "AvgDailyRatePercentRule{" +
                "hotelCode=" + hotelCode +
                ", effectiveDate=" + effectiveDate +
                ", category='" + category + '\'' +
                ", rewardNightPenetration='" + rewardNightPenetration + '\'' +
                ", floor=" + floor +
                ", ranges=" + ranges +
                '}';
    }
}
