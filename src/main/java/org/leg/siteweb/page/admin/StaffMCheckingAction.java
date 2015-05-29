package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.JxlHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.AttendanceDao;
import org.leg.siteweb.common.dao.CityinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class StaffMCheckingAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(StaffMCheckingAction.class);

    private String beginDate;
    private String endDate;
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
        this.addCity();
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

        AttendanceDao attendanceDao = new AttendanceDao();

//        IList<Record> recordMinList = Storage.executor().select(attendanceDao.getMinInfo(beginDate, endDate));
//        IList<Record> recordMaxList = Storage.executor().select(attendanceDao.getMaxInfo(beginDate, endDate));
//        for(Record recordMin : recordMinList) {
//            String sentry = "无记录";
//            for(Record recordMax : recordMaxList) {
//                if(recordMax.getInteger("userId").equals(recordMin.getInteger("userId"))&&
//                        recordMax.getDate("creaDay").toString().equals(recordMin.getDate("creaDay").toString())) {
//                    sentry = recordMax.get("maxTime").toString();
//                    break;
//                }
//            }
//            recordMin.put("maxTime", sentry);
//        }

        IList<Record> recordMinList = Storage.executor().select(attendanceDao.getAllIfo(beginDate, endDate, cityId));

        List<Record> resultList = new ArrayList<Record>();
        if(null != recordMinList && recordMinList.size() > 0) {
            for (Record subUser : recordMinList) {
                if(1 == subUser.getInteger("attType")) {
                    subUser.put("attType", "上岗");
                } else if(2 == subUser.getInteger("attType")) {
                    subUser.put("attType", "下岗");
                }
                resultList.add(subUser);
            }
        }

        this.addCity();

        return resultList;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
