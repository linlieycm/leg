package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class WorkinfoDao {

    public String selWorkinfoById(Integer id) {
        String result = "select * from Workinfo where ID = " + id;
        return result;
    }

    public String insertWorkinfoThree(Integer storeId,
                                   Integer userId,
                                   Integer reportType,
                                   String picIdList,
                                   String remark,
                                   Integer status) {
        String result = "insert into Workinfo (" +
                " StoreId," +
                " ReportDate," +
                " UserId," +
                " ReportType," +
                " PicIdList," +
                " UpdateTime," +
                " Remark," +
                " Status)" +
                " values(" +
                storeId + ", " +
                " Now(), " +
                userId + ", " +
                reportType + ", '" +
                picIdList + "', " +
                " Now(), '" +
                remark + "', " +
                status +
                " )";
        return result;
    }

    public String insertWorkinfoUp(Integer storeId,
                                 Integer userId,
                                 Integer reportType,
                                 String giftInfo,
                                 String picIdList,
                                 Integer stockStatus,
                                 Integer status) {
        String result = "insert into Workinfo (" +
                " StoreId," +
                " ReportDate," +
                " UserId," +
                " ReportType," +
                " GiftInfo," +
                " PicIdList," +
                " StockStatus," +
                " UpdateTime," +
                " Status)" +
                " values(" +
                storeId + ", " +
                " Now(), " +
                userId + ", " +
                reportType + ", '" +
                giftInfo + "', '" +
                picIdList + "', " +
                stockStatus + ", " +
                " Now(), " +
                status +
                " )";
        return result;
    }

    public String insertWorkinfoDown(Integer storeId,
                                     Integer userId,
                                     Integer reportType,
                                     String saleInfo,
                                     String giftInfo,
                                     String picIdList,
                                     Integer stockStatus,
                                     Integer status) {
        String result = "insert into Workinfo (" +
                " StoreId," +
                " ReportDate," +
                " UserId," +
                " ReportType," +
                " SaleInfo," +
                " GiftInfo," +
                " PicIdList," +
                " StockStatus," +
                " UpdateTime," +
                " Status)" +
                " values(" +
                storeId + ", " +
                " Now(), " +
                userId + ", " +
                reportType + ", '" +
                saleInfo + "', '" +
                giftInfo + "', '" +
                picIdList + "', " +
                stockStatus + ", " +
                " Now(), " +
                status +
                " )";
        return result;
    }

    public String selWorkinfo(String reportDate,
                              Integer userId,
                              Integer reportType) {
        String result = "select * from Workinfo " +
                " where userId = " + userId +
                " and reportType = " + reportType +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') = '" + reportDate +
                "' ";
        return result;
    }

    public String selWorkinfoStore(String reportDate,
                              Integer userId,
                              Integer storeId,
                              Integer reportType) {
        String result = "select * from Workinfo " +
                " where userId = " + userId +
                " and reportType = " + reportType +
                " and storeId = " + storeId +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') = '" + reportDate +
                "' ";
        return result;
    }

    public String selWorkinfoStoreBydate(String beginDate,
                                   String endDate,
                                   Integer cityId,
                                   Integer reportType) {
        String result = "select * from Workinfo " +
                " where reportType = " + reportType +
                " and storeId in (select ID from Storeinfo where CityId = " + cityId + ")" +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') >= '" + beginDate + "' " +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') <= '" + endDate + "'";
        return result;
    }

    public String selWorkinfoStoreBydateAll(String beginDate,
                                         String endDate,
                                         Integer cityId,
                                         Integer reportType) {
        String result = "select * from Workinfo " +
                " where reportType = " + reportType +
                " and storeId in (select ID from Storeinfo where CityId = " + cityId + ")" +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') >= '" + beginDate + "' " +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') <= '" + endDate + "'";
        return result;
    }

    public String selWorkinfoStoreBydateUser(String beginDate,
                                         String endDate,
                                         Integer userId,
                                         Integer reportType) {
        String result = "select * from Workinfo " +
                " where reportType = " + reportType +
                " and storeId in (select ID from Storeinfo where SteerId = " + userId + ")" +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') >= '" + beginDate + "' " +
                " and DATE_FORMAT(reportDate,'%Y-%m-%d') <= '" + endDate + "'";
        return result;
    }

    public String selWorkinfoStoreByCond(String beginDate,
                                         String endDate,
                                         Integer cityId,
                                         Integer reportType) {
        String result = "select DATE_FORMAT(w.ReportDate,'%Y-%m-%d') as reportDate, w.Remark as remark, u.Name as usName, s.StoreName as storeName " +
                " from Workinfo w, Userinfo u, Storeinfo s " +
                " where reportType = " + reportType +
                " and w.UserId = u.ID " +
                " and w.StoreId = s.ID ";

        if(null != cityId) {
            result += " and w.storeId in (select ID from Storeinfo where CityId = " + cityId + ")";
        }

        if(null != beginDate && beginDate.length() > 0) {
            result += " and DATE_FORMAT(w.reportDate,'%Y-%m-%d') >= '" + beginDate + "' ";
        }

        if(null != endDate && endDate.length() > 0) {
            result += " and DATE_FORMAT(w.reportDate,'%Y-%m-%d') <= '" + endDate + "'";
        }
        return result;
    }

    //查询某个督导名下所有人的离岗报告
    public String getOutInfoBySuperId(Integer superId) {
        String result = "select b.StoreName as storeName, c.Name as userName, a.ID as workId, DATE_FORMAT(a.CreateTime,'%Y-%m-%d') as reportDate " +
                " from Workinfo a, Storeinfo b, Userinfo c" +
                " where a.UserId = c.ID" +
                " and b.ID = c.StoreId" +
                " and a.status = 1" +
                " and a.ReportType = 2" +
                " and b.status = 1" +
                " and c.status = 1" +
                " and a.userId in (" +
                " select id from Userinfo" +
                " where status = 1" +
                " and SuperiorId = " + superId +")";
        return result;
    }

    //根据id跟新一条记录的状态
    public String updateWorkinfoStatusById(Integer id, Integer status) {
        String result = "update Workinfo set status = " + status + " where id = " + id;
        return result;
    }

}
