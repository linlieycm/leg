package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class SendInfoDao {
    public String getSendInfoById(Integer courseId) {
        String result = "select * from Sendinfo where Status = 1 and ID = " + courseId;
        return result;
    }

    public String getSendInfoAll() {
        String result = "select * from Sendinfo where Status = 1 ";
        return result;
    }

    public String delSendById(Integer courseId) {
        String result = "update Sendinfo set status = 0 where status = 1 and  ID = " + courseId;
        return result;
    }

    public String addSend(String name, String introduce) {
        String result = "insert Sendinfo (Name, " +
                " Introduce, " +
                " UpdateTime, " +
                " Status) values('" +
                name + "', '" +
                introduce + "', " +
                " Now(), " +
                " 1" +
                ")";
        return result;
    }

}
