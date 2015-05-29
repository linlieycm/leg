package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class AttendanceDao {

    public String getAttendanceAll() {
        String result = "select * from Attendance where 1=1 ";

        return result;
    }

    public String getDateInfoByUseId(Integer userId, String today, Integer type) {
        String result = "select * from Attendance where UserId = " + userId
                +" and DATE_FORMAT(Createtime,'%Y-%m-%d') = '" + today
                +"' and type = " + type
                +" limit 1";

        return result;
    }

    public String getAttendanceOneDay(Integer userId, String today) {
        String result = "select DATE_FORMAT(Createtime,'%T') as attTime, Type as type from Attendance where UserId = " + userId
                +" and DATE_FORMAT(Createtime,'%Y-%m-%d') = '" + today + "'";

        return result;
    }

    public String getOneMinTimeDay(Integer userId, String today) {
        String result = "select min(DATE_FORMAT(Createtime, '%T')) as minTime " +
                " from Attendance " +
                " where Status = 1 " +
                " and UserId = " + userId +
                " and DATE_FORMAT(Createtime,'%Y-%m-%d') = '" + today +
                "'";
        return result;
    }

    public String getOneMaxTimeDay(Integer userId, String today) {
        String result = "select max(DATE_FORMAT(Createtime, '%T')) as maxTime " +
                " from Attendance " +
                " where Status = 1 " +
                " and UserId = " + userId +
                " and DATE_FORMAT(Createtime,'%Y-%m-%d') = '" + today +
                "'";
        return result;
    }

    public String insertIntoAttendance(Integer userId,
                                       Integer storeId,
                                       Integer form,
                                       String address,
                                       Integer type,
                                       String picStr) {
        String result = "insert into Attendance(" +
                "UserId," +
                "StoreId," +
                "Form," +
                "Type," +
                "Address," +
                "PicStr," +
                "UpdateTime," +
                "Status) values(" +
                userId + "," +
                storeId + "," +
                form + "," +
                type + ",'" +
                address + "', '" +
                picStr + "', " +
                "Now(), " +
                "1);";
        return result;
    }

    public String getMaxInfo(String begin, String end) {
        String result = "select a.UserId as userId, max(a.CreateTime) as maxTime, str_to_date(a.CreateTime, '%Y-%m-%d') as creaDay, s.StoreCity as city, s.StoreName as storeName, u.Name as name, us.Name as usName " +
                " from Userinfo u, Storeinfo s, Attendance a, Userinfo us" +
                " where a.UserId = u.id " +
                " and a.StoreId = s.id " +
                " and u.SuperiorId = us.id " +
                " and a.Type = 2 ";

        if(null != begin && begin.length() > 0 ) {
            result += " and str_to_date(a.CreateTime, '%Y-%m-%d') >= '" + begin + "'";
        }
        if(null != end && end.length() > 0 ) {
            result += " and str_to_date(a.CreateTime, '%Y-%m-%d') <= '" + end + "'";
        }
        result += " group by a.UserId, str_to_date(a.CreateTime, '%Y-%m-%d') ";

        return result;
    }

    public String getMinInfo(String begin, String end) {
        String result = "select str_to_date(a.CreateTime, '%Y-%m-%d') as creaDay, a.UserId as userId, u.Name as name, us.Name as usName, s.StoreCity as city, s.StoreName as storeName, min(a.CreateTime) as minTime " +
                " from Userinfo u, Storeinfo s, Attendance a, Userinfo us " +
                " where a.UserId = u.id " +
                " and a.StoreId = s.id " +
                " and u.SuperiorId = us.id " +
                " and a.Type = 1 ";

        if(null != begin && begin.length() > 0 ) {
            result += " and str_to_date(a.CreateTime, '%Y-%m-%d') >= '" + begin + "'";;
        }
        if(null != end && end.length() > 0 ) {
            result += " and str_to_date(a.CreateTime, '%Y-%m-%d') <= '" + end + "'";;
        }
        result += " group by a.UserId, str_to_date(a.CreateTime, '%Y-%m-%d') ";

        return result;
    }

    public String getAllIfo(String begin, String end, Integer CityId) {
        String result = "select str_to_date(a.CreateTime, '%Y-%m-%d') as creaDay, a.UserId as userId, u.Name as name, us.Name as usName, s.StoreCity as city, s.StoreName as storeName, a.CreateTime, a.Type as attType " +
                " from Userinfo u, Storeinfo s, Attendance a, Userinfo us " +
                " where a.UserId = u.id  " +
                " and a.StoreId = s.id  " +
                " and u.SuperiorId = us.id " +
                " and s.CityId = " + CityId;
        if(null != begin && begin.length() > 0 ) {
            result += " and str_to_date(a.CreateTime, '%Y-%m-%d') >= '" + begin + "'";;
        }
        if(null != end && end.length() > 0 ) {
            result += " and str_to_date(a.CreateTime, '%Y-%m-%d') <= '" + end + "'";;
        }
        result += " order by 2, 7";

        return result;
    }

}
