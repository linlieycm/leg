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
import org.leg.siteweb.common.dao.WorkinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日志记录动作
 */
public class RoundOldReportAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(RoundOldReportAction.class);

    private String queryDate;
    private Integer storeId;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        if(null == queryDate || queryDate.length() <= 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            queryDate = df.format(new Date());
        }

        StoreinfoDao storeinfoDao = new StoreinfoDao();
        WorkinfoDao workinfoDao = new WorkinfoDao();

        //查询所辖分店
        IList<Record> storeinfoList = Storage.executor().select(storeinfoDao.getStoreInfoBySteerIdDo(userId));
        if(null != storeinfoList && storeinfoList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record storeinfo : storeinfoList) {
                resultList.add(storeinfo);
            }
            ServletActionContext.getContext().put("storeinfoList", resultList);
        }

        //查询巡店报告
        if(null != storeId) {
            Record workInfo = Storage.executor().load(workinfoDao.selWorkinfoStore(queryDate, userId, storeId, 3));
            ServletActionContext.getContext().put("workInfo", workInfo);

            if(null != workInfo) {
                Record storeinfo = Storage.executor().load(storeinfoDao.getStoreInfoById(workInfo.getInteger("StoreId")));
                ServletActionContext.getContext().put("storeinfo", storeinfo);
            }
        }

        ServletActionContext.getContext().put("storeId", storeId);
        return "SUCCESS";

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

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
