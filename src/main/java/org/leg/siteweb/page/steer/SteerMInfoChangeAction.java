package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

/**
 * Created by myj on 15/5/7.
 */
public class SteerMInfoChangeAction extends ActionSupport {
    private static final long serialVersionUID = 5165050377715752795L;

    /**
     * 日志对象
     */
    protected static Logger logger = Logger.getLogger(SteerMInfoAdjustAction.class);

    private Integer subUserId;

    /**
     * 执行请求动作
     */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        Record userRecrod = Storage.executor().load(userinfoDao.getUserInfoById(subUserId));
        ServletActionContext.getContext().put("userRecrod", userRecrod);

        Integer storeId = userRecrod.getInteger("StoreId");
        Record storeRecord = Storage.executor().load(storeinfoDao.getStoreInfoById(storeId));
        ServletActionContext.getContext().put("storeRecord", storeRecord);

        ServletActionContext.getContext().put("userId", userId);
        return "SUCCESS";
    }

    public Integer getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(Integer subUserId) {
        this.subUserId = subUserId;
    }

}
