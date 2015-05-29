package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class CityinfoDao {

    public String getCityinfoAll() {
        String result = "select * from Cityinfo where status = 1";
        return result;
    }

    public String getCityinfoById(Integer cityId) {
        String result = "select * from Cityinfo where status = 1 and ID = " + cityId;
        return result;
    }

}
