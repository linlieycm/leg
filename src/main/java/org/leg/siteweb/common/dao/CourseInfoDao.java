package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/2.
 */
public class CourseInfoDao {
    public String getCourseInfoAll() {
        String result = "select * from Courseinfo where Status = 1 ";
        return result;
    }

    public String delCourseById(Integer courseId) {
        String result = "update Courseinfo set status = 0 where status = 1 and  ID = " + courseId;
        return result;
    }

    public String addCourse(String name, String introduce, String linkPla) {
        String result = "insert Courseinfo (Name, " +
                " Introduce, " +
                " LinkPla, " +
                " UpdateTime, " +
                " Status) values('" +
                name + "', '" +
                introduce + "', '" +
                linkPla + "', " +
                " Now(), " +
                " 1" +
                ")";
        return result;
    }

}
