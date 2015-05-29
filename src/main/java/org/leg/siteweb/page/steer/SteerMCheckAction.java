package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.json.JSONObject;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.SaleinfoDao;
import org.leg.siteweb.common.dao.StoreinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;
import org.leg.siteweb.common.dao.WorkinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class SteerMCheckAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SteerMCheckAction.class);

    private Integer subUserId;
    private Integer storeId;
    private String queryDate;


	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        UserinfoDao userinfoDao = new UserinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        //查询所辖员工
        IList<Record> userinfoList = Storage.executor().select(userinfoDao.getSubUser(userId));
        if(null != userinfoList && userinfoList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : userinfoList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("userinfoList", resultList);
        }

        //查询所辖门店
        IList<Record> storeInfoList = Storage.executor().select(storeinfoDao.getStoreInfoBySteerId(userId));
        if(null != storeInfoList && storeInfoList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : storeInfoList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("storeInfoList", resultList);
        }

        ServletActionContext.getContext().put("userId", userId);

        return "SUCCESS";

    }

    public String onQuery() {

        WorkinfoDao workinfoDao = new WorkinfoDao();
        SaleinfoDao saleinfoDao = new SaleinfoDao();

        //查询产品列表
        IList<Record> saleList = Storage.executor().select(saleinfoDao.getSaleInfoAll());

        //根据日期、人员、店名查询
        Record workInfo = Storage.executor().load(workinfoDao.selWorkinfoStore(queryDate, subUserId, storeId, 2));

        if(null != workInfo) {

            JSONObject jSale = JSONObject.convert(workInfo.get("SaleInfo").toString());

            List<Record> saleResultList = new ArrayList<Record>();
            for (Record record : saleList) {
                String saleId = record.get("ID").toString();
                String saleName = record.get("ProductName").toString();
                if (null != jSale.get(saleId + "1")) {
                    Record saleResult = new Record();
                    Double price = Double.parseDouble(jSale.get(saleId + "1").toString());
                    Double num = Double.parseDouble(jSale.get(saleId + "2").toString());
                    Double total = Double.parseDouble(jSale.get(saleId + "3").toString());
                    saleResult.put("saleId", saleId);
                    saleResult.put("saleName", saleName);
                    saleResult.put("price", price);
                    saleResult.put("num", num);
                    saleResult.put("total", total);
                    saleResultList.add(saleResult);
                }
            }
            ServletActionContext.getContext().put("saleResultList", saleResultList);

        } else {
            ServletActionContext.getContext().put("saleResultList", null);
        }

        ServletActionContext.getContext().put("queryDate", queryDate);

        String result = "";
        try {
            result = this.execute();
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

    public Integer getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(Integer subUserId) {
        this.subUserId = subUserId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
