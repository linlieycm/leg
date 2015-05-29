package org.leg.siteweb.page.promoter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.AttendanceDao;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

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
public class AttendanceQueryAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(AttendanceQueryAction.class);

    private String queryDate;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {
        int userId = User.getUserId();
        if(0 == userId) {
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI());
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        AttendanceDao attendanceDao = new AttendanceDao();
        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        Record userRecord = Storage.executor().load(userinfoDao.getUserInfoById(userId));
        ServletActionContext.getContext().put("name", userRecord.get("Name"));


        Record storeRecord = Storage.executor().load(storeinfoDao.getStoreInfoById(Integer.parseInt(userRecord.get("StoreId").toString())));
        ServletActionContext.getContext().put("store", storeRecord.get("StoreName"));

        if( null == queryDate || queryDate.trim().length() == 0) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            queryDate = df.format(new Date());
        }
        ServletActionContext.getContext().put("queryDate", queryDate);

		IList<Record> recordList = Storage.executor().select(attendanceDao.getAttendanceOneDay(userId, queryDate));
        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for(Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("recordList", resultList);

            Record maxTime = Storage.executor().load(attendanceDao.getOneMaxTimeDay(userId, queryDate));
            Record minTime = Storage.executor().load(attendanceDao.getOneMinTimeDay(userId, queryDate));
            ServletActionContext.getContext().put("maxTime", maxTime.get("maxTime"));
            ServletActionContext.getContext().put("minTime", minTime.get("minTime"));
        } else {
            ServletActionContext.getContext().put("maxTime", "无记录");
            ServletActionContext.getContext().put("minTime", "无记录");
        }


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
}
