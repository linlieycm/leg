package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.json.JSONObject;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.JxlHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.CityinfoDao;
import org.leg.siteweb.common.dao.GiftinfoDao;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.WorkinfoDao;

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
public class SalesMGiftAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SalesMGiftAction.class);

    private Integer giftId;
    private String giftName;
    private String beginDate;
    private String endDate;
    private Integer storeId;
    private String cityName;
    private Integer cityId;


	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        GiftinfoDao giftinfoDao = new GiftinfoDao();

        IList<Record> recordList = Storage.executor().select(giftinfoDao.getGiftInfoAll());
        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("recordList", resultList);
        }

        return "SUCCESS";
    }

    public String checkMessage() {
        GiftinfoDao giftinfoDao = new GiftinfoDao();
        Record record = Storage.executor().load(giftinfoDao.getGiftinfoById(giftId));
        ServletActionContext.getContext().put("record", record);

        return "SUCCESS";
    }

    public String delMessage() {

        String result = "";

        GiftinfoDao giftinfoDao = new GiftinfoDao();
        Storage.executor().alter(giftinfoDao.delGiftInfoById(giftId));

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    public String addGiftPre() {
        return "SUCCESS";
    }


    public String addGift() {

        //不用去插入督导ID和员工ID，督导会自己申请
        GiftinfoDao giftinfoDao = new GiftinfoDao();
        try {
            giftName = java.net.URLDecoder.decode(giftName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Storage.executor().alter(giftinfoDao.insertGift(giftName));

        String result = "";

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public String updateGiftPre() {

        GiftinfoDao giftinfoDao = new GiftinfoDao();

        Record record = Storage.executor().load(giftinfoDao.getGiftinfoById(giftId));
        ServletActionContext.getContext().put("record", record);

        return "SUCCESS";
    }


    public String updateGift() {

        GiftinfoDao giftinfoDao = new GiftinfoDao();
        try {
            giftName = java.net.URLDecoder.decode(giftName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Storage.executor().alter(giftinfoDao.updateGiftInfo(giftId, giftName));

        String result = "";

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String downloadPre() {

        //查出所有城市
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

        return "SUCCESS";
    }

    public String downQuery() {

        List<Record> giftResultList =  getGiftResult();

        ServletActionContext.getContext().put("giftResultList", giftResultList);

        return "SUCCESS";
    }



    public String download() {
        ActionContext context = ActionContext.getContext();

        try {
            List<Record> giftResultList =  getGiftResult();

            InputStream data = JxlHelper.getExcel(giftResultList);

            context.put("data", data);
        } catch (Exception e) {
            logger.error("download error:", e);
        }
        return "SUCCESS";
    }

    public List<Record> getGiftResult(){
        WorkinfoDao workinfoDao = new WorkinfoDao();
        GiftinfoDao giftinfoDao = new GiftinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        ServletActionContext.getContext().put("beginDate", beginDate);
        ServletActionContext.getContext().put("endDate", endDate);
        //ServletActionContext.getContext().put("storeId", storeId);
        ServletActionContext.getContext().put("cityName", cityName);

        //查询赠品列表
        IList<Record> giftList = Storage.executor().select(giftinfoDao.getGiftInfoAll());

        //根据日期、人员、店名查询
        IList<Record> workInfoList = Storage.executor().select(workinfoDao.selWorkinfoStoreBydate(beginDate, endDate, cityId, 2));

//        Record storeInfo = Storage.executor().load(storeinfoDao.getStoreInfoById(storeId));
//        String storeName = storeInfo.getString("StoreName");
//
//        ServletActionContext.getContext().put("storeName", storeName);

        List<Record> giftResultList = new ArrayList<Record>();
        if (null != workInfoList) {

            for (Record workInfo : workInfoList) {

                JSONObject jGift = JSONObject.convert(workInfo.get("GiftInfo").toString());
                String reportDate = workInfo.get("ReportDate").toString();

                for (Record record : giftList) {
                    String giftId = record.get("ID").toString();
                    String giftName = record.get("GiftName").toString();
                    if (null != jGift.get(giftId)) {
                        Record giftResult = new Record();
                        String sNum = jGift.get(giftId).toString();
                        Double num = 0.0;
                        if(null != sNum && sNum.length() > 0 ) {
                            num = Double.parseDouble(jGift.get(giftId).toString());
                        }
                        Record storeInfo = Storage.executor().load(storeinfoDao.getStoreInfoById(workInfo.getInteger("StoreId")));
                        String storeName = storeInfo.getString("StoreName");
                        giftResult.put("reportDate", reportDate);
                        giftResult.put("storeName", storeName);
                        giftResult.put("giftId", giftId);
                        giftResult.put("giftName", giftName);
                        giftResult.put("num", num);
                        giftResultList.add(giftResult);
                    }
                }
            }
        }

        this.downloadPre();

        return giftResultList;
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

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
