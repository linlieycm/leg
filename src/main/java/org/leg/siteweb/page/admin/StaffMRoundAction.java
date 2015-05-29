package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.JxlHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.CityinfoDao;
import org.leg.siteweb.common.dao.WorkinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class StaffMRoundAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(StaffMRoundAction.class);

    private String beginDate;
    private String endDate;
    private String cityName;
    private Integer cityId;


	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        List<Record> resultList = queryResult();
        ServletActionContext.getContext().put("recordlist", resultList);
		return "SUCCESS";
    }

    public String init() {
        //查询城市
        CityinfoDao cityinfoDao = new CityinfoDao();
        IList<Record> cityRecordList = Storage.executor().select(cityinfoDao.getCityinfoAll());
        List<Record> resultList = new ArrayList<Record>();
        if(null != cityRecordList && cityRecordList.size() > 0) {
            for (Record record : cityRecordList) {
                resultList.add(record);
            }
        }
        ServletActionContext.getContext().put("cityRecordList", resultList);

        return "SUCCESS";
    }

    /**
     * 下载记录
     * @return
     */
    public String download() {
        ActionContext context = ActionContext.getContext();

        try {
            InputStream data = JxlHelper.getExcel(queryResult());
            context.put("data", data);
        } catch (Exception e) {
            logger.error("download error:", e);
        }
        return "SUCCESS";
    }

    private List<Record> queryResult() {

        ServletActionContext.getContext().put("beginDate", beginDate);
        ServletActionContext.getContext().put("endDate", endDate);
        ServletActionContext.getContext().put("cityId", cityId);
        CityinfoDao cityinfoDao = new CityinfoDao();
        Record record1 = Storage.executor().load(cityinfoDao.getCityinfoById(cityId));
        if(null != record1) {
            ServletActionContext.getContext().put("cityName", record1.get("CityName"));
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

//        StoreinfoDao storeinfoDao = new StoreinfoDao();
//        IList<Record> cityRecordList = Storage.executor().select(storeinfoDao.getDisCity());
//        ServletActionContext.getContext().put("cityRecordList", cityRecordList);

        //查询记录
        WorkinfoDao workinfoDao = new WorkinfoDao();
        IList<Record> recordList = Storage.executor().select(workinfoDao.selWorkinfoStoreByCond(beginDate, endDate, cityId, 3));
        List<Record> resultList = new ArrayList<Record>();
        if(null != recordList && recordList.size() > 0) {
            for (Record subUser : recordList) {
                resultList.add(subUser);
            }
        }
        return resultList;
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
