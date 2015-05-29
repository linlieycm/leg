package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by myj on 15/5/7.
 */
public class SteerMAddStaffAction extends ActionSupport {
    private static final long serialVersionUID = 5165050377715752795L;

    /**
     * 日志对象
     */
    protected static Logger logger = Logger.getLogger(SteerMInfoAdjustAction.class);

    private String queryDate;
    private String name;
    private String contact;
    private Integer storeId;


    /**
     * 执行请求动作
     */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        StoreinfoDao storeinfoDao = new StoreinfoDao();

        //查询所辖分店
        IList<Record> storeinfoList = Storage.executor().select(storeinfoDao.getStoreInfoBySteerId(userId));
        if(null != storeinfoList && storeinfoList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record storeinfo : storeinfoList) {
                resultList.add(storeinfo);
            }
            ServletActionContext.getContext().put("storeinfoList", resultList);
        }

        ServletActionContext.getContext().put("userId", userId);
        return "SUCCESS";
    }

    public String doInsert() {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        UserinfoDao userinfoDao = new UserinfoDao();

        Random random = new Random();
        int x = random.nextInt(899999);
        x = x+100000;
        String password = Integer.toString(x);

        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Storage.executor().alter(userinfoDao.addStoreInfo(password, name, storeId, 1, userId, contact, queryDate));

        //查询所辖员工
        IList<Record> subUserList = Storage.executor().select(userinfoDao.getSubUserInfo(userId));
        if(null != subUserList && subUserList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record subUser : subUserList) {
                resultList.add(subUser);
            }
            ServletActionContext.getContext().put("subUserList", resultList);
        }

        ServletActionContext.getContext().put("userId", userId);

        return "SUCCESS";
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
