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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myj on 15/5/7.
 */
public class SteerMInfoChangeEditAction extends ActionSupport {
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
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI());
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        Record userRecrod = Storage.executor().load(userinfoDao.getUserInfoById(subUserId));
        ServletActionContext.getContext().put("userRecrod", userRecrod);

        if( null != userRecrod) {
            Integer storeId = userRecrod.getInteger("StoreId");
            Record storeRecord = Storage.executor().load(storeinfoDao.getStoreInfoById(storeId));
            ServletActionContext.getContext().put("storeRecord", storeRecord);

            Integer superiorId = userRecrod.getInteger("SuperiorId");
            IList<Record> recordList = Storage.executor().select(storeinfoDao.getStoreInfoBySteerId(superiorId));
            List<Record> storeList = new ArrayList<Record>();
            for (Record record : recordList) {
                storeList.add(record);
            }

            ServletActionContext.getContext().put("userId", userId);
            ServletActionContext.getContext().put("storeList", storeList);
        } else {

            ServletActionContext.getContext().put("storeList", null);
        }
        return "SUCCESS";
    }

    public Integer getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(Integer subUserId) {
        this.subUserId = subUserId;
    }
}
