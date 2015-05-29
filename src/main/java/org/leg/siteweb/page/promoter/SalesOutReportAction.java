package org.leg.siteweb.page.promoter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.SaleinfoDao;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class SalesOutReportAction extends ActionSupport {
	private static final long serialVersionUID = 5165880377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SalesOutReportAction.class);

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        SaleinfoDao saleinfoDao = new SaleinfoDao();
        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        Record userRecord = Storage.executor().load(userinfoDao.getUserInfoById(userId));
        Record storeRecord = Storage.executor().load(storeinfoDao.getStoreInfoById(userRecord.getInteger("StoreId")));

        ServletActionContext.getContext().put("name", userRecord.getString("Name"));
        ServletActionContext.getContext().put("storeName", storeRecord.getString("StoreName"));

        IList<Record> recordIList = Storage.executor().select(saleinfoDao.getSDistinctSKU());
        List<Record> skuList = new ArrayList<Record>();
        for(Record record : recordIList) {
            skuList.add(record);
        }

        ServletActionContext.getContext().put("skuList", skuList);
        ServletActionContext.getContext().put("userId", userId);

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
}
