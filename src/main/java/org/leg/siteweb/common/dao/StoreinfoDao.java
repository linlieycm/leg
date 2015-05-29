package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class StoreinfoDao {

    public String getStoreInfoAll() {
        String result = "select s.*, u1.Name as UserName, u2.Name as SuperiorName" +
                " from Storeinfo s, Userinfo u1, Userinfo u2 " +
                " where s.Status = 1 " +
                " and u1.Status != 0 " +
                " and u2.Status != 0 " +
                " and s.SteerId = u2.ID " +
//                " and s.SalesmanId = u1.ID " +
                " and s.ID = u1.StoreId " +
                " and u1.SuperiorId = u2.ID";
        return result;
    }

//    select s.*, u1.Name as UserName, u2.Name as SuperiorName
//    from Storeinfo s
//    right join Userinfo u1 on s.SteerId = u1.ID
//    right join Userinfo u2 on s.SalesmanId = u2.ID
//    where s.Status = 1
//    and u1.Status != 0
//    and u2.Status != 0
//    and u2.SuperiorId = u1.ID;

    public String getDisCity() {
        String result = "select distinct StoreCity as storeCity from Storeinfo";
        return result;
    }

    public String getStoreSimple() {
        String result = "select * from Storeinfo where status = 1";
        return result;
    }

    public String getStoreCity(Integer citySel) {
//        String result = "select s.ID, s.StoreName, c.CityName as CityName, s.StoreAddress, u1.Name as UserName, u2.Name as SuperiorName" +
//                " from Storeinfo s, Userinfo u1, Userinfo u2, Cityinfo c " +
//                " where s.Status = 1 " +
//                " and u1.Status != 0 " +
//                " and u2.Status != 0 " +
//                " and s.SteerId = u2.ID " +
//                " and s.ID = u1.StoreId " +
//                " and c.ID = s.CityId " +
//                //" and u1.SuperiorId = u2.ID" +
//                " and s.CityId = " + citySel ;
        String result = "select s.ID, s.StoreName, c.CityName as CityName, s.StoreAddress, u1.Name as UserName, u2.Name as SuperiorName " +
                " from (select * from Storeinfo where Status = 1 and CityId = " + citySel +") s" +
                " left join Userinfo u1 " +
                " on u1.Status != 0  and s.ID = u1.StoreId " +
                " left join Userinfo u2 " +
                " on u2.Status != 0 and s.SteerId = u2.ID " +
                " left join Cityinfo c " +
                "on c.ID = s.CityId ";
        return result;
    }

    public String getOneCityStore(String cityName) {
        String result = "select * from Storeinfo where StoreCity = '" + cityName + "'";
        return result;
    }

    public String getStoreInfoAllUse() {
        String result = "select * from Storeinfo where Status = 1";
        return result;
    }

    public String getStoreInfoById(Integer storeid) {
        String result = "select * from Storeinfo where Status = 1 and ID = " + storeid;
        return result;
    }

    public String delStoreInfoById(Integer storeid) {
        String result = "update Storeinfo set Status = 0 where Status = 1 and ID = " + storeid;
        return result;
    }

    public String updStoreInfoById(Integer salesmanId, Integer storeId, Integer steerId) {
        String result = "update Storeinfo " +
                " set SalesmanId = " + salesmanId +
                " , SteerId = " + steerId +
                " where Status = 1 " +
                " and ID = " + storeId;
        return result;
    }

    public String updateStoreInfo(String storeName, Integer cityId, String storeAddress, Integer userSel, Integer storeId) {
        String result = "update Storeinfo " +
                " set StoreName = '" + storeName +
                "', CityId = " + cityId +
                ", StoreAddress = '" + storeAddress +
                "', SteerId = " + userSel +
                " where Status = 1 " +
                " and ID = " + storeId;
        return result;
    }

    public String insertStore(String storeName, Integer cityId, String storeAddress, Integer userSel) {
        String result = "insert into Storeinfo(StoreName, " +
                "CityId, " +
                "StoreAddress, " +
                "SteerId, " +
                "UpdateTime, " +
                "Status) values('" +
                storeName + "', " +
                cityId + ", '" +
                storeAddress + "', " +
                userSel + ", " +
                " Now(), " +
                " 1 " +
                ")";
        return result;
    }

    public String selStoreInfoHis(String storeName, Integer cityId, Integer userSel) {
        String result = "select count(1) as num " +
                " from Storeinfo " +
                " where StoreName = '" + storeName  +
                "' and CityId = " + cityId +
                " and SteerId = " + userSel;
        return result;
    }

    public String getStoreInfoBySteerId(Integer steerId) {
        String result = "select * from Storeinfo where Status = 1 and steerId = " + steerId;
        return result;
    }

    public String getStoreInfoBySteerIdUndo(Integer steerId) {
        String result = "select * from Storeinfo " +
                " where Status = 1 " +
                " and steerId = " + steerId +
                " and Id not in(select storeId " +
                " from Workinfo " +
                " where userId =  " + steerId +
                " and str_to_date(ReportDate, '%Y-%m-%d') = str_to_date(Now(), '%Y-%m-%d'))";
        return result;
    }

    public String getStoreInfoBySteerIdDo(Integer steerId) {
        String result = "select * from Storeinfo " +
                " where Status = 1 " +
                " and steerId = " + steerId;
        return result;
    }

    //根据cityId查询门店
    public String getStroeInfoByCityId(Integer cityId) {
        String result = " select * from Storeinfo where status = 1 and CityId = " + cityId;
        return result;
    }


}
