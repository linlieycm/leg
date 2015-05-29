package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.CityinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 日志记录动作
 */
public class SalesMObserverAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SalesMObserverAction.class);

    private Integer userId;
    private String userName;
    private String contact;
    private Integer cityId;


	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        UserinfoDao userinfoDao = new UserinfoDao();

        IList<Record> recordList = Storage.executor().select(userinfoDao.getSuperiorInfoAll());
        if (null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("recordList", resultList);
        }

        this.addCity();

        return "SUCCESS";
    }

    public String queryObserver() {
        UserinfoDao userinfoDao = new UserinfoDao();

        IList<Record> recordList = Storage.executor().select(userinfoDao.getSuperiorInfoByCityId(cityId));
        if (null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("recordList", resultList);
        }

        this.addCity();

        return "SUCCESS";
    }


    public String delObserver() {

        String result = "";

        UserinfoDao userinfoDao = new UserinfoDao();
        Storage.executor().alter(userinfoDao.deleteUserInfoById(userId));

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.addCity();
        return result;

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


    public String addObserverPre() {
        this.addCity();
        return "SUCCESS";
    }

    public String addObserver() {
        UserinfoDao userinfoDao = new UserinfoDao();

        Random random = new Random();
        int x = random.nextInt(899999);
        x = x+100000;
        String password = Integer.toString(x);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String queryDate = df.format(new Date());

        try {
            userName = java.net.URLDecoder.decode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Storage.executor().alter(userinfoDao.addStoreInfo(password, userName, 0, 2, userId, contact, queryDate));

        String result = "";
        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
