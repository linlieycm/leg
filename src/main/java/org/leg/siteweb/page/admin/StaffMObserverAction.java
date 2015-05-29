package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.StoreinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class StaffMObserverAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(StaffMObserverAction.class);

    private Integer cityId;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        //查询所有城市
        StoreinfoDao storeinfoDao = new StoreinfoDao();
        IList<Record> recordList = Storage.executor().select(storeinfoDao.getDisCity());

        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("storeCityList", resultList);

            if (null != cityId) {
                ServletActionContext.getContext().put("citySelPre", cityId);
                //根据城市查找店
                IList<Record> recordStoreList = Storage.executor().select(storeinfoDao.getStoreCity(cityId));
                List<Record> resultStoreList = new ArrayList<Record>();
                for (Record record : recordStoreList) {
                    resultStoreList.add(record);
                }
                ServletActionContext.getContext().put("resultStoreList", cityId);
            } else {
                ServletActionContext.getContext().put("citySelPre", "无");
            }
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
