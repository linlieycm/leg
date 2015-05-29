package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class GiftinfoDao {

    public String getGiftinfoById(Integer giftId) {
        String result = "select * from Giftinfo where Status = 1 and ID = " + giftId;
        return result;
    }

    public String getGiftInfoByDate(String beginDate, String endDate, String storeCity) {
        String result = "select * from Giftinfo where Status = 1 " +
                " and str_to_date(CreateTime, '%Y-%m-%d') >= '" + beginDate +
                "' and str_to_date(CreateTime, '%Y-%m-%d') <= '" + endDate +
                "' and StoreCity = " + storeCity;
        return result;
    }

    public String getGiftInfoAll() {
        String result = "select * from Giftinfo where Status = 1 ";
        return result;
    }

    public String delGiftInfoById(Integer giftId) {
        String result = "update Giftinfo set status = 0 where ID = " + giftId;
        return result;
    }

    public String updateGiftInfo(Integer giftId, String giftName) {
        String result = "update Giftinfo set GiftName = '" + giftName + "' where ID = " + giftId;
        return result;
    }

    public String insertGift(String giftName) {
        String result = "insert into Giftinfo(GiftName, UpdateTime, Status) values ( '" + giftName +"',Now(), 1)";
        return result;
    }

}
