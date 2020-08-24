package home.sabapathy.jms.utils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import home.sabapathy.jms.mock.Hotel;

public class OccAdrPctHotelHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OccAdrPctHotelHelper.class);

    @Resource
    //    GrowthDao growthDao;

    public Hotel getHotelByCode(String hotelCode)
    {
        //        Hotel hotel = growthDao.getHotelByCode(hotelCode);
        Hotel hotel = new Hotel();
        if (hotel == null)
        {
            LOGGER.error("Could not find a hotel with code {}", hotelCode);
            //            throw new CodedException("No Hotel found for code " + hotelCode);
        }
        return hotel;
    }
}
