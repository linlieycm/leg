package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.JxlHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.CityinfoDao;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class SalesMStoreAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SalesMStoreAction.class);

    private Integer storeId;
    private String storeAddress;
    private String storeName;
    private Integer userSel;
    private Integer cityId;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        StoreinfoDao storeinfoDao = new StoreinfoDao();

        //查询所有城市
//        CityinfoDao cityinfoDao = new CityinfoDao();
//        IList<Record> cityRecordList = Storage.executor().select(cityinfoDao.getCityinfoAll());
//

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

        if (null != cityId) {
            List<Record> resultStoreList = this.getResult();
            ServletActionContext.getContext().put("resultStoreList", resultStoreList);
        } else {
            ServletActionContext.getContext().put("citySelPre", "无");
        }

        return "SUCCESS";
    }

    public String download() {
        ActionContext context = ActionContext.getContext();

        List<Record> resultStoreList = this.getResult();

        InputStream data = null;
        try {
            data = JxlHelper.getExcel(resultStoreList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

        context.put("data", data);

        return "SUCCESS";
    }

    private List<Record> getResult() {
        StoreinfoDao storeinfoDao = new StoreinfoDao();
        List<Record> resultStoreList = null;
        try {
//            cityName = java.net.URLDecoder.decode(cityName, "UTF-8");
//            ServletActionContext.getContext().put("cityName", cityName);
            //根据城市查找店
            IList<Record> recordStoreList = Storage.executor().select(storeinfoDao.getStoreCity(cityId));
            resultStoreList = new ArrayList<Record>();
            for (Record record : recordStoreList) {
                resultStoreList.add(record);
            }

        } catch (Exception e) {
            logger.error("download error:", e);
        }
        return resultStoreList;
    }

    public String delMessage() {

        String result = "";

        StoreinfoDao storeinfoDao = new StoreinfoDao();
        Storage.executor().alter(storeinfoDao.delStoreInfoById(storeId));

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    public String addStorePre() {
        UserinfoDao userinfoDao = new UserinfoDao();
        //查询有效督导
        IList<Record> recordIList = Storage.executor().select(userinfoDao.selSuperByType(2));
        List<Record> resultUserList = new ArrayList<Record>();
        for (Record record : recordIList) {
            resultUserList.add(record);
        }
        ServletActionContext.getContext().put("resultUserList", resultUserList);

        CityinfoDao cityinfoDao = new CityinfoDao();
        //查询城市
        IList<Record> cityRecordList = Storage.executor().select(cityinfoDao.getCityinfoAll());
        List<Record> resultList1 = new ArrayList<Record>();
        if(null != cityRecordList && cityRecordList.size() > 0) {
            for (Record record : cityRecordList) {
                resultList1.add(record);
            }
        }
        ServletActionContext.getContext().put("cityRecordList", resultList1);
        return "SUCCESS";
    }



    public String addStore() {

        StoreinfoDao storeinfoDao = new StoreinfoDao();
        try {
            storeName = java.net.URLDecoder.decode(storeName, "UTF-8");
            storeAddress = java.net.URLDecoder.decode(storeAddress, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //根据门店名，cityID，督导ID查询门店是否已经新增
        Record record = Storage.executor().load(storeinfoDao.selStoreInfoHis(storeName, cityId, userSel));
        if(null == record || Integer.parseInt(record.get("num").toString()) == 0) {
            Storage.executor().alter(storeinfoDao.insertStore(storeName, cityId, storeAddress, userSel));
        }

        String result = "";

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public String updateStorePre() {

        StoreinfoDao storeinfoDao = new StoreinfoDao();
        UserinfoDao userinfoDao = new UserinfoDao();
        //查询有效督导
        IList<Record> recordIList = Storage.executor().select(userinfoDao.selSuperByType(2));
        List<Record> resultUserList = new ArrayList<Record>();
        for (Record record : recordIList) {
            resultUserList.add(record);
        }
        ServletActionContext.getContext().put("resultUserList", resultUserList);

        Record record = Storage.executor().load(storeinfoDao.getStoreInfoById(storeId));
        ServletActionContext.getContext().put("record", record);

        CityinfoDao cityinfoDao = new CityinfoDao();
        //查询城市
        IList<Record> cityRecordList = Storage.executor().select(cityinfoDao.getCityinfoAll());
        List<Record> resultList1 = new ArrayList<Record>();
        if(null != cityRecordList && cityRecordList.size() > 0) {
            for (Record record1 : cityRecordList) {
                resultList1.add(record1);
            }
        }

        ServletActionContext.getContext().put("cityRecordList", resultList1);

        return "SUCCESS";
    }


    public String updateStore() {

        //不用去插入督导ID和员工ID，督导会自己申请
        StoreinfoDao storeinfoDao = new StoreinfoDao();
        try {
            storeName = java.net.URLDecoder.decode(storeName, "UTF-8");
            storeAddress = java.net.URLDecoder.decode(storeAddress, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Storage.executor().alter(storeinfoDao.updateStoreInfo(storeName, cityId, storeAddress, userSel, storeId));

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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getUserSel() {
        return userSel;
    }

    public void setUserSel(Integer userSel) {
        this.userSel = userSel;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
