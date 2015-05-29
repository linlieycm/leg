package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.CityinfoDao;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 日志记录动作
 */
public class StaffMPromoterAction extends ActionSupport {
    private static final long serialVersionUID = 5165050377715752795L;
    private Integer cityId;
    private Integer storeId;
    private Integer userIdIns;
    private String userName;
    private String contact;
    private Integer userId;

    /**
     * 日志对象
     */
    protected static Logger logger = Logger.getLogger(StaffMPromoterAction.class);

    /**
     * 执行请求动作
     */
    public String execute() throws Exception {
        //查询出待审批的用户
//        UserinfoDao userinfoDao = new UserinfoDao();
//
//        IList<Record> recordList = Storage.executor().select(userinfoDao.getApprovalUser());
//        if(null != recordList && recordList.size() > 0) {
//            List<Record> resultList = new ArrayList<Record>();
//            for (Record record : recordList) {
//                resultList.add(record);
//            }
//            ServletActionContext.getContext().put("recordList", resultList);
//        }

        this.addCity();

        return "SUCCESS";
    }

    public String doQuery() {
        UserinfoDao userinfoDao = new UserinfoDao();

        IList<Record> recordList = Storage.executor().select(userinfoDao.getApprovalUserByCityId(cityId));
        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("recordList", resultList);
        }

        this.addCity();

        return "SUCCESS";
    }

    public String agreeUser() {

        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        Storage.executor().alter(userinfoDao.updateUserStatusById(userId, 1));

        Record record = Storage.executor().load(userinfoDao.getUserInfoById(userId));

        Integer storeid = record.getInteger("StoreId");
        Integer steerId = record.getInteger("SuperiorId");
        Storage.executor().alter(storeinfoDao.updStoreInfoById(userId, storeid, steerId));


        String result = "";
        try {
            result = this.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String refuseUser() {
        UserinfoDao userinfoDao = new UserinfoDao();
        Storage.executor().alter(userinfoDao.updateUserStatusById(userId, 2));

        String result = "";
        try {
            result = this.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String addPromoterPre() {
        //获取门店信息
        StoreinfoDao storeinfoDao = new StoreinfoDao();
        IList<Record> recordList = Storage.executor().select(storeinfoDao.getStoreSimple());
        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("storeRecordList", resultList);
        }
        this.addCity();
        return "SUCCESS";
    }

    public String addPromoter() {
        UserinfoDao userinfoDao = new UserinfoDao();

        Random random = new Random();
        int x = random.nextInt(899999);
        x = x+100000;
        String password = Integer.toString(x);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String queryDate = df.format(new Date());

        try {
            userName = java.net.URLDecoder.decode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Storage.executor().alter(userinfoDao.addStoreInfo(password, userName, 0, 1, 0, contact, queryDate));
        Record userRecord = Storage.executor().load(userinfoDao.selUserPass(userName, password, queryDate));
        ServletActionContext.getContext().put("userIdIns", userRecord.getInteger("ID"));

        //根据cityId寻找门店
        StoreinfoDao storeinfoDao = new StoreinfoDao();
        IList<Record> recordList = Storage.executor().select(storeinfoDao.getStroeInfoByCityId(cityId));
        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("storeRecordList", resultList);
        }

        return "SUCCESS";
    }

    public String addPromoterTwo() {

        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        //根据userid更新所属门店
        Storage.executor().alter(userinfoDao.updateUserStorebyId(storeId, userIdIns));

        //根据门店id更新督导
        Record record = Storage.executor().load(storeinfoDao.getStoreInfoById(storeId));
        Storage.executor().alter(userinfoDao.updateUserSuperiorbyId(record.getInteger("SteerId"),userIdIns));

        String result = "";
        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 输出控制台内容
     *
     * @param content 控制台内容
     */
    public void output(String content) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        }
        catch (IOException ex) {
            logger.error("call Response.getWriter() failed", ex);
            return;
        }
        writer.write(content);
        writer.close();
    }

    public void addCity() {
        CityinfoDao cityinfoDao = new CityinfoDao();
        if(null != cityId) {
            Record record1 = Storage.executor().load(cityinfoDao.getCityinfoById(cityId));
            if (null != record1) {
                ServletActionContext.getContext().put("cityName", record1.get("CityName"));
            }
        }
        //查询城市
        IList<Record> cityRecordList = Storage.executor().select(cityinfoDao.getCityinfoAll());
        List<Record> resultList1 = new ArrayList<Record>();
        if(null != cityRecordList && cityRecordList.size() > 0) {
            for (Record record : cityRecordList) {
                resultList1.add(record);
            }
        }
        ServletActionContext.getContext().put("cityRecordList", resultList1);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserIdIns() {
        return userIdIns;
    }

    public void setUserIdIns(Integer userIdIns) {
        this.userIdIns = userIdIns;
    }
}
