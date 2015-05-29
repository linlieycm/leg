package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class UserinfoDao {
    public String getUserInfoById(Integer userid) {
        String result = "select * from Userinfo where Status != 0 and ID = " + userid;
        return result;
    }

    public String checkUserPass(Integer userid, String password) {
        String result = "select * from Userinfo where Status != 0 and ID = " + userid + " and Password = " + password;
        return result;
    }

    public String selUserPass(String userName, String password, String queryDate) {
        String result = "select * from Userinfo where Status != 0 and Name = '" + userName
                + "' and Password = '" + password
                + "' and EntryTime = '" + queryDate + "'";
        return result;
    }

    public String getSubUser(Integer userid) {
        String result = "select ID as userId, Name as name from Userinfo where Status != 0 and SuperiorId = " + userid + " ";
        return result;
    }

    public String getSubUserInfo(Integer userid) {
        String result = "select u.Id as userId, u.Name as name, s.StoreName as storeName" +
                " from Userinfo u, Storeinfo s " +
                " where u.StoreId = s.ID " +
                " and s.Status = 1 " +
                " and u.Status = 1 " +
                " and SuperiorId = " + userid + " ";
        return result;
    }

    public String updateContact(Integer userid, String contact) {
        String result = "update Userinfo set Contact = " + contact + " where id = " + userid;
        return result;
    }

    public String updateStoreId(Integer userid, Integer storeId) {
        String result = "update Userinfo set StoreId = " + storeId + " where id = " + userid;
        return result;
    }

    public String updateUserStatusById(Integer userid, Integer status) {
        String result = "update Userinfo set status = " + status + " where id = " + userid;
        return result;
    }

    public String deleteUserInfo(Integer userid, String quitTime) {
        String result = "update Userinfo set Status = -1, QuitTime = '" + quitTime +"' where ID = " + userid;
        return result;
    }

    public String deleteUserInfoById(Integer userid) {
        String result = "update Userinfo set Status = -1 where ID = " + userid;
        return result;
    }

    public String updateUserSuperiorbyId(Integer StoreId, Integer userId) {
        String result = "update Userinfo set SuperiorId = " + StoreId + " where ID = " + userId;
        return result;
    }

    public String updateUserStorebyId(Integer StoreId, Integer userId) {
        String result = "update Userinfo set StoreId = " + StoreId + " where ID = " + userId;
        return result;
    }

    public String addStoreInfo(String password,
                               String name,
                               Integer storeId,
                               Integer userType,
                               Integer superiorId,
                               String contact,
                               String entryTime) {
        String result = "insert into Userinfo(" +
                " Password, " +
                " Name, " +
                " StoreId, " +
                " UserType, " +
                " SuperiorId, " +
                " Contact, " +
                " EntryTime, " +
                " UpdateTime, " +
                " Status) values ( " +
                password + ", \"" +
                name + "\", " +
                storeId + ", " +
                userType + ", " +
                superiorId + ", '" +
                contact + "', '" +
                entryTime + "', " +
                " Now(), " +
                " 1)";

        return result;
    }

    //查询待审批的用户
    public String getApprovalUser() {
        String result = "select u.ID as id, u.Name as name, u.Password as password, s.StoreName as storeName, us.Name as superName " +
                " from Userinfo u, Storeinfo s, Userinfo us " +
                " where u.SuperiorId = us.ID " +
                " and u.StoreId = s.ID " +
                " and u.status = 1 ";
        return result;
    }

    //根据查询待审批的用户
    public String getApprovalUserByCityId(Integer cityId) {
        String result = "select u.ID as id, u.Name as name, u.Password as password, s.StoreName as storeName, us.Name as superName " +
                " from Userinfo u, Storeinfo s, Userinfo us " +
                " where u.SuperiorId = us.ID " +
                " and u.StoreId = s.ID " +
                " and s.CityId = " + cityId +
                " and u.status = 1 ";
        return result;
    }

    //督导信息
    public String getSuperiorInfo(String cityName) {
        String result = "select distinct u.ID as ID, u.Name as superiorName, s.StoreCity as storeCity " +
                " from Storeinfo s, Userinfo u " +
                " where u.ID = s.SteerId " +
                " and u.status =1 " +
                " and u.ID != 0 " +
                " and s.StoreCity = '" + cityName + "'";
        return result;
    }

    //督导信息
    public String getSuperiorInfoAll() {
        String result = "select *, c.CityName as CityName " +
                " from Userinfo u, Cityinfo c, Storeinfo s" +
                " where u.UserType = 2 " +
                " and u.ID = s.SteerId"  +
                " and s.CityId = c.ID " +
                " and u.status = 1 " +
                " group by u.ID ";
        return result;
    }

    //督导信息
    public String getSuperiorInfoByCityId(Integer cityId) {
        String result = "select *, c.CityName as CityName " +
                " from Userinfo u, Cityinfo c, Storeinfo s" +
                " where u.UserType = 2 " +
                " and u.ID = s.SteerId"  +
                " and s.CityId = c.ID " +
                " and s.CityId =  " + cityId +
                " and u.status = 1 " +
                " group by u.ID ";
        return result;
    }

    //根据城市查询督导信息
    public String selSuperByType(Integer userType) {
        String result = "select * " +
                "from Userinfo " +
                "where usertype = " + userType;
        return result;
    }

}
