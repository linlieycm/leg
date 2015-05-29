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
import org.leg.siteweb.common.dao.*;

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
public class SalesMSaleAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SalesMSaleAction.class);

    private Integer saleId;
    private String saleName;
    private String beginDate;
    private String endDate;
    private Integer storeId;
    private String sku;
    private String cityName;
    private Integer cityId;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        SaleinfoDao saleinfoDao = new SaleinfoDao();

        IList<Record> recordList = Storage.executor().select(saleinfoDao.getSaleInfoAll());
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
        SaleinfoDao saleinfoDao = new SaleinfoDao();
        Record record = Storage.executor().load(saleinfoDao.getSaleInfoById(saleId));
        ServletActionContext.getContext().put("record", record);

        return "SUCCESS";
    }

    public String delMessage() {

        String result = "";

        SaleinfoDao saleinfoDao = new SaleinfoDao();
        Storage.executor().alter(saleinfoDao.delSaleInfoById(saleId));

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

        SaleinfoDao saleinfoDao = new SaleinfoDao();
        try {
            saleName = java.net.URLDecoder.decode(saleName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Storage.executor().alter(saleinfoDao.insertSale(saleName, sku));

        String result = "";

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public String updateGiftPre() {

        SaleinfoDao saleinfoDao = new SaleinfoDao();

        Record record = Storage.executor().load(saleinfoDao.getSaleInfoById(saleId));
        ServletActionContext.getContext().put("record", record);

        return "SUCCESS";
    }


    public String updateGift() {

        SaleinfoDao saleinfoDao = new SaleinfoDao();

        try {
            saleName = java.net.URLDecoder.decode(saleName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Storage.executor().alter(saleinfoDao.updateSaleInfo(saleId, sku, saleName));

        String result = "";

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    public String downQuery() {

        List<Record> giftResultList =  getSaleResult();

        ServletActionContext.getContext().put("giftResultList", giftResultList);

        return "SUCCESS";
    }



    public String download() {
        ActionContext context = ActionContext.getContext();

        try {
            List<Record> saleResultList =  getSaleResult();

            InputStream data = JxlHelper.getExcel(saleResultList);

            context.put("data", data);
        } catch (Exception e) {
            logger.error("download error:", e);
        }
        return "SUCCESS";
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

    public List<Record> getSaleResult(){
        WorkinfoDao workinfoDao = new WorkinfoDao();
        SaleinfoDao saleinfoDao = new SaleinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        ServletActionContext.getContext().put("beginDate", beginDate);
        ServletActionContext.getContext().put("endDate", endDate);

        GiftinfoDao giftinfoDao = new GiftinfoDao();
        //查询产品列表
        IList<Record> saleList = Storage.executor().select(saleinfoDao.getSaleInfoAll());
        //查询赠品列表
        IList<Record> giftList = Storage.executor().select(giftinfoDao.getGiftInfoAll());

        IList<Record> workInfoList = Storage.executor().select(workinfoDao.selWorkinfoStoreBydateAll(beginDate, endDate, cityId, 2));

        List<Record> saleResultList = new ArrayList<Record>();
        List<Record> giftResultList = new ArrayList<Record>();

        List<Record> recordList =  new ArrayList<Record>();
        CityinfoDao cityinfoDao = new CityinfoDao();
        Record cityRecord = Storage.executor().load(cityinfoDao.getCityinfoById(cityId));

        if (null != workInfoList) {

            for (Record workInfo : workInfoList) {

                Integer steerId = workInfo.getInteger("StoreId");
                //查询门店信息
                Record storeRecord = Storage.executor().load(storeinfoDao.getStoreInfoById(steerId));

                //查询督导信息
                UserinfoDao userinfoDao = new UserinfoDao();
                Record userRecord = Storage.executor().load(userinfoDao.getUserInfoById(steerId));

                JSONObject jSale = JSONObject.convert(workInfo.get("SaleInfo").toString());
                String reportDate = workInfo.get("ReportDate").toString();

                Record resultRecord = new Record();

                //以门店ID作为索引
                //resultRecord.put("id", storeRecord.getInteger("ID"));

                //城市
                resultRecord.put("city",cityRecord.getString("CityName"));

                //区域
                resultRecord.put("area", " ");

                //督导
                resultRecord.put("dudao", userRecord.getString("Name"));

                //门店名称
                resultRecord.put("shopname", storeRecord.getString("StoreName"));

                //店面地址
                resultRecord.put("address", storeRecord.getString("StoreAddress"));

                //计划档期
                resultRecord.put("plan", " ");

                //执行场次
                resultRecord.put("zhixing", " ");

                //实际执行日期
                resultRecord.put("reportdate", reportDate);

                //实际执行场次
                resultRecord.put("shiji", " ");

                //销量-目标
                resultRecord.put("", " ");

                //销量-销售
                Double amount = 0.0;
                for (Record record : saleList) {
                    amount += Double.parseDouble(jSale.get(saleId+"3").toString());
                }

                //销量-促购率


                //促购率低原因分析

                //礼品-分配

                //礼品-使用

                //礼品-剩余

//                for (Record record : saleList) {
//                    String saleId = record.get("ID").toString();
//                    String giftName = record.get("ProductName").toString();
//                    if (null != jSale.get(saleId+"1")) {
//
//                        Record saleResult = new Record();
//                        String price = jSale.get(saleId+"1").toString();
//                        String number = jSale.get(saleId+"2").toString();
//                        String amount = jSale.get(saleId+"3").toString();
//                        Record storeInfo = Storage.executor().load(storeinfoDao.getStoreInfoById(workInfo.getInteger("StoreId")));
//                        String storeName = storeInfo.getString("StoreName");
//                        saleResult.put("reportDate", reportDate);
//                        saleResult.put("storeName", storeName);
//                        saleResult.put("giftName", giftName);
//                        saleResult.put("price", price);
//                        saleResult.put("number", number);
//                        saleResult.put("amount", amount);
//                        saleResultList.add(saleResult);
//                    }
//                }
//
//                JSONObject jGift = JSONObject.convert(workInfo.get("GiftInfo").toString());
//                for (Record record : giftList) {
//                    String giftId = record.get("ID").toString();
//                    String giftName = record.get("GiftName").toString();
//                    if (null != jGift.get(giftId)) {
//                        Record giftResult = new Record();
//                        String sNum = jGift.get(giftId).toString();
//                        Double num = 0.0;
//                        if(null != sNum && sNum.length() > 0 ) {
//                            num = Double.parseDouble(jGift.get(giftId).toString());
//                        }
//                        Record storeInfo = Storage.executor().load(storeinfoDao.getStoreInfoById(workInfo.getInteger("StoreId")));
//                        String storeName = storeInfo.getString("StoreName");
//                        giftResult.put("reportDate", reportDate);
//                        giftResult.put("storeName", storeName);
//                        giftResult.put("giftId", giftId);
//                        giftResult.put("giftName", giftName);
//                        giftResult.put("num", num);
//                        giftResultList.add(giftResult);
//                    }
//                }
            }
        }

        this.downloadPre();

        return saleResultList;
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

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
