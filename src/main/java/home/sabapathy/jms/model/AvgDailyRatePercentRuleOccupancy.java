package home.sabapathy.jms.model;

import java.util.Objects;

public class AvgDailyRatePercentRuleOccupancy
{
    private Integer occLowPercent;
    private Integer occHighPercent;
    private Integer adrPercent;

    public AvgDailyRatePercentRuleOccupancy()
    {
    }
    
    public AvgDailyRatePercentRuleOccupancy(Integer occLowPercent, Integer occHighPercent, Integer adrPercent)
    {
        this.occLowPercent = occLowPercent;
        this.occHighPercent = occHighPercent;
        this.adrPercent = adrPercent;
    }

    public Integer getOccLowPercent()
    {
        return occLowPercent;
    }

    public void setOccLowPercent(Integer occLowPercent)
    {
        this.occLowPercent = occLowPercent;
    }

    public Integer getOccHighPercent()
    {
        return occHighPercent;
    }

    public void setOccHighPercent(Integer occHighPercent)
    {
        this.occHighPercent = occHighPercent;
    }

    public Integer getAdrPercent()
    {
        return adrPercent;
    }

    public void setAdrPercent(Integer adrPercent)
    {
        this.adrPercent = adrPercent;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof AvgDailyRatePercentRuleOccupancy))
        {
            return false;
        }
        AvgDailyRatePercentRuleOccupancy that = (AvgDailyRatePercentRuleOccupancy) o;
        return occLowPercent.equals(that.occLowPercent) &&
                occHighPercent.equals(that.occHighPercent) &&
                adrPercent.equals(that.adrPercent);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(occLowPercent, occHighPercent, adrPercent);
    }

    @Override
    public String toString()
    {
        return "AvgDailyRatePercentRuleOccupancy{" +
                "occLowPercent=" + occLowPercent +
                ", occHighPercent=" + occHighPercent +
                ", adrPercent=" + adrPercent +
                '}';
    }
}
