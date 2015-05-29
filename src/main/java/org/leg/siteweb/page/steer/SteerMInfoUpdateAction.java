package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.UserinfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myj on 15/5/7.
 */
public class SteerMInfoUpdateAction extends ActionSupport {
    private static final long serialVersionUID = 5165050377715752795L;

    /**
     * 日志对象
     */
    protected static Logger logger = Logger.getLogger(SteerMInfoAdjustAction.class);

    private Integer subUserId;
    private String contact;
    private Integer storeId;

    /**
     * 执行请求动作
     */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI());
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        UserinfoDao userinfoDao = new UserinfoDao();

        //保存新的手机号
        if(null != contact) {
            Storage.executor().alter(userinfoDao.updateContact(subUserId, contact));
        }

        //保存新的门店Id
        if(null != storeId) {
            Storage.executor().alter(userinfoDao.updateStoreId(subUserId, storeId));
        }

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

    public Integer getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(Integer subUserId) {
        this.subUserId = subUserId;
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
